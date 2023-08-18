package swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterPanel extends JPanel implements ActionListener {
	JLabel lbDangky, lbUsername, lbPassword, lbRePassword;
	JTextField tfUsername;
	JPasswordField tfPassword, tfRePassword;
	 
	JButton btnDangky, btnDanhNhap, btnThoat;
	
	RegisterPanel(MainApp mainApp) {
		setBackground(Color.CYAN);
		setLayout(new FlowLayout());
		setBorder(new EmptyBorder(40, 0, 0, 0));
		
		///
		lbDangky = new JLabel("Đăng ký");
		lbDangky.setFont(new Font("Arial", Font.BOLD, 18));
		lbDangky.setForeground(Color.BLUE);
		
		///
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.setPreferredSize(new Dimension(510, 70));
		panel.setBackground(Color.CYAN);
		
		lbUsername = new JLabel("Tên đăng nhập:");
		lbUsername.setFont(new Font("Arial", Font.PLAIN, 14));
		tfUsername = new JTextField();
		tfUsername.setBorder(BorderFactory.createCompoundBorder(tfUsername.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfUsername.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lbPassword = new JLabel("Mật khẩu:");
		lbPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		tfPassword = new JPasswordField();
		tfPassword.setBorder(BorderFactory.createCompoundBorder(tfPassword.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lbRePassword = new JLabel("Nhập lại mật khẩu:");
		lbRePassword.setFont(new Font("Arial", Font.PLAIN, 14));
		tfRePassword = new JPasswordField();
		tfRePassword.setBorder(BorderFactory.createCompoundBorder(tfRePassword.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfRePassword.setFont(new Font("Arial", Font.PLAIN, 14));
		
		panel.add(lbUsername);
		panel.add(tfUsername);
		panel.add(lbPassword);
		panel.add(tfPassword);
		panel.add(lbRePassword);
		panel.add(tfRePassword);
		
		///
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setBackground(Color.CYAN);
		
		btnDangky = new JButton("Đăng ký");
		btnDangky.setBackground(new Color(0, 128, 255));
		btnDangky.setForeground(Color.WHITE);
		btnDangky.setBorderPainted(false); 
		btnDangky.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnDanhNhap = new JButton("Đăng nhập");
		btnDanhNhap.setBackground(new Color(0, 128, 255));
		btnDanhNhap.setForeground(Color.WHITE);
		btnDanhNhap.setBorderPainted(false); 
		btnDanhNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDanhNhap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "login");
				mainApp.setTitle("Đăng nhập");
				mainApp.setSize(600, 260);
				tfUsername.setText("");
				tfPassword.setText("");
				tfRePassword.setText(""); 
			}
		});
		
		btnThoat = new JButton("Thoát");
		btnThoat.setBackground(new Color(255, 0, 0));
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setBorderPainted(false); 
		btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThoat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "main");
				mainApp.setTitle("Ứng dụng quản lý thư viện");
				mainApp.setSize(600, 460);
				tfUsername.setText("");
				tfPassword.setText("");
				tfRePassword.setText(""); 
			}
		});
		
		btnDangky.addActionListener(this);
		
		panel1.add(btnDangky);
		panel1.add(btnDanhNhap);
		panel1.add(btnThoat);
		
		///
		add(lbDangky);
		add(panel);
		add(panel1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
