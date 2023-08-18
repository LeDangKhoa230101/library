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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class LoanPanel extends JPanel {

	DefaultTableModel tableModel;
	JTable table;
	JButton btnMuon, btnTra;

	LoanPanel(MainApp mainApp) {
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

		Object[] data = { "23", "4343", "343", "33223", "2323", false };
		Object[] data1 = { "23", "4343", "343", "33223", "2323", false };
		Object[] data2 = { "23", "4343", "343", "33223", "2323", false };
		Object[] data3 = { "23", "4343", "343", "33223", "2323", false };
		tableModel.addRow(data);
		tableModel.addRow(data1);
		tableModel.addRow(data2);
		tableModel.addRow(data3);
		tableModel.addRow(data3);
		tableModel.addRow(data3);
		tableModel.addRow(data3);
		tableModel.addRow(data3);

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
				 JOptionPane.showMessageDialog(btnMuon, "Bạn đã mượn sách!");
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
				
			}
		});

		panel2.add(btnMuon);
		panel2.add(btnTra);

		///
		add(panel1);
		add(scrollPane);
		add(panel2);
	}

}
