package swing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends JPanel {
	JLabel lbDangnhap, lbUsername, lbPassword;
	JTextField tfUsername, tfPassword;
	
	JButton btnDanhNhap, btnThoat;
	
	LoginPanel() {
		setBackground(Color.CYAN);
		setLayout(new FlowLayout());
		setBorder(new EmptyBorder(40, 0, 0, 0));
		
		lbDangnhap = new JLabel("Đăng nhập");
		lbDangnhap.setFont(new Font("Arial", Font.PLAIN, 16));
		lbDangnhap.setForeground(Color.BLUE);
		
		add(lbDangnhap);
	}
	
}
