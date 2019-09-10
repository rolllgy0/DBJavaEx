import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainForm extends JFrame {
	public MainForm() {
		setTitle("MainForm");

		setLocation(400, 200);

		// setLocationRelativeTo(null);
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 85, 484, 182);
		panel.setBackground(new Color(255, 215, 0));

		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("SelectMember");
		btnNewButton.setFont(new Font("±¼¸²", Font.PLAIN, 12));
		btnNewButton.setBounds(332, 136, 127, 23);
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 484, 85);
		panel_1.setBackground(new Color(51, 51, 204));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("MainForm");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("KoPubµ¸¿òÃ¼ Bold", Font.PLAIN, 20));
		lblNewLabel.setBounds(188, 23, 107, 36);
		panel_1.add(lblNewLabel);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MainForm();
	}
}
