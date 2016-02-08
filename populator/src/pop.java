import java.sql.DriverManager;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class pop  implements Runnable{
	public static String IP ;
	public static String first;
	public static String last ;
	public static String user ;
	public static String pass ;
	public static String port ;
	public static boolean firstRun = true;
	public static boolean enabled = false;
	private static BufferedReader bufferReader;
    public static int subOptions = 5;
    public static int numPeriods = 7;
	public static Connection conn;
	public static int size = 75;	//number of students total

	
	public static Courses [] classes = new Courses[numPeriods * subOptions]; //makes array of 20 courses (5 per subject)
	
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		File f= new File("adminData.txt");
	  if(f.exists()==true){
		  firstRun = false;
		  System.out.print("file did exist");
		  String fileName="adminData.txt";
	      FileReader inputFile = new FileReader(fileName);
	      bufferReader = new BufferedReader(inputFile);
	      IP=bufferReader.readLine();
	      //save IP address
	      first=bufferReader.readLine();
	      //save first name
	      last=bufferReader.readLine();
	      //save last name 
	      user=bufferReader.readLine();
	      //save last name 
	      pass=bufferReader.readLine();
	      //save last name 
	      port=bufferReader.readLine();
	      //save last name 
	      
	      EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						homePage mp = new homePage();
						mp.frame.setVisible(true);

						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}); 
		}
		else{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					loginPage.newFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		}
	
	      
	}
public void run(){
	//if(firstRun)makeStudents();
	sortSchool();
	graphMaker.getGraphs();
	homePage.frame.update(homePage.frame.getGraphics());
	System.out.println("DONE!!!");
}
public static void makeStudents(){
	   
	    	
		int[] ID = new int[size+1];
		String[] name = new String[size];
		int[] grade = new int[size];
		String[] math = new String[size];
		String[] social = new String[size];
		String[] science = new String[size];
		String[] english = new String[size];
		String[] art= new String[size];
		String[] pe = new String[size];
		String[] language = new String[size];
		
		for(int i=0; i<size; i++)	{	//loops through arrays to create students
			ID[i]=i;
			grade[i]=(9+(int)(Math.random()*4));	//randomizes grades 9-12
			
	
			math[i] = subjectss[0][(int)(1+Math.random()*(subOptions))];
			science[i] = subjectss[1][(int)(1+Math.random()*(subOptions))];
			social[i] = subjectss[2][(int)(1+Math.random()*(subOptions))];
			english[i] = subjectss[3][(int)(1+Math.random()*(subOptions))];
			art[i] = subjectss[4][(int)(1+Math.random()*(subOptions))];
			pe[i] = subjectss[5][(int)(1+Math.random()*(subOptions))];
			language[i] = subjectss[6][(int)(1+Math.random()*(subOptions))];
			name[i]="hannah";	//names of students**********
			}
			
			String query = "insert into students (id, name, grade, math, science, social, english, art, pe, language)"
			        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try{
			java.sql.PreparedStatement preparedStmt = pop.conn.prepareStatement(query);	//sends statement to mysql
			
			for(int i = 0; i < size; i++){	//loops through arrays to give (column, value) for each row
			      preparedStmt.setInt (1, ID[i]);
			      preparedStmt.setString(2, "hannah");
			      preparedStmt.setInt(3, grade[i]);
			      preparedStmt.setString(4, math[i]);
			      preparedStmt.setString(5, science[i]);
			      preparedStmt.setString(6, social[i]);
			      preparedStmt.setString(7, english[i]);
		 	      preparedStmt.setString(8, art[i]);
			      preparedStmt.setString(9, pe[i]);
			      preparedStmt.setString(10, language[i]);
			      preparedStmt.execute();
			     
			}
			
			query = "insert into classes (class_name, subject)"
			        + " values (?, ?)";
			preparedStmt = conn.prepareStatement(query);
			for(int i = 0; i < subjectss.length; i++){	//loops through arrays to give (column, value) for each row
				for(int x = 1; x < subjectss[i].length; x++){
			      preparedStmt.setString (1, subjectss[i][x]);
			      preparedStmt.setString(2, subjectss[i][0]);
			      preparedStmt.execute();
			}
			}
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error writing students to MYsql");
			}
			
			
}

