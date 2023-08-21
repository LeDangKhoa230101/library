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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import database.Dao;
import model.Book;
import model.UserSeccion;

public class GiveBookBackPanel extends JPanel {
	JButton btnTrasach;
	
	static DefaultTableModel tableModel;
	JTable table;
	JButton btnMuon, btnTra;

	Connection conn;
	static Dao dao;
	
	GiveBookBackPanel(MainApp mainApp) {
		connectToDatabase();
		dao = new Dao(conn);
		
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
		JLabel lbAdd = new JLabel("Các sách đã mượn");
		lbAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lbAdd.setFont(new Font("Arial", Font.BOLD, 18));
		lbAdd.setForeground(Color.BLUE);
		lbAdd.setPreferredSize(new Dimension(500, 40));
		
		///
		tableModel = new DefaultTableModel();
		tableModel.addColumn("ID");
		tableModel.addColumn("Title");
		tableModel.addColumn("Author");
		tableModel.addColumn("Genre");
		tableModel.addColumn("Year");
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
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(8);

		// Tạo TableCellEditor tùy chỉnh
		TableCellEditor nonEditableCellEditor = new DefaultCellEditor(new JTextField()) {
			@Override
			public boolean isCellEditable(EventObject e) {
				return false;
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			if (i != 5) { // Loại trừ cột checkbox (cột 5)
				table.getColumnModel().getColumn(i).setCellEditor(nonEditableCellEditor);
			}
		}

		/// checkbox
		table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
			@Override
			public boolean isCellEditable(EventObject e) {
				return true;
			}
		});
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
		
		getBorrowByUser();
		
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
		btnTrasach.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowCount = tableModel.getRowCount();
				List<Integer> loanIdsToReturn  = new ArrayList<Integer>();
				
				for(int i = 0; i < rowCount; i++) {
					boolean isSelected = (boolean) tableModel.getValueAt(i, 5);
					if(isSelected) {
						int loanId = (int) tableModel.getValueAt(i, 0);
						loanIdsToReturn .add(loanId);
					}
				} 
				
				// Trả sách và cập nhật trạng thái
				if(!loanIdsToReturn .isEmpty()) {
					dao.returnBooks(loanIdsToReturn );
					JOptionPane.showMessageDialog(btnTrasach, "Trả sách thành công!");
					
					getBorrowByUser();
				}
			}
		});
		
		panel2.add(btnTrasach);
		
		///
		add(btnBack);
		add(lbAdd);
		add(scrollPane);
		add(panel2);
	}
	
	public static void getBorrowByUser() {
		int currentUser = UserSeccion.getCurrentUserId();
		tableModel.setRowCount(0);
		List<Book> books = dao.getBorrowedBooks(currentUser);
		for (Book b : books) {
			Object[] data = { b.getLoanId(), b.getTitle(), b.getAuthor(), b.getGenre(), b.getYear(), false };
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
