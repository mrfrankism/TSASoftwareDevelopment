import java.sql.DriverManager;
import java.awt.EventQueue;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
//import com.mysql.*;
//import com.mysql.jdbc.PreparedStatement;
public class pop {
    public static int subOptions = 5;
    public static int numPeriods = 7;
	public static Connection conn;
	public static int size = 10;	//number of students total
	
	public static Courses [] classes = new Courses[numPeriods * subOptions]; //makes array of 20 courses (5 per subject)
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		
		GUI.newFrame();
	
		//EditStudent.newFrame(null);
		 conn = DriverManager//build connection
		          .getConnection("jdbc:mysql://mastacademy.ddns.net:1234/school?"
		              + "user=counselor&password=mastacademy");
	
//		conn = DriverManager//build connection
//		          .getConnection("jdbc:mysql://10.144.81.21:3307/school?"
//		              + "user=hannah&password=111998pw");
//		
		
		Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      
	

	    String[][] subjectss = new String[][]{
	    	  { "math", "Pre-calc", "Ap-Calc", "Calc Hrs", "Algebra 2", "Geometry"},
	    	  { "science", "Chemis", "Bio", "Physics", "Physical", "DE Geo" },
	    	  { "social","US History", "World Hist", "Human Geo", "Gov", "Econ" },
	    	  { "english","Ap-Lit", "Ap-Lang", "Lit Hrs", "Lang Hrs", "World Lit" },
	    	  { "art", "3d art", "fabrics and fibers", "2d art", "sculpting", "art hist" },
	    	  { "pe", "scuba", "gym", "sports1", "swim", "sailing" },
	    	  { "language", "german", "spanish", "chineese", "hebrew", "portuge" }
	    	};
	    	
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
			java.sql.PreparedStatement preparedStmt = pop.conn.prepareStatement(query);	//sends statement to mysql
			
			for(int i = 0; i < size; i++){	//loops through arrays to give (column, value) for each row
			      preparedStmt.setInt (1, ID[i]);
			      preparedStmt.setString(2, name[i]);
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
			
			query = "insert into Classes (class_Name, subject)"
			        + " values (?, ?)";
			preparedStmt = conn.prepareStatement(query);
			for(int i = 0; i < subjectss.length; i++){	//loops through arrays to give (column, value) for each row
				for(int x = 1; x < subjectss[i].length; x++){
			      preparedStmt.setString (1, subjectss[i][x]);
			      preparedStmt.setString(2, subjectss[i][0]);
			      preparedStmt.execute();
			}
			}
			GUI.textArea.append("Done adding to database");

			
			int z = 0;
			for ( int j = 0; j < numPeriods; j++){
				for ( int o = 1; o < subOptions+1; o++ ){
				classes[z] = new Courses(subjectss[j][0], subjectss[j][o]);
				z++;
				}
			}
							
		
			
			
			
			for(int i=0; i< numPeriods*(subOptions); i++){	//get count for each subject and their levels (20 total)
				classes[i].setUnits(getNumberOfStudents(classes[i].getSubject(), classes[i].getClassName()));
			}
			periodGenerator pg = new periodGenerator();
			pg.scheduele(classes);
			finalSchedule f = new finalSchedule();
			f.makeSchedules();
			System.out.println("DONE!!!");
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
			System.out.println(e);
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
}



