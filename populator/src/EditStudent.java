import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Choice;

public class EditStudent {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void newFrame(String [] args) { //have the parameters be the classses and the name of the student
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudent window = new EditStudent();
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
	public EditStudent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 781, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Choice choice = new Choice();
		choice.setBounds(10, 67, 181, 22);
		choice.add("Class 1");
		choice.add("class 2");
		choice.add("class 3");
		frame.getContentPane().add(choice, BorderLayout.NORTH);
	}

}
