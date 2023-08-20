package swing;

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
import javax.swing.JTextField;

import database.Dao;

public class UpdateBookPanel extends JPanel {

	JLabel lbTitle, lbAuthor, lbGenre, lbYear, lbQuantity;
	JTextField tfTitle, tfAuthor, tfGenre, tfYear, tfQuantity;
	JButton btnAdd;
	JPanel panel;
	
	Connection conn;
	Dao dao;

	public UpdateBookPanel(String title, String author, String genre, String year, String quantity) {
		connectToDatabase();
		dao = new Dao(conn);
		
		setLayout(new FlowLayout());
        setBackground(Color.CYAN);
        
		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setLayout(new GridLayout(5, 2));
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

		tfTitle = new JTextField(title);
		tfTitle.setBorder(
				BorderFactory.createCompoundBorder(tfTitle.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfAuthor = new JTextField(author);
		tfAuthor.setBorder(
				BorderFactory.createCompoundBorder(tfAuthor.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfGenre = new JTextField(genre);
		tfGenre.setBorder(
				BorderFactory.createCompoundBorder(tfGenre.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfYear = new JTextField(year);
		tfYear.setBorder(
				BorderFactory.createCompoundBorder(tfYear.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfQuantity = new JTextField(quantity);
		tfQuantity.setBorder(BorderFactory.createCompoundBorder(tfQuantity.getBorder(),
				BorderFactory.createEmptyBorder(0, 5, 0, 5)));

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

		///
		btnAdd = new JButton("Cập nhật sách");
		btnAdd.setBackground(new Color(0, 128, 255));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBorderPainted(false);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String updatedTitle = tfTitle.getText();
	        	String updatedAuthor = tfAuthor.getText();
	        	String updatedGenre = tfGenre.getText();
	        	String updatedYear = tfYear.getText();
	        	String updatedQuantity = tfQuantity.getText();
	        	
	        	if(updatedTitle.isEmpty() || updatedAuthor.isEmpty() 
	        			|| updatedGenre.isEmpty() 
	        			|| updatedYear.isEmpty() 
	        			|| updatedQuantity.isEmpty()) {
	        		JOptionPane.showMessageDialog(btnAdd, "Không để trống!");
	        	} else {
	        		dao.updateBook(updatedTitle, updatedAuthor, updatedGenre, updatedYear, updatedQuantity); 
	        		JOptionPane.showMessageDialog(btnAdd, "Cập nhật thành công!");
	        		
	        		BookManagerPanel.getBookList();
	        	}
			}
		});
		
		///
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
