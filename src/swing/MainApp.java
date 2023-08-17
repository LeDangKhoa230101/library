package swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainApp extends JFrame implements ActionListener {
	JButton btnOpen;
	
	JPanel panelTotal, loginPanel;
	
	public MainApp() {
		setTitle("Ứng dụng quản lý thư viện");
		setSize(600, 460);
        getContentPane().setBackground(Color.CYAN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panelTotal = new JPanel();
		panelTotal.setLayout(new CardLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.CYAN);
		panel.setBorder(new EmptyBorder(40, 0, 0, 0));
		
		///
		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		String logoPath = "https://i.pinimg.com/564x/77/a6/6c/77a66c1394b8eb1ef16fc79bf6662fe0.jpg";
		try {
			URL imageUrl = new URL(logoPath);
			BufferedImage image = ImageIO.read(imageUrl);
			
			int width = 140;
			int height = 140;
			Image scalImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon logo = new ImageIcon(scalImage);
			label.setIcon(logo); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		///
		JLabel label2 = new JLabel("Ứng dụng quản lý thư viện");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setPreferredSize(new Dimension(500, 30));
		label2.setFont(new Font("Arial", Font.PLAIN, 16)); 
		
		///
		btnOpen = new JButton("Đăng nhập ứng dụng");
		btnOpen.setBackground(new Color(0, 128, 255));
		btnOpen.setForeground(Color.WHITE);
		btnOpen.setBorderPainted(false); 
		btnOpen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOpen.addActionListener(this);
		
		///
		panel.add(label);
		panel.add(label2);
		panel.add(btnOpen);
		
		///
		loginPanel = new LoginPanel();
		
		///
		panelTotal.add(panel, "main");
		panelTotal.add(loginPanel, "login");
		
		///
		getContentPane().add(panelTotal); 
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getSource();
		if(btn == btnOpen) {
			CardLayout c = (CardLayout) panelTotal.getLayout();
			c.show(panelTotal, "login");
			setTitle("Đăng nhập ứng dụng");
			setSize(600, 300);
		}
	}
	
	public static void main(String[] args) {
		new MainApp();
	}

	
	
}