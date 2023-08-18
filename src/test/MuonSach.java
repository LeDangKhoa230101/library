package test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MuonSach {
	private JFrame frame;
	private DefaultTableModel availableBooksTableModel;
	private JTable availableBooksTable;
	private JButton borrowButton;
	private DefaultTableModel borrowedBooksTableModel;
	private JTable borrowedBooksTable;

	public MuonSach() {
		frame = new JFrame("Library App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		availableBooksTableModel = new DefaultTableModel(
				new Object[][] { { "Sách 1", "Tác giả 1", 5, false }, { "Sách 2", "Tác giả 2", 3, false },
						{ "Sách 3", "Tác giả 3", 7, false } },
				new String[] { "Tiêu đề", "Tác giả", "Số lượng", "Mượn sách" });

		availableBooksTable = new JTable(availableBooksTableModel);

		JScrollPane availableBooksScrollPane = new JScrollPane(availableBooksTable);
		frame.add(availableBooksScrollPane, BorderLayout.CENTER);

		borrowButton = new JButton("Mượn sách");
		borrowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createBorrowedBooksTable();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(borrowButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}

	private void createBorrowedBooksTable() {
		borrowedBooksTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Tiêu đề", "Tác giả", "Số lượng" });

		for (int row = 0; row < availableBooksTableModel.getRowCount(); row++) {
			Boolean isSelected = (Boolean) availableBooksTableModel.getValueAt(row, 3);
			if (isSelected) {
				Object[] rowData = new Object[] { availableBooksTableModel.getValueAt(row, 0),
						availableBooksTableModel.getValueAt(row, 1), 
						availableBooksTableModel.getValueAt(row, 2) };
				borrowedBooksTableModel.addRow(rowData);
			}
		}

		borrowedBooksTable = new JTable(borrowedBooksTableModel);

		JFrame borrowFrame = new JFrame("Hóa đơn mượn sách");
		borrowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		borrowFrame.setLayout(new BorderLayout());

		JScrollPane borrowedBooksScrollPane = new JScrollPane(borrowedBooksTable);
		borrowFrame.add(borrowedBooksScrollPane, BorderLayout.CENTER);

		borrowFrame.pack();
		borrowFrame.setVisible(true);
	}
}
