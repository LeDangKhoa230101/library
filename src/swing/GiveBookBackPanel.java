package swing;

import java.awt.BorderLayout;
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

public class GiveBookBackPanel extends JPanel {
	JLabel lbMaSach, lbSoluong;
	JTextField tfMaSach, tfSoluong;
	JButton btnTrasach;
	
	GiveBookBackPanel(MainApp mainApp) {
		setBackground(Color.CYAN);
		setLayout(new FlowLayout());
		setBorder(new EmptyBorder(20, 0, 0, 0));
		
		///
		JButton btnBack = new JButton("Quay lại");
		btnBack.setBackground(new Color(0, 128, 255));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBorderPainted(false); 
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "loan");
				mainApp.setTitle("Mượn trả sách");
				mainApp.setSize(600, 300);
			}
		});
		
		///
		JLabel lbAdd = new JLabel("Trả sách");
		lbAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lbAdd.setFont(new Font("Arial", Font.BOLD, 18));
		lbAdd.setForeground(Color.BLUE);
		lbAdd.setPreferredSize(new Dimension(500, 40));
		
		///
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setLayout(new GridLayout(2, 1));
		panel.setPreferredSize(new Dimension(300, 50));
		
		lbMaSach = new JLabel("Mã sách:");
		lbMaSach.setFont(new Font("Arial", Font.PLAIN, 14));
		lbSoluong = new JLabel("Số lượng:");
		lbSoluong.setFont(new Font("Arial", Font.PLAIN, 14));
		
		tfMaSach = new JTextField();
		tfMaSach.setBorder(BorderFactory.createCompoundBorder(tfMaSach.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfMaSach.setFont(new Font("Arial", Font.PLAIN, 14));
		tfSoluong = new JTextField();
		tfSoluong.setBorder(BorderFactory.createCompoundBorder(tfSoluong.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfSoluong.setFont(new Font("Arial", Font.PLAIN, 14));
		
		panel.add(lbMaSach);
		panel.add(tfMaSach);
		panel.add(lbSoluong);
		panel.add(tfSoluong);
		
		///
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.CYAN);
		panel2.setPreferredSize(new Dimension(500, 50));
		
		btnTrasach = new JButton("Trả sách");
		btnTrasach.setBackground(new Color(255, 128, 64));
		btnTrasach.setForeground(Color.WHITE);
		btnTrasach.setBorderPainted(false);
		btnTrasach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panel2.add(btnTrasach);
		
		///
		add(btnBack);
		add(lbAdd);
		add(panel);
		add(panel2);
	}
	
}
