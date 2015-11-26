import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class GUI {

	static JFrame frame;
	static JTextArea textArea;
	static JProgressBar progressBar;
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
	}

}
