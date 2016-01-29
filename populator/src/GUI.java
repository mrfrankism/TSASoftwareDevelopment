import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.io.PrintWriter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI {

	public static String IP;
	public static String first;
	public static String last;
	
	static JFrame frame;

	static JTextArea textArea;

	private static JTextField IPText;
	private static JTextField firstText;
	private static JTextField lastText;

	/**
	 * Create the application.
	 */
	public GUI() {

		try {
			initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setSize(new Dimension(752, 502));
		//frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(123, 211, 117, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name:");
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
		
		firstText = new JTextField();
		firstText.setBounds(314, 215, 130, 26);
		frame.getContentPane().add(firstText);
		firstText.setColumns(10);
		
		lastText = new JTextField();
		lastText.setBounds(314, 262, 130, 26);
		frame.getContentPane().add(lastText);
		lastText.setColumns(10);

		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(314, 381, 117, 29);
		frame.getContentPane().add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   try {
				//testing.main(null);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			   MainPage.NewScreen();
			   GUI.frame.dispose();
			   
			   
				IP=IPText.getText();
				pop.IP=IP;
				first=firstText.getText();
				pop.first=first;
				last=lastText.getText();
				pop.last=last;
			
				
			    PrintWriter printWriter;
					try {
						printWriter = new PrintWriter ("adminData.txt");
						printWriter.println (GUI.IP);
					    printWriter.println (GUI.first);
					    printWriter.println (GUI.last);
					    printWriter.flush();
					    printWriter.close ();  
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			}});
			

		
			

			}};
		    
			
