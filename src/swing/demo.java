package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Insets;

public class demo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demo window = new demo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public demo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(new Color(128, 255, 0));
		lblNewLabel.setBackground(new Color(0, 128, 0));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setPreferredSize(new Dimension(400, 20));
		panel.add(lblNewLabel); 
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setInheritsPopupMenu(true);
		btnNewButton.setIgnoreRepaint(true);
		btnNewButton.setDefaultCapable(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 128));
		panel.add(btnNewButton);
	}

}