static String[][] subjectss = new String[][]{
	  { "math", "Pre-calc", "Ap-Calc", "Calc Hrs", "Algebra 2", "Geometry"},
	  { "science", "Chemis", "Bio", "Physics", "Physical", "DE Geo" },
	  { "social","US History", "World Hist", "Human Geo", "Gov", "Econ" },
	  { "english","Ap-Lit", "Ap-Lang", "Lit Hrs", "Lang Hrs", "World Lit" },
	  { "art", "3d art", "fabrics and fibers", "2d art", "sculpting", "art hist" },
	  { "pe", "scuba", "gym", "sports1", "swim", "sailing" },
	  { "language", "german", "spanish", "chineese", "portugese","hebrew" }
	};

public static void sortSchool(){
			
	
	mysqlHandler.deleteFrom("schedules");//clears the current schedules in the database
	mysqlHandler.deleteFrom("periods"); //clears the current set up of periods
	int z = 0;
	for ( int j = 0; j < numPeriods; j++){
		for ( int o = 1; o < subOptions+1; o++ ){
		classes[z] = new Courses(subjectss[j][0], subjectss[j][o]);
		z++;
		}
	}
	
	for(int i=0; i< classes.length; i++){	//get count for each subject and their levels (20 total)
		classes[i].setUnits(getNumberOfStudents(classes[i].getSubject(), classes[i].getClassName()));
	System.out.println(classes[i].getClassName());
	}
	
			periodGenerator pg = new periodGenerator();
			pg.scheduele(classes);
			finalSchedule k = new finalSchedule();
			k.makeSchedules();
			
	}
	
	public static int getNumberOfStudents(String subject, String course){ //gets total students enrolled in a course
		String query;
		java.sql.PreparedStatement preparedStmt; //sets up a query
		ResultSet rs1;

		query = "SELECT  COUNT(" + subject + ") FROM students WHERE " + subject + " = '" + course + "'"; //sends query to mysql to count amount of students enrolled in a course
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute(); //run the statement
			rs1 = preparedStmt.getResultSet();
			rs1.absolute(1); //goes to first row
			int a= rs1.getInt(1); //value from first column
			return a;
		} catch (Exception e) { //checks for error
			System.out.println("error counting students that have "+course+ " as a "+subject+ " course.");
		}		
	 return -1;	//returns -1 if there is an error
	}
	
	int [] getStudents(String subject, String className){
		String query;
		java.sql.PreparedStatement preparedStmt; //sets up a query
		ResultSet rs1;
		int [] ids = null;
		int amountOfStudents = getNumberOfStudents(subject, className);
		query = "SELECT * FROM students WHERE " + subject + " = '" + className + "'"; //sends query to mysql to count amount of students enrolled in a course
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute(); //run the statement
			rs1 = preparedStmt.getResultSet();
			rs1.absolute(1); //goes to first row
			ids = new int [amountOfStudents];
			for (int i =0; i < amountOfStudents; i++){
			ids[i] = rs1.getInt("id"); //value from first column
			rs1.absolute(2+i);
			}
			return ids;
		} catch (Exception e) { //checks for error
			
			System.out.println(e);
		}		
	 return ids;	//returns -1 if there is an error
	}
	public static void connect(String [] changes){ //used when changes have been made to connection settings
		try {
			conn = DriverManager//build connection
			          .getConnection("jdbc:mysql://"+changes[0]+":"+changes[1]+"/school?"
				              + "user="+changes[2]+"&password="+changes[3]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String [] args = {IP,port,user,pass,first, last};
			couldNotConnectPopup.newFrame(args);
		}
		}
	
	public static void connect(){ //used to simply reuse previous connection settings
		try {
			conn = DriverManager//build connection
			          .getConnection("jdbc:mysql://"+IP+":"+port+"/school?"
				              + "user="+user+"&password="+pass);
			System.out.println("Connected!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not connect");
			String [] args = {IP,port,user,pass,first, last};
			couldNotConnectPopup.newFrame(args);
		}
		
		}
}



