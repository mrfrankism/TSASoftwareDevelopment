import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.EventQueue;

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

public class loginPage {

	public static String IP;
	public static String first;
	public static String last;
	public static String user;
	public static String pass;
	public static String port;
	
	 static JFrame frame;

	static JTextArea textArea;

	static loginPage pg;
	private static JTextField IPText;
	private static JTextField firstText;
	private static JTextField lastText;
	private static JTextField userText;
	private static JTextField passText;
	private static JTextField portText;

	/**
	 * Create the application.
	 */
	
	
	public static void newFrame(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pg = new loginPage();
					pg.frame.setVisible(true);
					pop.enabled = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}
	public loginPage() {

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
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel.setBounds(350, 100, 100, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(350, 200, 100, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IP Address");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(50, 100, 100, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Username");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(50, 200, 100, 30);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Password");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(200, 200, 100, 30);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Port");
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(200, 100, 100, 30);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_3 = new JLabel("Login");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(200, 0, 200, 60);
		frame.getContentPane().add(lblNewLabel_3);
		
		IPText = new JTextField();
		IPText.setBounds(50, 140, 100, 30);
		frame.getContentPane().add(IPText);
		IPText.setColumns(10);
		
		firstText = new JTextField();
		firstText.setBounds(350, 140, 100, 30);
		frame.getContentPane().add(firstText);
		firstText.setColumns(10);
		
		lastText = new JTextField();
		lastText.setBounds(350, 240, 100, 30);
		frame.getContentPane().add(lastText);
		lastText.setColumns(10);

		userText = new JTextField();
		userText.setBounds(50, 240, 100, 30);
		frame.getContentPane().add(userText);
		userText.setColumns(10);
		
		passText = new JTextField();
		passText.setBounds(200, 240, 100, 30);
		frame.getContentPane().add(passText);
		passText.setColumns(10);
		
		portText = new JTextField();
		portText.setBounds(200, 140, 100, 30);
		frame.getContentPane().add(portText);
		portText.setColumns(10);
		
		JButton loginButton = new JButton("Enter");
		loginButton.setBounds(200, 310, 100, 30);
		frame.getContentPane().add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   
			   
			   String [] settings = new String[6];
			   
			   pop.IP=IPText.getText();
			   System.out.println("IP from loginWindow: "+ IPText.getText());
				pop.first=firstText.getText();
				pop.last=lastText.getText();
				pop.user=userText.getText();
				pop.pass=passText.getText();
				pop.port=portText.getText();
				
				pop.connect();
				
				
				homePage.NewScreen();
				loginPage.frame.dispose();

			  editConnection.saveSettings();
					
			}});
			

		
			

			}
	public static JFrame getFrame(){
		return pg.frame;
	}
	

}
		    
			
			
			
			
