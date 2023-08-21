package swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import database.Dao;
import model.Book;

public class StatisticalPanel extends JPanel {
	static DefaultTableModel tableModel;
	JTable table;
	
	Connection conn;
	static Dao dao;

	StatisticalPanel(MainApp mainApp) {
		connectToDatabase();
		dao = new Dao(conn);
		
		setBackground(Color.CYAN);
		setLayout(new FlowLayout());

		///
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 240, 4));
		panel1.setBackground(Color.CYAN);

		JButton btnBack = new JButton("Quay lại");
		btnBack.setBackground(new Color(0, 128, 255));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBorderPainted(false);
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "main manager");
				mainApp.setTitle("Quản lý thư viện");
				mainApp.setSize(600, 400);
			}
		});

		panel1.add(btnBack);

		///
		JLabel lbAdd = new JLabel("Thống kê");
		lbAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lbAdd.setFont(new Font("Arial", Font.BOLD, 18));
		lbAdd.setForeground(Color.BLUE);
		lbAdd.setPreferredSize(new Dimension(500, 40));

		///
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Genre");
		tableModel.addColumn("Quantity");

		table = new JTable(tableModel);
		/// căn giữa, màu chữ, font chữ
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setForeground(Color.BLACK);
		Font font = new Font("Arial", Font.PLAIN, 14);
		centerRenderer.setFont(font);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		table.setBackground(Color.WHITE);
		table.getTableHeader().setBackground(Color.WHITE);
		table.setSelectionBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(550, 64));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(this.getBackground());

		getStatis();

		///
		add(panel1);
		add(lbAdd);
		add(scrollPane);
	}
	
	public static void getStatis() {
		tableModel.setRowCount(0);
		List<Book> books = dao.statissticalGenre();
		for(Book b : books) {
			Object[] data = { b.getGenre(), b.getQuantity() };
			tableModel.addRow(data);
		}
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
