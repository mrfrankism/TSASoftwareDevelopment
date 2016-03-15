import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class couldNotConnectPopup {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void newFrame(String[] args) {//make the arguments the ip address that the user is trying to connect to
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					couldNotConnectPopup window = new couldNotConnectPopup();
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
	public couldNotConnectPopup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 221);
		frame.getContentPane().setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
}
		});
		btnClose.setBounds(12, 136, 97, 25);
		frame.getContentPane().add(btnClose);
		
		JButton btnTryAgain = new JButton("Try Again");
		btnTryAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pop.connect(); //try to reconnect using the previous settings
				frame.dispose();
			}
		});
		btnTryAgain.setBounds(214, 136, 97, 25);
		frame.getContentPane().add(btnTryAgain);
		
		JTextArea txtrText = new JTextArea();
		txtrText.setText("-----Current Settings-----\n");
		txtrText.append("IP: "+ pop.IP + "\n");
		txtrText.append("Port: "+ pop.port + "\n");
		txtrText.append("User Name "+ pop.user + "\n");	
		txtrText.append("Password: "+ pop.pass + "\n");
		//ADD IN THE OTHER APPEND LINES TO SHOW INFO
		
		txtrText.setBounds(12, 13, 408, 110);
		frame.getContentPane().add(txtrText);
		
		JButton btnMore = new JButton("More...");
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editConnection.newFrame(null);//opens a frame to edit the connection settings
				frame.dispose();
			}
		});
		btnMore.setBounds(323, 136, 97, 25);
		frame.getContentPane().add(btnMore);
		
		
	}
}
