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
<<<<<<< Updated upstream
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
=======
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextField;
import java.awt.Font;
>>>>>>> Stashed changes
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

	public static String IP;
	public static String username;
	public static String password;
	
	static JFrame frame;
<<<<<<< Updated upstream
	static JTextArea textArea;
=======
	private JTextField IPText;
	private JTextField usernameText;
	private JTextField passwordText;
>>>>>>> Stashed changes
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
		frame.getContentPane().setLayout(null);
		
<<<<<<< Updated upstream
		JDesktopPane desktopPane_2 = new JDesktopPane();
		frame.getContentPane().add(desktopPane_2, BorderLayout.NORTH);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 734, 454);
		desktopPane_2.add(textArea);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(-179, 0, 189, 324);
		desktopPane.add(panel);
		
		JButton btnStart = new JButton("start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							EditWindow window = new EditWindow();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		frame.getContentPane().add(btnStart, BorderLayout.SOUTH);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		//probably use this to show an error messsage
=======
		

		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(123, 211, 117, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(123, 258, 123, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IP Address:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(123, 161, 117, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mysql Server");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblNewLabel_3.setBounds(241, 19, 281, 80);
		frame.getContentPane().add(lblNewLabel_3);
		
		IPText = new JTextField();
		IPText.setBounds(314, 165, 130, 26);
		frame.getContentPane().add(IPText);
		IPText.setColumns(10);
		
		usernameText = new JTextField();
		usernameText.setBounds(314, 215, 130, 26);
		frame.getContentPane().add(usernameText);
		usernameText.setColumns(10);
		
		passwordText = new JTextField();
		passwordText.setBounds(314, 262, 130, 26);
		frame.getContentPane().add(passwordText);
		passwordText.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IP=IPText.getText();
				username=usernameText.getText();
				password=passwordText.getText();
			}
			
		});
		
		loginButton.setBackground(Color.WHITE);
		loginButton.setBounds(279, 394, 117, 29);
		frame.getContentPane().add(loginButton);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
>>>>>>> Stashed changes
	}
}
