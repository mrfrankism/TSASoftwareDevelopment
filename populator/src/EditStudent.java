import java.awt.EventQueue;
import java.util.ArrayList;

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
	private ArrayList<String> allOptions;
	Choice choice;
	/**
	 * Launch the application.
	 */
	public static void newFrame(Object [] args) { //have the parameters be the classses and the name of the student
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudent window = new EditStudent(args);
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
	public EditStudent(Object [] arg) {
		allOptions = mysqlHandler.readClasses();
		initialize(arg);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object currentClasses []) {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		choice = new Choice();
		choice.setBounds(10, 41, 150, 22);
		if(currentClasses[0] == null)choice.add("");
		else choice.add((String)currentClasses[0]);
		putAllOptions(choice);
		frame.getContentPane().add(choice, BorderLayout.NORTH);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(202, 41, 150, 22);
		if(currentClasses[1] == null)choice_1.add("");
		else choice_1.add((String)currentClasses[1]);
		putAllOptions(choice_1);//puts all the options in this dropdown needed for all drop down menus
		frame.getContentPane().add(choice_1);
		
		Choice choice_2 = new Choice();
		choice_2.setBounds(10, 103, 150, 22);
		if(currentClasses[2] == null)choice_2.add("");
		else choice_2.add((String)currentClasses[2]);
		putAllOptions(choice_2);
		frame.getContentPane().add(choice_2);
		
		Choice choice_3 = new Choice();
		choice_3.setBounds(416, 41, 150, 22);
		if(currentClasses[3] == null)choice_3.add("");
		else choice_3.add((String)currentClasses[3]);
		putAllOptions(choice_3);
		frame.getContentPane().add(choice_3);
		
		Choice choice_4 = new Choice();
		choice_4.setBounds(603, 41, 150, 22);
		if(currentClasses[4] == null)choice_4.add("");
		else choice_4.add((String)currentClasses[4]);
		putAllOptions(choice_4);
		frame.getContentPane().add(choice_4);
		
		Choice choice_5 = new Choice();
		choice_5.setBounds(416, 103, 150, 22);
		if(currentClasses[5] == null)choice_5.add("");
		else choice_5.add((String)currentClasses[5]);
		putAllOptions(choice_5);
		frame.getContentPane().add(choice_5);
		
		Choice choice_6 = new Choice();
		choice_6.setBounds(202, 103, 150, 22);
		if(currentClasses[6] == null)choice_6.add("");
		else choice_6.add((String)currentClasses[6]);
		putAllOptions(choice_6);
		frame.getContentPane().add(choice_6);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(266, 186, 97, 25);
		frame.getContentPane().add(btnSave);
	}
	
	void putAllOptions(Choice c){
		System.out.println("hello");
		for(int x = 0; x < allOptions.size(); x++){
			c.add(allOptions.get(x));
		}
	}
}
