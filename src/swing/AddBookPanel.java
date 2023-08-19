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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.Dao;
import model.Book;

public class AddBookPanel extends JPanel {

	JLabel lbTitle, lbAuthor, lbGenre, lbYear, lbQuantity, lbAvailable;
	JTextField tfTitle, tfAuthor, tfGenre, tfYear, tfQuantity, tfAvailable;
	JButton btnAdd;
	
	Connection conn;
	Dao dao;

	AddBookPanel(MainApp mainApp) {
		connectToDatabase();
		dao = new Dao(conn);
		
		setBackground(Color.CYAN);
		setLayout(new FlowLayout());
		setBorder(new EmptyBorder(20, 0, 0, 0));
		
		JButton btnBack = new JButton("Quay lại");
		btnBack.setBackground(new Color(0, 128, 255));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBorderPainted(false); 
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "book manager");
				mainApp.setTitle("Quản lý sách");
				mainApp.setSize(600, 300);
				tfTitle.setText("");
				tfAuthor.setText("");
				tfGenre.setText("");
				tfYear.setText("");
				tfQuantity.setText("");
				tfAvailable.setText("");
			}
		});
		
		///
		JLabel lbAdd = new JLabel("Thêm sách");
		lbAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lbAdd.setFont(new Font("Arial", Font.BOLD, 18));
		lbAdd.setForeground(Color.BLUE);
		lbAdd.setPreferredSize(new Dimension(500, 40));

		///
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setLayout(new GridLayout(6, 1));
		panel.setPreferredSize(new Dimension(490, 140));

		lbTitle = new JLabel("Tên sách:");
		lbTitle.setFont(new Font("Arial", Font.PLAIN, 14));
		lbAuthor = new JLabel("Tác giả:");
		lbAuthor.setFont(new Font("Arial", Font.PLAIN, 14));
		lbGenre = new JLabel("Thể loại:");
		lbGenre.setFont(new Font("Arial", Font.PLAIN, 14));
		lbYear = new JLabel("Năm xuất bản:");
		lbYear.setFont(new Font("Arial", Font.PLAIN, 14));
		lbQuantity = new JLabel("Số lượng:");
		lbQuantity.setFont(new Font("Arial", Font.PLAIN, 14));	
		lbAvailable = new JLabel("Có sẵn:");
		lbAvailable.setFont(new Font("Arial", Font.PLAIN, 14));	

		tfTitle = new JTextField();
		tfTitle.setBorder(BorderFactory.createCompoundBorder(tfTitle.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfAuthor = new JTextField();
		tfAuthor.setBorder(BorderFactory.createCompoundBorder(tfAuthor.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfGenre = new JTextField();
		tfGenre.setBorder(BorderFactory.createCompoundBorder(tfGenre.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfYear = new JTextField();
		tfYear.setBorder(BorderFactory.createCompoundBorder(tfYear.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfQuantity = new JTextField();
		tfQuantity.setBorder(BorderFactory.createCompoundBorder(tfQuantity.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfAvailable = new JTextField();
		tfAvailable.setBorder(BorderFactory.createCompoundBorder(tfAvailable.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));

		panel.add(lbTitle);
		panel.add(tfTitle);
		panel.add(lbAuthor);
		panel.add(tfAuthor);
		panel.add(lbGenre);
		panel.add(tfGenre);
		panel.add(lbYear);
		panel.add(tfYear);
		panel.add(lbQuantity);
		panel.add(tfQuantity);
		panel.add(lbAvailable);
		panel.add(tfAvailable);

		///
		btnAdd = new JButton("Thêm sách");
		btnAdd.setBackground(new Color(0, 128, 255));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBorderPainted(false);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = tfTitle.getText();
				String author = tfAuthor.getText();
				String genre = tfGenre.getText();
				String year = tfYear.getText();
				String quantity = tfQuantity.getText();
				String available = tfAvailable.getText();
				
				if(title.isEmpty() || author.isEmpty()
						|| genre.isEmpty() || year.isEmpty() || 
						quantity.isEmpty() || available.isEmpty()) {
					JOptionPane.showMessageDialog(btnAdd, "Hãy nhập tất cả các ô!");
				} else { 
					dao.addBook(title, author, genre, Integer.parseInt(year), Integer.parseInt(quantity), Integer.parseInt(available));
					JOptionPane.showMessageDialog(btnAdd, "Đã thêm sách thành công!");
					BookManagerPanel.getBookList();
				}
			}
		});
		
		///
		add(btnBack);
		add(lbAdd);
		add(panel);
		add(btnAdd);
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
