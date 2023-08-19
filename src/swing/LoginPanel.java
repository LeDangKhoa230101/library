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
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.Dao;

public class LoginPanel extends JPanel implements ActionListener {
	JLabel lbDangnhap, lbUsername, lbPassword;
	JTextField tfUsername;
	JPasswordField tfPassword;
	
	JButton btnDanhNhap, btnDangKy, btnThoat;
	
	Connection conn;
	Dao dao;
	
	LoginPanel(MainApp mainApp) {
		connectToDatabase();
		dao = new Dao(conn);
		
		setBackground(Color.CYAN);
		setLayout(new FlowLayout());
		setBorder(new EmptyBorder(40, 0, 0, 0));
		
		///
		lbDangnhap = new JLabel("Đăng nhập");
		lbDangnhap.setFont(new Font("Arial", Font.BOLD, 18));
		lbDangnhap.setForeground(Color.BLUE);
		
		///
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setPreferredSize(new Dimension(480, 50));
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
		
		panel.add(lbUsername);
		panel.add(tfUsername);
		panel.add(lbPassword);
		panel.add(tfPassword);
		
		///
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setBackground(Color.CYAN);
		
		btnDanhNhap = new JButton("Đăng nhập");
		btnDanhNhap.setBackground(new Color(0, 128, 255));
		btnDanhNhap.setForeground(Color.WHITE);
		btnDanhNhap.setBorderPainted(false); 
		btnDanhNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDanhNhap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = tfUsername.getText();
				String password = new String(tfPassword.getPassword());
				
				if(dao.login(username, password)) {
					JOptionPane.showMessageDialog(btnDanhNhap, "Đăng nhập thành công!");
					CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
					c.show(mainApp.panelTotal, "main manager");
					mainApp.setTitle("Quản lý thư viện " + "(" + username + ")");
					mainApp.setSize(600, 400);
					tfUsername.setText("");
					tfPassword.setText("");
				} else {
					JOptionPane.showMessageDialog(btnDanhNhap, "Sai tài khoản hoặc mật khẩu!");
				}
			}
		});
		
		btnDangKy = new JButton("Đăng ký");
		btnDangKy.setBackground(new Color(0, 128, 255));
		btnDangKy.setForeground(Color.WHITE);
		btnDangKy.setBorderPainted(false); 
		btnDangKy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDangKy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "register");
				mainApp.setTitle("Đăng ký");
				mainApp.setSize(600, 260);
				tfUsername.setText("");
				tfPassword.setText("");
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
			}
		});
		
		btnDanhNhap.addActionListener(this);
		
		panel1.add(btnDanhNhap);
		panel1.add(btnDangKy);
		panel1.add(btnThoat);
		
		///
		add(lbDangnhap);
		add(panel);
		add(panel1);
	}
	
	private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "ledangkhoa2301");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
