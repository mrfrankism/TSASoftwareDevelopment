import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;
import javax.swing.JLayeredPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DebugGraphics;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditWindow {

	JFrame frame;
	private JTable studentTable;
	private JTable requestTable;
	private JTable schedulesTable;

	/**
	 * Launch the application.
	 */public static void NewWindow(){
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(new Dimension(900, 702));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setSize(new Dimension(100, 100));
		frame.getContentPane().add(tabbedPane, BorderLayout.WEST);
		
		studentTable = new JTable();
		studentTable.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		studentTable.setShowVerticalLines(false);
		studentTable.setModel(new DefaultTableModel(
			mysqlHandler.getTableData("students"), //gets the student info from Mysql possible only parse 500 students at a time
			new String[] {
				"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"
			}
		));
		tabbedPane.addTab("Students\r\n", null, studentTable, "Displays a list of Students currently enrolled in the school\r\n");
		
		
		requestTable = new JTable();
		tabbedPane.addTab("Requests", null, requestTable, "");
		requestTable.setModel(new DefaultTableModel(
				mysqlHandler.getTableData("requests"), //gets the student info from Mysql possible only parse 500 students at a time
				new String[] {
					"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"
				}
			));
		
		schedulesTable = new JTable();
		schedulesTable.setShowVerticalLines(false);
		tabbedPane.addTab("Schedules", null, schedulesTable, null);
		schedulesTable.setModel(new DefaultTableModel(
				mysqlHandler.getTableData("classes"), //gets the student info from Mysql possible only parse 500 students at a time
				new String[] {
					"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"
				}
			));

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNew = new JButton("New Student");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// DefaultTableModel model = (DefaultTableModel) table.getModel(); 
				// model.addRow(new Object[]{"Column 1", "Column 2", "Column 3"}); 
			}
		});
		btnNew.setToolTipText("Create a new student");
		panel_1.add(btnNew);
		
		JButton btnNewButton_1 = new JButton("Edit Student");
		btnNewButton_1.setToolTipText("Edit the student that is currently selected");
		panel_1.add(btnNewButton_1);
		
		JButton btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.setToolTipText("Delete the student that is currently selected\r\n");
		panel_1.add(btnDeleteStudent);
		
		JButton btnInfo = new JButton("Info...");
		btnInfo.setToolTipText("Displays more in depth inofrmation on the currently selected student");
		panel_1.add(btnInfo);
	}
}
