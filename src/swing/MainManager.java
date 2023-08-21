package swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.Dao;
import model.Book;

public class MainManager extends JPanel {
	JButton btnManaBook, btnLoan, btnThongke;
	JLabel lbTongSach, lbSachCoSan, lbDangMuon;
	int sachCoSan = 0;
	int dangMuon = 0;
	
	Connection conn;
	static Dao dao;
	
	MainManager(MainApp mainApp) {
		connectToDatabase();
		dao = new Dao(conn);
		
		setLayout(new GridLayout(3, 0));
		setBackground(Color.CYAN);
		setBorder(new EmptyBorder(20, 0, 0, 0));
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setBackground(Color.CYAN);
		
		///
		btnManaBook = new JButton("Quản lý sách");
		btnManaBook.setBackground(new Color(0, 128, 255));
		btnManaBook.setForeground(Color.WHITE);
		btnManaBook.setBorderPainted(false); 
		btnManaBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnManaBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "book manager");
				mainApp.setTitle("Quản lý sách");
				mainApp.setSize(600, 300);
			}
		});
		
		btnLoan = new JButton("Mượn trả sách");
		btnLoan.setBackground(new Color(0, 255, 0));
		btnLoan.setForeground(Color.WHITE);
		btnLoan.setBorderPainted(false); 
		btnLoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLoan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "loan");
				mainApp.setTitle("Mượn trả sách");
				mainApp.setSize(600, 300);
			}
		});
		
		btnThongke = new JButton("Thống kê");
		btnThongke.setBackground(new Color(128, 0, 255));
		btnThongke.setForeground(Color.WHITE);
		btnThongke.setBorderPainted(false); 
		btnThongke.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThongke.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "statistica");
				mainApp.setTitle("Thống kê");
				mainApp.setSize(600, 300);
			}
		});
		
		panel1.add(btnManaBook);
		panel1.add(btnLoan);
		panel1.add(btnThongke);
		
		///
		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		String logoPath = "https://i.pinimg.com/564x/77/a6/6c/77a66c1394b8eb1ef16fc79bf6662fe0.jpg";
		try {
			URL imageUrl = new URL(logoPath);
			BufferedImage image = ImageIO.read(imageUrl);
			
			int width = 150;
			int height = 150;
			Image scalImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon logo = new ImageIcon(scalImage);
			label.setIcon(logo); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		///
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0, 3));
		panel2.setBackground(Color.CYAN);
		
		int totalBooks = dao.totalBook();
		int bookAvailable = dao.bookIsAvailable();
		int bookBorrowed = dao.bookBorrowed();
		
		lbTongSach = new JLabel("Tổng số sách: " + totalBooks);
		lbSachCoSan = new JLabel("Sách có sẵn: " + bookAvailable);
		lbDangMuon = new JLabel("Sách đang mượn: " + bookBorrowed);
		
		lbTongSach.setFont(new Font("Arial", Font.PLAIN, 15)); 
		lbSachCoSan.setFont(new Font("Arial", Font.PLAIN, 15)); 
		lbDangMuon.setFont(new Font("Arial", Font.PLAIN, 15)); 
		
		lbTongSach.setHorizontalAlignment(SwingConstants.CENTER);
		lbSachCoSan.setHorizontalAlignment(SwingConstants.CENTER);
		lbDangMuon.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel2.add(lbTongSach);
		panel2.add(lbSachCoSan);
		panel2.add(lbDangMuon);
		
		///
		add(panel1);
		add(label);
		add(panel2);
	}
	
	private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false", "root", "ledangkhoa2301");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
