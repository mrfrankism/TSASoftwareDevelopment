import java.awt.Choice;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewSchedule {

	private static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void newFrame(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewSchedule window = new NewSchedule();
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
	public NewSchedule() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 300);
		frame.getContentPane().setLayout(null);
		
		Choice period1 = new Choice();
		period1.setBounds(10, 42, 172, 22);
		putAllOptions(period1, mysqlHandler.readClasses("math"));
		frame.getContentPane().add(period1);
		
		Choice period2 = new Choice();
		period2.setBounds(188, 42, 166, 22);
		putAllOptions(period2, mysqlHandler.readClasses("science"));//puts all the options in this dropdown needed for all drop down menus
		frame.getContentPane().add(period2);
		
		Choice period3 = new Choice();
		period3.setBounds(10, 104, 172, 22);
		putAllOptions(period3, mysqlHandler.readClasses("language"));
		frame.getContentPane().add(period3);
		
		Choice period4 = new Choice();
		period4.setBounds(360, 42, 166, 22);
		putAllOptions(period4, mysqlHandler.readClasses("social"));
		frame.getContentPane().add(period4);
		
		Choice period5 = new Choice();
		period5.setBounds(532, 42, 166, 22);
		putAllOptions(period5, mysqlHandler.readClasses("english"));
		frame.getContentPane().add(period5);
		
		Choice period6 = new Choice();
		period6.setBounds(360, 104, 166, 22);
		putAllOptions(period6, mysqlHandler.readClasses("pe"));
		frame.getContentPane().add(period6);
		
		Choice period7 = new Choice();
		period7.setBounds(188, 104, 166, 22);
		putAllOptions(period7, mysqlHandler.readClasses("art"));
		frame.getContentPane().add(period7);
		
		JButton btnSave = new JButton("Save");
		JTextField txtName;
		JTextField txtId;
		JTextField txtGrade;

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
		
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String chosenClasses[] = {
						period1.getSelectedItem(), period2.getSelectedItem(),
						period4.getSelectedItem(), period3.getSelectedItem(),
						period7.getSelectedItem(), period6.getSelectedItem(),
						period5.getSelectedItem()
				};//set up the array with the new students classes
				try{
				
				mysqlHandler.writeScheduleToMysql(Integer.parseInt(txtId.getText()), txtName.getText(), Integer.parseInt(txtGrade.getText()), chosenClasses);
				EditWindow.refreshScheduleData();//refresh the students tab to reflect the new student
				NewSchedule.frame.dispose(); //closes the new student window
				}catch(Exception ex){
				popUpSchedules.newFrame(null);
			}				
			}
		});
		JLabel lblPeriod = new JLabel("Period 1");
		lblPeriod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPeriod.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriod.setBounds(10, 13, 166, 22);
		frame.getContentPane().add(lblPeriod);
		
		JLabel lblScience = new JLabel("Period 2");
		lblScience.setHorizontalAlignment(SwingConstants.CENTER);
		lblScience.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScience.setBounds(188, 13, 166, 22);
		frame.getContentPane().add(lblScience);
		
		JLabel lblSocialStudies = new JLabel("Period 3");
		lblSocialStudies.setHorizontalAlignment(SwingConstants.CENTER);
		lblSocialStudies.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSocialStudies.setBounds(360, 13, 166, 22);
		frame.getContentPane().add(lblSocialStudies);
		
		JLabel lblLanguage = new JLabel("Period 4");
		lblLanguage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLanguage.setBounds(532, 13, 166, 22);
		frame.getContentPane().add(lblLanguage);
		
		JLabel lblLanguage_1 = new JLabel("Period 5");
		lblLanguage_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanguage_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLanguage_1.setBounds(10, 76, 172, 22);
		frame.getContentPane().add(lblLanguage_1);
		
		JLabel lblArt = new JLabel("Period 6");
		lblArt.setHorizontalAlignment(SwingConstants.CENTER);
		lblArt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArt.setBounds(188, 76, 166, 22);
		frame.getContentPane().add(lblArt);
		
		JLabel lblPhysicalEd = new JLabel("Period 7eqwe");
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


