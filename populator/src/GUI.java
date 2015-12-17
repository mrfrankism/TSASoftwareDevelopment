import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class GUI {

	static JFrame frame;
	static JTextArea textArea;
	static JProgressBar progressBar;
	private JButton btnNewButton;
	private JMenuBar menuBar;
	private JButton btnNewButton_1;
	private JPopupMenu popupMenu;
	private JLabel lblNewLabel;
	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(new Dimension(752, 502));
		//frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textArea = new JTextArea();
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		progressBar = new JProgressBar();
		progressBar.setMaximum(pop.size - 1);
		progressBar.setMinimum(0);
		frame.getContentPane().add(progressBar, BorderLayout.NORTH);
		
		btnNewButton = new JButton("New button");
		frame.getContentPane().add(btnNewButton, BorderLayout.WEST);
		
		popupMenu = new JPopupMenu();
		addPopup(btnNewButton, popupMenu);
		
		lblNewLabel = new JLabel("New label");
		popupMenu.add(lblNewLabel);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		btnNewButton_1 = new JButton("New button");
		menuBar.add(btnNewButton_1);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
