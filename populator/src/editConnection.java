import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class editConnection {

	private JFrame frame;
	private JTextField IPTextField;
	private JTextField Port;
	private JTextField Username;
	private JTextField Password;
	private JTextField FirstName;
	private JTextField LastName;

	/**
	 * Launch the application.
	 */
	public static void newFrame(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editConnection window = new editConnection();
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
	public editConnection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 417, 300);
		frame.getContentPane().setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(12, 215, 97, 25);
		frame.getContentPane().add(btnClose);
		
		IPTextField = new JTextField();
		IPTextField.setBounds(12, 45, 116, 22);
		IPTextField.setText(pop.IP);
		frame.getContentPane().add(IPTextField);
		IPTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setLabelFor(IPTextField);
		lblNewLabel.setBounds(12, 16, 116, 25);
		frame.getContentPane().add(lblNewLabel);
		
		Port = new JTextField();
		Port.setText(pop.port);
		Port.setBounds(140, 45, 116, 22);
		frame.getContentPane().add(Port);
		Port.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setLabelFor(Port);
		lblPort.setBounds(140, 16, 116, 31);
		frame.getContentPane().add(lblPort);
		
		Username = new JTextField();
		Username.setText(pop.user);
		Username.setBounds(12, 106, 116, 22);
		frame.getContentPane().add(Username);
		Username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setLabelFor(Username);
		lblNewLabel_1.setBounds(12, 80, 116, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		Password = new JTextField();
		Password.setText(pop.pass);
		Password.setBounds(140, 106, 116, 22);
		frame.getContentPane().add(Password);
		Password.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setLabelFor(Password);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(140, 80, 116, 25);
		frame.getContentPane().add(lblPassword);
		
		FirstName = new JTextField();
		FirstName.setText(pop.first);
		FirstName.setBounds(268, 45, 116, 22);
		frame.getContentPane().add(FirstName);
		FirstName.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setLabelFor(FirstName);
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setBounds(268, 23, 116, 24);
		frame.getContentPane().add(lblFirstName);
		
		JLabel LastNamelbl = new JLabel("Last Name");
		LastNamelbl.setHorizontalAlignment(SwingConstants.CENTER);
		LastNamelbl.setBounds(268, 84, 116, 24);
		frame.getContentPane().add(LastNamelbl);
		
		LastName = new JTextField();
		LastName.setText(pop.last);
		LastNamelbl.setLabelFor(LastName);
		LastName.setColumns(10);
		LastName.setBounds(268, 106, 116, 22);
		frame.getContentPane().add(LastName);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [] settings = new String [6];//ip, port, username, password, firstname, lastname;
				//Checks to make sure that all textfield have an input if not set settings[] to be equal to the previous setting
			
				if(IPTextField.getText().equals("")) settings[0] = pop.IP;
				else settings[0] = IPTextField.getText();
				
				if(Port.getText().equals("")) settings[1] = pop.port;
				else settings[1] = Port.getText();
				
				if(Username.getText().equals("")) settings[2] = pop.user;
				else settings[2] = Username.getText();
				
				if(Password.getText().equals("")) settings[3] = pop.pass;
				else settings[3] = Password.getText();
				
				if(FirstName.getText().equals("")) settings[4] = pop.first;
				else settings[4] = FirstName.getText();
				
				if(LastName.getText().equals("")) settings[5] = pop.last;
				else settings[5] = FirstName.getText();
				
				//set settings variables locally to the most current ones
				pop.IP = settings[0];
				pop.port = settings[1];
				pop.user = settings[2];
				pop.pass = settings[3];
				pop.first = settings[4];
				pop.last = settings[5];
				
				//LINK TO HANNAH"S METHOD  TO SAVE THE SETTINGS IN THE FILE
				saveSettings();
				pop.connect(settings); //tries to reconnect with the mysql server using the new settings
				frame.dispose();//CHECK IF THIS WORKS BECSUE IT MIGHT RUN INTO A LOOP WITH RECONNECT
			}
		});
		btnSave.setBounds(287, 215, 97, 25);
		frame.getContentPane().add(btnSave);
		
	}
	
	
	public static void saveSettings(){
	    PrintWriter printWriter;
		try {
			printWriter = new PrintWriter ("adminData.txt");
			printWriter.println (pop.IP);
			System.out.println(pop.IP);
		    printWriter.println (pop.first);
		    printWriter.println (pop.last);
		    printWriter.println (pop.user);
		    printWriter.println (pop.pass);
		    printWriter.println (pop.port);
		    printWriter.flush();
		    printWriter.close ();  
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
