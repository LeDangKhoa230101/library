package swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class StatisticalPanel extends JPanel {
	DefaultTableModel tableModel;
	JTable table;

	StatisticalPanel(MainApp mainApp) {
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

		Object[] data = { "23", "4343" };
		Object[] data1 = { "23", "4343" };
		Object[] data2 = { "23", "4343" };
		Object[] data3 = { "23", "4343" };
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
