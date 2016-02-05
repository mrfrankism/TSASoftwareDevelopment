import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DebugGraphics;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class EditWindow {

	static JFrame frame;
	private static JTable studentTable;
	private JTable requestTable;
	static JTable schedulesTable;
	private JButton btnNew = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	/**
	 * Launch the application.
	 */
	public static void NewWindow(){
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				EditWindow w = new EditWindow();
				w.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}
	 
	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws IOException 
	 */
	 
	public EditWindow() throws SQLException  {
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws SQLException 
	 */
	
	private void initialize() throws IOException, SQLException {
		frame = new JFrame();
		frame.setResizable(false);

		
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(new Dimension(900, 702));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(tabbedPane.getSelectedIndex() == 0){//if the students tab is selected do this...
						btnNew.setText("New Student");	
						btnDelete.setText("Delete Student");
						btnEdit.setText("Edit Student");
						frame.update(frame.getGraphics());
			}else if(tabbedPane.getSelectedIndex() == 1){//if the request tab is selected do this...
				btnNew.setText("Accept Request"); 
				btnEdit.setText("Edit Request");
				btnDelete.setText("Delete Request");
				frame.update(frame.getGraphics());
				}else if(tabbedPane.getSelectedIndex() == 2){//is the schedules tab is selected do this...
					btnNew.setText("New Schedule");
					btnEdit.setText("EditSchedule");
					btnDelete.setText("Delete Schedule");
					frame.update(frame.getGraphics());
				} 
				 }
		});
		tabbedPane.setSize(new Dimension(100, 100));
		
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		studentTable = new JTable();
		studentTable.setDragEnabled(true);
		studentTable.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		studentTable.setShowVerticalLines(false);
		studentTable.setModel(new DefaultTableModel(
			mysqlHandler.getTableData("students", 20), //gets the student info from Mysql possible only parse 500 students at a time
			new String[] {
				"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"
			}
		));
		JScrollPane jsp = new JScrollPane(studentTable);
		tabbedPane.addTab("Students\r\n", null, jsp, "Displays a list of Students currently enrolled in the school\r\n");
		
		
		requestTable = new JTable();
		
		requestTable.setModel(new DefaultTableModel(
				mysqlHandler.getTableData("requests", 20), //gets the student info from Mysql possible only parse 500 students at a time
				new String[] {
					"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"
				}
			));
		JScrollPane jsp1 = new JScrollPane(requestTable);
		tabbedPane.addTab("Requests", null, jsp1, "");
		
		schedulesTable = new JTable();
		schedulesTable.setShowVerticalLines(false);
	
		schedulesTable.setModel(new DefaultTableModel(
				mysqlHandler.getTableData("schedules", 20), //gets the student info from Mysql possible only parse 500 students at a time
				new String[] {
					"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"
				}
			));
	JScrollPane jsp2 = new JScrollPane(schedulesTable);
		tabbedPane.addTab("Schedules", null, jsp2, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//opens up another GUI Page to add a student
				if(tabbedPane.getSelectedIndex() == 0){
					NewStudent.newFrame(null);
					
				}
				else if(tabbedPane.getSelectedIndex() == 1){
					//ADD CODE TO SET THE REQUEST TO BE THE STUDENTS SCHEDULE
				}
			}
		});
		btnNew.setToolTipText("Create a new student");
		panel_1.add(btnNew);
		
	
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//opens a page to edit the classes a student has
				if(tabbedPane.getSelectedIndex() == 0) {
					EditStudent.newFrame(
					studentTable.getValueAt(studentTable.getSelectedRow(),0).toString(),//get the idof the student that is selected
					studentTable.getValueAt(studentTable.getSelectedRow(), 1).toString(),//get the name from the student table
					(String)studentTable.getValueAt(studentTable.getSelectedRow(), 2),//get the grade from the studentTable
									getStudentClasses());
				}else if(tabbedPane.getSelectedIndex() == 2 )	{
					int r = schedulesTable.getSelectedRow();
					//opens the frame to edit the currently selected student's schedule, also passes the title of the editing window
					EditSchedule.newFrame((String)schedulesTable.getValueAt(r, 1) + " -Id-" 
					+schedulesTable.getValueAt(r,0).toString(), getStudentSchedule());
				}else if (tabbedPane.getSelectedIndex() == 1){
					System.out.println("OPEN THE EDIT REQUEST PAGE");
				}
			}
		});
		btnEdit.setToolTipText("Edit a selected schedule,mainly used for schedules that are missing classes");
		panel_1.add(btnEdit);
		
	
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//opens a pop up to confirm the delete-tion of a student
				if(tabbedPane.getSelectedIndex() != 0) deleteButtonPopUp.newFrame(null);
				else{
				mysqlHandler.deleteStudent(Integer.parseInt(studentTable.getValueAt(studentTable.getSelectedRow(), 0).toString()));	
					refreshStudentsTab();
			}}
		});
		btnDelete.setToolTipText("Delete the student that is currently selected\r\n");
		panel_1.add(btnDelete);
		
		Box verticalBox = Box.createVerticalBox(); //creates a box that keeps the buttons "pushed" towards the top of the Panel
		verticalBox.setOpaque(true);
		verticalBox.setForeground(Color.LIGHT_GRAY);
		verticalBox.setBackground(new Color(192, 192, 192));
		panel_1.add(verticalBox);
	}
	
	public static String [] getStudentSchedule(){
		int r, c;
		r = schedulesTable.getSelectedRow();
		System.out.println("Row value is : " + r);
		c = schedulesTable.getColumnCount();
		System.out.println("Column value  is: " + c);
		String classes[] = new String[c-3];
		
		
		for(int x = 0; x < c-3; x++){
			if(schedulesTable.getValueAt(r, x+3) == null) classes[x] = "";
			else classes[x] = schedulesTable.getValueAt(r, x+3).toString();
		}
		return  classes;
	}
	
	public static String [] getStudentClasses(){
		int r, c;
		r = studentTable.getSelectedRow();
		System.out.println("Row value is : " + r);
		c = studentTable.getColumnCount();
		System.out.println("Column value  is: " + c);
		String classes[] = new String[c-3];
		
		
		for(int x = 0; x < c-3; x++){
			if(studentTable.getValueAt(r, x+3) == null) classes[x] = "";
			else classes[x] = studentTable.getValueAt(r, x+3).toString();
		}
		return classes;
	}
	
	public static int getSchedulesCurrentRowId(){
		return (int)schedulesTable.getValueAt(schedulesTable.getSelectedRow(), 0);
	}
	public static String getName(){
		return (String)schedulesTable.getValueAt(schedulesTable.getSelectedRow(), 1);
	}
	public static int getGrade(){
		return (int)schedulesTable.getValueAt(schedulesTable.getSelectedRow(), 2);
	}
	
	public static void refreshStudentsTab() {
		try {
			studentTable.setModel(new DefaultTableModel(
					mysqlHandler.getTableData("students", 20), //gets the student info from Mysql possible only parse 500 students at a time
					new String[] {
						"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"
					}
				));
		} catch (SQLException e) {
			System.out.println("Problem refresh student tab with data from mysql");
			e.printStackTrace();
		}
		frame.update(frame.getGraphics());
	}
}
