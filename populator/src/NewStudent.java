import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

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
		science.setBounds(188, 42, 166, 22);
		putAllOptions(science, mysqlHandler.readClasses("science"));//puts all the options in this dropdown needed for all drop down menus
		frame.getContentPane().add(science);
		
		Choice language = new Choice();
		language.setBounds(10, 104, 172, 22);
		putAllOptions(language, mysqlHandler.readClasses("language"));
		frame.getContentPane().add(language);
		
		Choice social = new Choice();
		social.setBounds(360, 42, 166, 22);
		putAllOptions(social, mysqlHandler.readClasses("social"));
		frame.getContentPane().add(social);
		
		Choice english = new Choice();
		english.setBounds(532, 42, 166, 22);
		putAllOptions(english, mysqlHandler.readClasses("english"));
		frame.getContentPane().add(english);
		
		Choice pe = new Choice();
		pe.setBounds(360, 104, 166, 22);
		putAllOptions(pe, mysqlHandler.readClasses("pe"));
		frame.getContentPane().add(pe);
		
		Choice art = new Choice();
		art.setBounds(188, 104, 166, 22);
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
				try{
				mysqlHandler.writeStudentToMysql(Integer.parseInt(txtId.getText()), txtName.getText(), Integer.parseInt(txtGrade.getText()), chosenClasses);
				EditWindow.refreshStudentData();//refresh the students tab to reflect the new student
				NewStudent.frame.dispose(); //closes the new student window
				}catch(Exception ex){
				popUpSchedules.newFrame(null);
			}
				
			}
		});
		btnSave.setBounds(0, 228, 782, 25);
		frame.getContentPane().add(btnSave);
		
		txtId = new JTextField();
		txtId.setBounds(10, 155, 172, 22);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(188, 155, 166, 22);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtGrade = new JTextField();
		txtGrade.setBounds(360, 155, 166, 22);
		frame.getContentPane().add(txtGrade);
		txtGrade.setColumns(10);
		
		JLabel lblPeriod = new JLabel("Math");
		lblPeriod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPeriod.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriod.setBounds(10, 13, 166, 22);
		frame.getContentPane().add(lblPeriod);
		
		JLabel lblScience = new JLabel("Science");
		lblScience.setHorizontalAlignment(SwingConstants.CENTER);
		lblScience.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScience.setBounds(188, 13, 166, 22);
		frame.getContentPane().add(lblScience);
		
		JLabel lblSocialStudies = new JLabel("Social Studies");
		lblSocialStudies.setHorizontalAlignment(SwingConstants.CENTER);
		lblSocialStudies.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSocialStudies.setBounds(360, 13, 166, 22);
		frame.getContentPane().add(lblSocialStudies);
		
		JLabel lblLanguage = new JLabel("English");
		lblLanguage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLanguage.setBounds(532, 13, 166, 22);
		frame.getContentPane().add(lblLanguage);
		
		JLabel lblLanguage_1 = new JLabel("Language");
		lblLanguage_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanguage_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLanguage_1.setBounds(10, 76, 172, 22);
		frame.getContentPane().add(lblLanguage_1);
		
		JLabel lblArt = new JLabel("Art");
		lblArt.setHorizontalAlignment(SwingConstants.CENTER);
		lblArt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArt.setBounds(188, 76, 166, 22);
		frame.getContentPane().add(lblArt);
		
		JLabel lblPhysicalEd = new JLabel("Physical Ed.");
		lblPhysicalEd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhysicalEd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhysicalEd.setBounds(360, 76, 166, 22);
		frame.getContentPane().add(lblPhysicalEd);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(10, 132, 172, 22);
		frame.getContentPane().add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(188, 132, 172, 22);
		frame.getContentPane().add(lblName);
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrade.setBounds(354, 132, 172, 22);
		frame.getContentPane().add(lblGrade);
	}
	
	void putAllOptions(Choice c, ArrayList<String> s){//puts all the class options into an ArrayList that thedrop menus access and present in the GUI
		for(int x = 0; x < s.size(); x++){
			c.add(s.get(x));
		}
	}
	}


