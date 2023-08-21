package swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import model.Book;

public class OrderBookPanel extends JPanel {
	DefaultTableModel tableModel;
	JTable table;

	OrderBookPanel(List<Book> selectedBooks) {
		setBackground(Color.CYAN);
		setLayout(new GridLayout(2, 1));

		///
		JLabel lbAdd = new JLabel("Hóa đơn mượn sách");
		lbAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lbAdd.setFont(new Font("Arial", Font.BOLD, 18));
		lbAdd.setForeground(Color.BLUE);
		lbAdd.setPreferredSize(new Dimension(500, 40));

		///
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Title");
		tableModel.addColumn("Author");
		tableModel.addColumn("Genre");
		tableModel.addColumn("Year");

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

		/// width cố định
		table.getColumnModel().getColumn(0).setPreferredWidth(140);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);

		table.setBackground(Color.WHITE);
		table.getTableHeader().setBackground(Color.WHITE);
		table.setSelectionBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(566, 48));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(this.getBackground());

		tableModel.setRowCount(0);
		for (Book book : selectedBooks) {
			Object[] rowData = { book.getTitle(), book.getAuthor(), book.getGenre(), book.getYear() };
			tableModel.addRow(rowData);
		}

		///
		add(lbAdd);
		add(scrollPane);
	}

}
