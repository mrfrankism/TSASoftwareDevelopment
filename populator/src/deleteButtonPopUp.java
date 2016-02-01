import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class deleteButtonPopUp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void newFrame(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deleteButtonPopUp window = new deleteButtonPopUp();
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
	public deleteButtonPopUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 448, 196);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSwitchToThe = new JLabel("Switch to the students tab to use this function...");
		lblSwitchToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwitchToThe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSwitchToThe.setBounds(12, 45, 408, 53);
		frame.getContentPane().add(lblSwitchToThe);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(170, 111, 97, 25);
		frame.getContentPane().add(btnClose);
	}

}
