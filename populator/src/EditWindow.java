import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
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

public class EditWindow {

	JFrame frame;
	private JTable studentTable;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public EditWindow() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(new Dimension(900, 702));
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); took this out so that closing the edit wondow doesnt close the program
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 109, 663, 545);
		frame.getContentPane().add(tabbedPane);
		
		studentTable = new JTable();
		studentTable.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		studentTable.setShowVerticalLines(false);
		studentTable.setModel(new DefaultTableModel(
			mysqlHandler.getTableData(), //gets the student info from Mysql possible only parse 500 students at a time
			new String[] {
				"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"
			}
		));
		tabbedPane.addTab("Students\r\n", null, studentTable, "Displays a list of Students currently enrolled in the school\r\n");
		

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(687, 131, 195, 308);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNew = new JButton("New Student");
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
