import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Choice;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class EditStudent {

	private JFrame frame;
	private ArrayList<String> allOptions;
	Choice pd1;
	/**
	 * Launch the application.
	 */
	public static void newFrame(String title, Object [] args) { //have the parameters be the classses and the name of the student
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudent window = new EditStudent(title, args);
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
	public EditStudent(String title, Object [] arg) {
		allOptions = mysqlHandler.readClasses();
		initialize(title, arg);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String title, Object currentClasses []) {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 201);
		frame.setTitle(title);
		frame.getContentPane().setLayout(null);
		
		
		
		pd1 = new Choice();
		pd1.setBounds(10, 41, 150, 22);
		if(currentClasses[0] == null)pd1.add("");
		else pd1.add((String)currentClasses[0]);
		putAllOptions(pd1);
		frame.getContentPane().add(pd1, BorderLayout.NORTH);
		
		Choice pd2 = new Choice();
		pd2.setBounds(202, 41, 150, 22);
		if(currentClasses[1] == null)pd2.add("");
		else pd2.add((String)currentClasses[1]);
		
		JLabel lblPeriod = new JLabel("Period 1");
		lblPeriod.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriod.setBounds(10, 13, 150, 22);
		frame.getContentPane().add(lblPeriod);
		putAllOptions(pd2);//puts all the options in this dropdown needed for all drop down menus
		frame.getContentPane().add(pd2);
		
		Choice pd3 = new Choice();
		pd3.setBounds(10, 103, 150, 22);
		if(currentClasses[2] == null)pd3.add("");
		else pd3.add((String)currentClasses[2]);
		
		JLabel lblPeriod_1 = new JLabel("Period 2");
		lblPeriod_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriod_1.setBounds(202, 13, 150, 22);
		frame.getContentPane().add(lblPeriod_1);
		putAllOptions(pd3);
		frame.getContentPane().add(pd3);
		
		Choice pd4 = new Choice();
		pd4.setBounds(416, 41, 150, 22);
		if(currentClasses[3] == null)pd4.add("");
		else pd4.add((String)currentClasses[3]);
		putAllOptions(pd4);
		frame.getContentPane().add(pd4);
		
		Choice pd5 = new Choice();
		pd5.setBounds(603, 41, 150, 22);
		if(currentClasses[4] == null)pd5.add("");
		else pd5.add((String)currentClasses[4]);
		putAllOptions(pd5);
		frame.getContentPane().add(pd5);
		
		Choice pd6 = new Choice();
		pd6.setBounds(416, 103, 150, 22);
		if(currentClasses[5] == null)pd6.add("");
		else pd6.add((String)currentClasses[5]);
		putAllOptions(pd6);
		frame.getContentPane().add(pd6);
		
		Choice pd7 = new Choice();
		pd7.setBounds(202, 103, 150, 22);
		if(currentClasses[6] == null)pd7.add("");
		else pd7.add((String)currentClasses[6]);
		putAllOptions(pd7);
		frame.getContentPane().add(pd7);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c [] = {pd1.getSelectedItem(), pd2.getSelectedItem(),
						pd3.getSelectedItem(), pd4.getSelectedItem(),
						pd5.getSelectedItem(), pd6.getSelectedItem(),
						pd7.getSelectedItem()};
				
				mysqlHandler.changeSchedule(EditWindow.getSchedulesCurrentRowId(),  //replaces the old schedule with the new schedule for that specific student
						EditWindow.getName(),
						EditWindow.getGrade(),
						c);
				
				//update the table in the JFrame that holds the schedules..
				try {
					EditWindow.schedulesTable.setModel(new DefaultTableModel(
							mysqlHandler.getTableData("schedules", 20), //gets the student info from Mysql possible only parse 500 students at a time
							new String[] {
								"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"
							}
						));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				EditWindow.frame.update(EditWindow.frame.getGraphics());//update the actual frame to refresh the graphics displayed
				
				frame.dispose();//Finally, dispose of the the "Edit students" frame, because it is no longer needed
			}
		});
		btnSave.setBounds(628, 103, 97, 25);
		frame.getContentPane().add(btnSave);
		
		JLabel lblPeriod_2 = new JLabel("Period 3");
		lblPeriod_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriod_2.setBounds(416, 16, 150, 22);
		frame.getContentPane().add(lblPeriod_2);
		
		JLabel lblPeriod_3 = new JLabel("Period 4");
		lblPeriod_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriod_3.setBounds(603, 13, 150, 22);
		frame.getContentPane().add(lblPeriod_3);
		
		JLabel lblPeriod_4 = new JLabel("Period 5");
		lblPeriod_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriod_4.setBounds(10, 75, 150, 22);
		frame.getContentPane().add(lblPeriod_4);
		
		JLabel lblPeriod_5 = new JLabel("Period 6");
		lblPeriod_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriod_5.setBounds(202, 75, 150, 22);
		frame.getContentPane().add(lblPeriod_5);
		
		JLabel lblPeriod_6 = new JLabel("Period 7");
		lblPeriod_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriod_6.setBounds(416, 75, 150, 22);
		frame.getContentPane().add(lblPeriod_6);
	}
	
	void putAllOptions(Choice c){
		for(int x = 0; x < allOptions.size(); x++){
			c.add(allOptions.get(x));
		}
	}
}
