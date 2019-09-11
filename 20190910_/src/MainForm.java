import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MainForm extends JFrame {
	private JTable table;
	private DefaultTableModel dtModel;
	private Vector<String> vec_column = new Vector<>();
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	JLabel lblMainForm = new JLabel("MainForm");
	
	JButton btnSelect = new JButton("SelectMember");

	public void init() {
		panel1.setBounds(0, 0, 484, 85);
		panel1.setBackground(new Color(51, 51, 204));
		getContentPane().add(panel1);
		panel1.setLayout(null);
		
		panel2.setBounds(0, 85, 484, 182);
		panel2.setBackground(new Color(255, 215, 0));
		getContentPane().add(panel2);
		panel2.setLayout(null);
		
		lblMainForm.setForeground(new Color(255, 255, 255));
		lblMainForm.setFont(new Font("KoPub돋움체 Bold", Font.PLAIN, 20));
		lblMainForm.setBounds(188, 23, 107, 36);
		panel1.add(lblMainForm);
		
		btnSelect.setBounds(340, 136, 127, 23);
		panel2.add(btnSelect);
		
		vec_column.addElement("ID");
		vec_column.addElement("PW");
		vec_column.addElement("COMMENTA");
		vec_column.addElement("GENDER");
		
		dtModel = new DefaultTableModel(vec_column, 0);
//		dtModel.addRow(new Object[] {"111", "222", "333", "444"});
		
		table = new JTable();
		table.setModel(dtModel);
		table.setRowHeight(23);
		table.setBounds(12, 10, 306, 150);
		panel2.add(table);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(12, 10, 310, 154);
		panel2.add(sp);
	}
	
	public void event() {
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dtModel.setDataVector(new Vector<String>(), vec_column);
				/*
				 * 1. ojdbc6.jar 추가
				 * 2. class.forName으로 oracle이면 oracle driver class 확인
				 * 3. Connection으로 DB 연결
				 * 4. PrepareStatement sql 구문 작성
				 * 5. ResultSet rs에 table의 내용 반환
				 * 6. connection pstmt rs를 close()로 자원 읽기 속성 해제
				 */
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "1234");
					pstmt = conn.prepareStatement("select * from member");
					rs = pstmt.executeQuery();
					while(rs.next()) {
						Object[] temp = new Object[] {rs.getString(1),
													  rs.getString(2),
													  rs.getString(3),
													  rs.getString(4)};
						dtModel.addRow(temp);
						dtModel.fireTableDataChanged();
					}
				}catch (Exception ex) {
					ex.printStackTrace();
				}finally {
					try {
						if(rs != null)
							rs.close();
						if(pstmt != null)
							pstmt.close();
						if(conn != null)
							conn.close();
					}catch (Exception ee) {}
				}
			}
		});
	}
	
	public MainForm() {
		setTitle("MainForm");
		setLocation(650, 350);
		// setLocationRelativeTo(null);
		
		init();
		event();
		
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MainForm();
	}
}
