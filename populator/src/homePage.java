import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Font;
import javax.swing.*;
import javax.swing.ImageIcon;


public class homePage {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homePage window = new homePage();
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
	public homePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hello "+ pop.first+ " " + pop.last);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel.setBounds(18, 6, 350, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Server");
		lblNewLabel_1.setBounds(395, 90, 75, 40);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.setBounds(380, 120, 75, 40);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Socket client = new Socket("mastacademy.ddns.net", 80);
					
					OutputStream out = client.getOutputStream();
					
					out.write("serverStatus:on".getBytes());
					out.flush();
					
					out.close();
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			});
		
		JButton btnNewButton_1 = new JButton("Stop");
		btnNewButton_1.setBounds(380, 160, 75, 40);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Socket client = new Socket("mastacademy.ddns.net", 80);
					
					OutputStream out = client.getOutputStream();
					
					out.write("serverStatus:off".getBytes());
					out.flush();
					
					out.close();
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			});
		
		JButton btnEditStudents = new JButton("Edit Students");
		btnEditStudents.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnEditStudents.setBounds(370, 265, 95, 40);
		frame.getContentPane().add(btnEditStudents);
		

		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnRefresh.setBounds(370, 302, 95, 40);
		frame.getContentPane().add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					graphMaker.getGraphs();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.update(frame.getGraphics());
				System.out.println("refresh hoe");
			}
			});
		
		JLabel lblNewLabel_9 = new JLabel("Today is:");
		lblNewLabel_9.setBounds(420, -20, 100, 100);
		frame.getContentPane().add(lblNewLabel_9);
		
		  GregorianCalendar gcalendar = new GregorianCalendar();
		  String day = ("" + gcalendar.get(Calendar.DATE) + "/");
		  String month = ("" + (gcalendar.get(Calendar.MONTH)+1) + "/");
		  String year = ("" + gcalendar.get(Calendar.YEAR));

			JLabel lblNewLabel_8 = new JLabel(month + day + year);
			lblNewLabel_8.setBounds(420, 0, 100, 100);
			frame.getContentPane().add(lblNewLabel_8);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 50, 350, 300);
		frame.getContentPane().add(tabbedPane);
		
				JLabel lblNewLabel_3 = new JLabel();
				tabbedPane.addTab("Math", null, lblNewLabel_3, null);
				lblNewLabel_3.setIcon(new ImageIcon("mathPieChart.png"));
				
				
				JLabel lblNewLabel_4 = new JLabel();
				tabbedPane.addTab("Social Studies", null, lblNewLabel_4, null);
				lblNewLabel_4.setIcon(new ImageIcon("socialPieChart.png"));
		
		JLabel lblNewLabel_2 = new JLabel();
		tabbedPane.addTab("Science", null, lblNewLabel_2, null);
		lblNewLabel_2.setIcon(new ImageIcon("sciencePieChart.png"));
		
		JLabel lblNewLabel_7 = new JLabel();
		tabbedPane.addTab("English", null, lblNewLabel_7, null);
		lblNewLabel_7.setIcon(new ImageIcon("englishPieChart.png"));
		
		JLabel lblNewLabel_5 = new JLabel();
		tabbedPane.addTab("Language", null, lblNewLabel_5, null);
		lblNewLabel_5.setIcon(new ImageIcon("languagePieChart.png"));
		
		JLabel lblNewLabel_6 = new JLabel();
		tabbedPane.addTab("PE", null, lblNewLabel_6, null);
		lblNewLabel_6.setIcon(new ImageIcon("pePieChart.png"));

		
	}
}
