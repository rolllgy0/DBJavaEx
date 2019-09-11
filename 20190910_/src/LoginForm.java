import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class LoginForm extends JFrame implements ActionListener{
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	JLabel lblLoginForm = new JLabel("Login Form");
	JLabel lblId = new JLabel("ID");
	JLabel lblPw = new JLabel("PW");
	
	private JTextField tf_id;
	private JTextField tf_pw;
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnLogin = new JButton("Login");
	
	public void init() {
		panel1.setBackground(new Color(0, 0, 153));
		panel1.setBounds(0, 0, 284, 86);
		getContentPane().add(panel1);
		panel1.setLayout(null);
		
		panel2.setBackground(new Color(255, 215, 0));
		panel2.setBounds(0, 84, 284, 148);
		getContentPane().add(panel2);
		panel2.setLayout(null);
		
		lblLoginForm.setForeground(Color.WHITE);
		lblLoginForm.setFont(new Font("KoPub돋움체 Bold", Font.BOLD, 20));
		lblLoginForm.setBounds(85, 21, 113, 44);
		panel1.add(lblLoginForm);
		
		btnCancel.setBounds(40, 103, 102, 24);
		panel2.add(btnCancel);
		
		btnLogin.setBounds(152, 102, 102, 24);
		panel2.add(btnLogin);
		
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setBounds(40, 23, 52, 24);
		panel2.add(lblId);
		
		lblPw.setForeground(new Color(0, 0, 0));
		lblPw.setBounds(40, 57, 52, 24);
		panel2.add(lblPw);
		
		tf_id = new JTextField();
		tf_id.setBounds(104, 23, 150, 24);
		panel2.add(tf_id);
		tf_id.setColumns(10);
		
		tf_pw = new JTextField();
		tf_pw.setColumns(10);
		tf_pw.setBounds(104, 57, 150, 24);
		panel2.add(tf_pw);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancel) {
			System.exit(0);
		}else {
			// DB 연결 해가지고 ID PW 가 같으면
			/*
			 * 1. ojdbc6.jar 파일을 가져와서 Project에 BuildPass 설정
			 * 2. class.forName 함수로 class가 추가 되었는지 확인
			 * 3. Connection DriverManager.getConnection DB 연결
			 * 4. PrepareStatement pstmt sql 구문 지정
			 * 5. ResultSet Table 내용 담기
			 * 
			 * insert update delete	:: executeUpdate(); -- 반환값이 0,1
			 * select				:: executeQuery();  -- 반환값이 table
			 */
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","1234");
				pstmt = conn.prepareStatement("select * from member where id=? and pw=?");
				pstmt.setString(1, tf_id.getText());
				pstmt.setString(2, tf_pw.getText());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					setVisible(false);
					new MainForm();	
				}else {
					JOptionPane.showMessageDialog(null, "로그인 정보를 확인 하세요.");
				}
				
			}catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public LoginForm(){
		setLocation(650, 350);
//		setLocationRelativeTo(null);	// JFrame 중간으로 실행
		
		setTitle("LoginForm");
		setSize(300,270);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
		btnCancel.addActionListener(this);
		btnLogin.addActionListener(this);
		
		getContentPane().setLayout(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new LoginForm();
	}
}






