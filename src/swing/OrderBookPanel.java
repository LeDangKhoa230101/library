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

public class OrderBookPanel extends JPanel {
	DefaultTableModel tableModel;
	JTable table;

	OrderBookPanel(MainApp mainApp) {
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
				c.show(mainApp.panelTotal, "loan");
				mainApp.setTitle("Mượn trả sách");
				mainApp.setSize(600, 300);
			}
		});

		panel1.add(btnBack);

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

		Object[] data = { "23", "4343", "343", "33223", "2323", false };
		Object[] data1 = { "23", "4343", "343", "33223", "2323", false };
		Object[] data2 = { "23", "4343", "343", "33223", "2323", false };
		Object[] data3 = { "23", "4343", "343", "33223", "2323", false };
		tableModel.addRow(data);
		tableModel.addRow(data1);
		tableModel.addRow(data2);
		tableModel.addRow(data3);

		///
		add(panel1);
		add(lbAdd);
		add(scrollPane);
	}

}
