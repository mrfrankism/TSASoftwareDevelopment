import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;


public class homePage {

	static JFrame frame;
	static JLabel lblNewLabel;
	static JLabel mathLbl;
	static JLabel socialStudiesLbl, scienceLbl, englishLbl, peLbl, languageLbl, artLbl;
	
	static JTabbedPane tabbedPane;
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
		pop.connect();
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Hello "+ pop.first+ " " + pop.last);
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
		
		JButton btnEditSettings = new JButton("Edit Settings");
		btnEditSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editConnection.newFrame(null);
			}
		});
		btnEditSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnEditSettings.setBounds(370, 302, 95, 40);
		
		frame.getContentPane().add(btnEditSettings);
		
		JButton btnEditStudents = new JButton("Edit Students");
		btnEditStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditWindow.NewWindow();
			}
		});
		btnEditStudents.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnEditStudents.setBounds(370, 265, 95, 40);
		frame.getContentPane().add(btnEditStudents);
		

		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnRefresh.setBounds(370, 227, 95, 40);
		frame.getContentPane().add(btnRefresh);
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Thread t = new Thread(new Runnable() {
				        @Override
				        public void run() {
				        	frame.setTitle("Working");
				        	graphMaker.getGraphs();
				        
				        	//Updates all the images in the tabs to the recently made ones
							mathLbl.setIcon(getImageIcon(new File("mathPieChart.png")));
							socialStudiesLbl.setIcon(getImageIcon(new File("socialPieChart.png")));
							scienceLbl.setIcon(getImageIcon(new File("sciencePieChart.png")));
							languageLbl.setIcon(getImageIcon(new File("languagePieChart.png")));
							englishLbl.setIcon(getImageIcon(new File("englishPieChart.png")));
							artLbl.setIcon(getImageIcon(new File("artPieChart.png")));
							peLbl.setIcon(getImageIcon(new File("pePieChart.png")));
							frame.setTitle("");
				        }     
				    });
				    t.start();
			
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
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 50, 350, 300);
		frame.getContentPane().add(tabbedPane);
		
	
		
		mathLbl = new JLabel();
		mathLbl.setIcon(getImageIcon(new File("mathPieChart.png")));
		tabbedPane.add("Math", mathLbl);
				
		socialStudiesLbl = new JLabel();
		tabbedPane.addTab("Social Studies", socialStudiesLbl);
		socialStudiesLbl.setIcon(getImageIcon(new File("socialPieChart.png")));
		
		scienceLbl = new JLabel();
		tabbedPane.addTab("Science", scienceLbl);
		scienceLbl.setIcon(getImageIcon(new File("sciencePieChart.png")));
		
		englishLbl = new JLabel();
		tabbedPane.addTab("English", englishLbl);
		englishLbl.setIcon(getImageIcon(new File("englishPieChart.png")));
		
		languageLbl = new JLabel();
		tabbedPane.addTab("Language", languageLbl);
		languageLbl.setIcon(getImageIcon(new File("languagePieChart.png")));
		
		artLbl = new JLabel();
		tabbedPane.addTab("Art", artLbl);
		artLbl.setIcon(getImageIcon(new File("artPieChart.png")));
		
		peLbl = new JLabel();
		tabbedPane.addTab("PE", peLbl);
		peLbl.setIcon(getImageIcon(new File("pePieChart.png")));
		
		JButton btnResort = new JButton("Sort");
		btnResort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//pop.makeStudents();//TAKE THIS OUT FOR FINAL RELEASE
				(new Thread((new pop()))).start(); //sorts students and makes graphs
				
			}
		});
		btnResort.setBounds(286, 18, 97, 25);
		frame.getContentPane().add(btnResort);

		
	}
	public static void updateName(){
		lblNewLabel.setText("Hello "+ pop.first+ " " + pop.last);
		frame.update(frame.getGraphics());
	}
	 @SuppressWarnings("finally")
	public ImageIcon getImageIcon(File f)
	    {

		 ImageIcon ii = null;
	        try
	        {
	            Image im = ImageIO.read(f);


	            ii = new ImageIcon(im);


	        }
	        catch(IOException i)
	        {

	            i.printStackTrace();


	        }



	        finally
	        {

	            return ii;

	        }


	    }
}
