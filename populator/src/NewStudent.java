import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class NewStudent {

	private static JFrame frame;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtGrade;

	/**
	 * Launch the application.
	 */
	
	
	public static void newFrame(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewStudent window = new NewStudent();
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
	public NewStudent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 300);
		frame.getContentPane().setLayout(null);
		
		Choice math = new Choice();
		math.setBounds(10, 42, 172, 22);
		putAllOptions(math, mysqlHandler.readClasses("math"));
		frame.getContentPane().add(math);
		
		Choice science = new Choice();
		science.setBounds(202, 42, 150, 22);
		putAllOptions(science, mysqlHandler.readClasses("science"));//puts all the options in this dropdown needed for all drop down menus
		frame.getContentPane().add(science);
		
		Choice language = new Choice();
		language.setBounds(10, 104, 172, 22);
		putAllOptions(language, mysqlHandler.readClasses("language"));
		frame.getContentPane().add(language);
		
		Choice social = new Choice();
		social.setBounds(416, 42, 150, 22);
		putAllOptions(social, mysqlHandler.readClasses("social"));
		frame.getContentPane().add(social);
		
		Choice english = new Choice();
		english.setBounds(603, 42, 150, 22);
		putAllOptions(english, mysqlHandler.readClasses("english"));
		frame.getContentPane().add(english);
		
		Choice pe = new Choice();
		pe.setBounds(416, 104, 150, 22);
		putAllOptions(pe, mysqlHandler.readClasses("pe"));
		frame.getContentPane().add(pe);
		
		Choice art = new Choice();
		art.setBounds(202, 104, 150, 22);
		putAllOptions(art, mysqlHandler.readClasses("art"));
		frame.getContentPane().add(art);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chosenClasses[] = {
						math.getSelectedItem(), science.getSelectedItem(),
						social.getSelectedItem(), language.getSelectedItem(),
						art.getSelectedItem(), pe.getSelectedItem(),
						english.getSelectedItem()
				};//set up the array with the new students classes
				mysqlHandler.writeStudentToMysql(Integer.parseInt(txtId.getText()), txtName.getText(), Integer.parseInt(txtGrade.getText()), chosenClasses);
			NewStudent.frame.dispose(); //closes the new student window
			}
		});
		btnSave.setBounds(0, 228, 782, 25);
		frame.getContentPane().add(btnSave);
		
		txtId = new JTextField();
		txtId.setText("Id");
		txtId.setBounds(10, 167, 116, 22);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("NAme");
		txtName.setBounds(202, 167, 116, 22);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtGrade = new JTextField();
		txtGrade.setText("grade");
		txtGrade.setBounds(418, 167, 116, 22);
		frame.getContentPane().add(txtGrade);
		txtGrade.setColumns(10);
	}
	
	void putAllOptions(Choice c, ArrayList<String> s){
		
		for(int x = 0; x < s.size(); x++){
			c.add(s.get(x));
		}
	}
	}


