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
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import database.Dao;
import model.Book;
import model.UserSeccion;

public class LoanPanel extends JPanel {

	static DefaultTableModel tableModel;
	JTable table;
	JButton btnMuon, btnTra;

	Connection conn;
	static Dao dao;

	LoanPanel(MainApp mainApp) {
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
		tableModel = new DefaultTableModel();
		tableModel.addColumn("ID");
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
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

		/// width cố định
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(8);
		table.getColumnModel().getColumn(6).setPreferredWidth(8);

		// Tạo TableCellEditor tùy chỉnh
		TableCellEditor nonEditableCellEditor = new DefaultCellEditor(new JTextField()) {
			@Override
			public boolean isCellEditable(EventObject e) {
				return false;
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			if (i != 6) { // Loại trừ cột checkbox (cột 6)
				table.getColumnModel().getColumn(i).setCellEditor(nonEditableCellEditor);
			}
		}

		/// checkbox
		table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
			@Override
			public boolean isCellEditable(EventObject e) {
				return true;
			}
		});
		table.getColumnModel().getColumn(6).setCellRenderer(new TableCellRenderer() {
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

		getBookAvailable();

		///
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.CYAN);

		btnMuon = new JButton("Mượn sách");
		btnMuon.setBackground(new Color(0, 128, 255));
		btnMuon.setForeground(Color.WHITE);
		btnMuon.setBorderPainted(false);
		btnMuon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMuon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int currentUser = UserSeccion.getCurrentUserId();

				int rowCount = tableModel.getRowCount();
				List<Integer> rowsToBorrow = new ArrayList<Integer>();
				List<Integer> rowIndex = new ArrayList<Integer>();

				for (int i = 0; i < rowCount; i++) {
					boolean isSelected = (boolean) tableModel.getValueAt(i, 6);
					if (isSelected) {
						int bookId = (int) tableModel.getValueAt(i, 0);
						rowsToBorrow.add(bookId);
						rowIndex.add(i);
					}
				}

				if(!rowsToBorrow.isEmpty()) {
					// thực hiện mượn sách
					dao.borrowBooks(currentUser, rowsToBorrow);
					
					for (Integer row : rowIndex) {
						int qty = (int) tableModel.getValueAt(row, 5);
						tableModel.setValueAt(qty - 1, row, 5);
					}

					// Lấy danh sách các cuốn sách đã chọn
					List<Book> selectedBooks = new ArrayList<>();
					for (Integer row : rowIndex) {
						String title = (String) tableModel.getValueAt(row, 1);
						String author = (String) tableModel.getValueAt(row, 2);
						String genre = (String) tableModel.getValueAt(row, 3);
						int year = (int) tableModel.getValueAt(row, 4);
						
						selectedBooks.add(new Book(title, author, genre, year));
					}
					OrderBookPanel orderPanel = new OrderBookPanel(selectedBooks);
					JOptionPane.showConfirmDialog(btnMuon, orderPanel, "Các sách đã mượn",
		                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		btnTra = new JButton("Trả sách");
		btnTra.setBackground(new Color(255, 128, 64));
		btnTra.setForeground(Color.WHITE);
		btnTra.setBorderPainted(false);
		btnTra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GiveBookBackPanel.getBorrowByUser();
				
				CardLayout c = (CardLayout) mainApp.panelTotal.getLayout();
				c.show(mainApp.panelTotal, "give book back");
				mainApp.setTitle("Các sách đã mượn");
				mainApp.setSize(600, 300);
				
			}
		});

		panel2.add(btnMuon);
		panel2.add(btnTra);

		///
		add(panel1);
		add(scrollPane);
		add(panel2);
	}

	public static void getBookAvailable() {
		tableModel.setRowCount(0);
		List<Book> books = dao.getBookAvailable();
		for (Book b : books) {
			Object[] data = { b.getBookID(), b.getTitle(), b.getAuthor(), b.getGenre(), b.getYear(), b.getQuantity(), false };
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
