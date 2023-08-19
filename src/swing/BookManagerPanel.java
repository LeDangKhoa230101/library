package swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import database.Dao;
import model.Book;

public class BookManagerPanel extends JPanel {

	DefaultTableModel tableModel;
	JTable table;
	JButton btnRemove, btnAdd, btnUpdate, btnSearch;
	JTextField tfSearch;
	
	Connection conn;
	Dao dao;

	BookManagerPanel(MainApp mainApp) {
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

		///
		tfSearch = new JTextField();
		tfSearch.setPreferredSize(new Dimension(200, 26));
		tfSearch.setBorder(
				BorderFactory.createCompoundBorder(tfSearch.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		tfSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		String placeholder = "Tìm kiếm ...";
		tfSearch.setText(placeholder);
		tfSearch.setForeground(Color.GRAY);
		// Xử lý sự kiện focus để hiển thị/ẩn placeholder
		tfSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfSearch.getText().equals(placeholder)) {
					tfSearch.setText("");
					tfSearch.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfSearch.getText().length() == 0) {
					tfSearch.setText(placeholder);
					tfSearch.setForeground(Color.GRAY);
				}
			}
		});
		
		/// xử lý sự kiên khi tfSearch rỗng
		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateStatusLabel();
			}
			private void updateStatusLabel() {
				if(tfSearch.getText().isEmpty()) {
					tableModel.setRowCount(0); 
					List<Book> books = dao.getBooks();
					for(Book b : books) {
						Object[] data = { b.getTitle(), b.getAuthor(), b.getGenre(), b.getYear(), b.getQuantity(), false };
						tableModel.addRow(data);
					}
				}
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateStatusLabel();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateStatusLabel();
			}
		});

		panel1.add(btnBack);
		panel1.add(tfSearch);

		///
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Title");
		tableModel.addColumn("Author");
		tableModel.addColumn("Genre");
		tableModel.addColumn("Year");
		tableModel.addColumn("Quantity");
		tableModel.addColumn("");

		table = new JTable(tableModel);
		/// căn giữa, màu chữ, font chữ
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setForeground(Color.BLACK);
		Font font = new Font("Arial", Font.PLAIN, 14);
		centerRenderer.setFont(font);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

		/// width cố định
		table.getColumnModel().getColumn(0).setPreferredWidth(140);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(8);

		/// checkbox
		table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		table.getColumnModel().getColumn(5).setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JCheckBox checkBox = new JCheckBox();
				checkBox.setSelected((boolean) value);
				return checkBox;
			}
		});

		table.setBackground(Color.WHITE);
		table.getTableHeader().setBackground(Color.WHITE);
		table.setSelectionBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(566, 80));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(this.getBackground());

		List<Book> books = dao.getBooks();
		for(Book b : books) {
			Object[] data = { b.getTitle(), b.getAuthor(), b.getGenre(), b.getYear(), b.getQuantity(), false };
			tableModel.addRow(data);
		}

		///
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.CYAN);

		btnAdd = new JButton("Thêm sách");
		btnAdd.setBackground(new Color(0, 128, 255));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBorderPainted(false);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "add book");
				mainApp.setTitle("Thêm sách");
				mainApp.setSize(600, 360);
			}
		});

		btnUpdate = new JButton("Sữa");
		btnUpdate.setBackground(new Color(255, 128, 64));
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "update book");
				mainApp.setTitle("Cập nhật sách");
				mainApp.setSize(600, 360);
			}
		});

		btnRemove = new JButton("Xóa");
		btnRemove.setBackground(new Color(0, 255, 0));
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setBorderPainted(false);
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBackground(new Color(128, 0, 255));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBorderPainted(false);
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);
				String keyword = tfSearch.getText();
				List<Book> search = dao.searchBook(keyword);
				
				for(Book b : search) {
					Object[] data = { b.getTitle(), b.getAuthor(), b.getGenre(), b.getYear(), b.getQuantity(), false };
					tableModel.addRow(data);
				}
			}
		});
		
		panel2.add(btnAdd);
		panel2.add(btnUpdate);
		panel2.add(btnRemove);
		panel2.add(btnSearch);

		///
		add(panel1);
		add(scrollPane);
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
