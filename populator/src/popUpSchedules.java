import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class popUpSchedules {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void newFrame(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					popUpSchedules window = new popUpSchedules();
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
	public popUpSchedules() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 395, 213);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("You must be in the schedules page to use\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 30, 377, 55);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblThisFunction = new JLabel("this function...");
		lblThisFunction.setHorizontalAlignment(SwingConstants.CENTER);
		lblThisFunction.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThisFunction.setBounds(93, 77, 189, 16);
		frame.getContentPane().add(lblThisFunction);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnClose.setBounds(138, 123, 97, 25);
		frame.getContentPane().add(btnClose);
	}
}
