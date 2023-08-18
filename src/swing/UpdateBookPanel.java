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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class UpdateBookPanel extends JPanel {

	JLabel lbTitle, lbAuthor, lbGenre, lbYear, lbQuantity;
	JTextField tfTitle, tfAuthor, tfGenre, tfYear, tfQuantity;
	JButton btnAdd;

	UpdateBookPanel(MainApp mainApp) {
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
			}
		});
		
		///
		JLabel lbAdd = new JLabel("Cập nhật sách");
		lbAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lbAdd.setFont(new Font("Arial", Font.BOLD, 18));
		lbAdd.setForeground(Color.BLUE);
		lbAdd.setPreferredSize(new Dimension(410, 40));

		///
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setLayout(new GridLayout(5, 1));
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
		
		///
		add(btnBack);
		add(lbAdd);
		add(panel);
		add(btnAdd);
	}

}
