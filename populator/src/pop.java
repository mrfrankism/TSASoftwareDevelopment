import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
//import com.mysql.*;
//import com.mysql.jdbc.PreparedStatement;
public class pop {
    public static int coursePerSub= 5;
	public static Connection conn;
	public static void main(String args[]) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		
		
		conn = DriverManager//build connection
		          .getConnection("jdbc:mysql://10.144.81.21:3307/school?"
		              + "user=hannah&password=111998pw");
	
		
		Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      
		Courses [] classes = new Courses[20]; //makes array of 20 courses (5 per subject)
		
		String [] mclasses = {"Pre-calc", "Ap-Calc", "Calc Hrs", "Algebra 2", "Geometry"}; 
		String [] eclasses = {"Ap-Lit", "Ap-Lang", "Lit Hrs", "Lang Hrs", "World Literature"};
		String [] sclasses = {"Chemistry", "Bio", "Physics", "Physical Science", "DE Geology"};
	    String [] ssclasses = {"US History", "World History", "Human Geo", "Gov", "Econ"};
	    

	    int size = 500;	//number of students total
		int[] ID = new int[size+1];
		String[] name = new String[size];
		int[] grade = new int[size];
		String[] math = new String[size];
		String[] social = new String[size];
		String[] science = new String[size];
		String[] english = new String[size];
		
		for(int i=0; i<size; i++)	{	//loops through arrays to create students
			ID[i]=i;
			grade[i]=(9+(int)(Math.random()*4));	//randomizes grades 9-12
			math[i]="Math "+ (1 + (int)(Math.random()*coursePerSub));
			social[i]="Social "+ (1 + (int)(Math.random()*coursePerSub));
			science[i]="Science "+ (1 + (int)(Math.random()*coursePerSub));
			english[i]="English "+ (1 + (int)(Math.random()*coursePerSub));
			name[i]="hannah";	//names of students
			}
			
			String query = "insert into students (id, name, grade, math, science, social, english)"
			        + " values (?, ?, ?, ?, ?, ?, ?)";
			java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);	//sends statement to mysql
			
			for(int i = 0; i < size; i++){	//loops through arrays to give (collumn, value) for each row
			      preparedStmt.setInt (1, ID[i]);
			      preparedStmt.setString(2, name[i]);
			      preparedStmt.setInt(3, grade[i]);
			      preparedStmt.setString(4, math[i]);
			      preparedStmt.setString(5, science[i]);
			      preparedStmt.setString(6, social[i]);
			      preparedStmt.setString(7, english[i]);
			      preparedStmt.execute();
			}
			for(int i=0; i<4*coursePerSub; i++){	//get count for each subject and their levels (20 total)
				classes[i] = new Courses("math", mclasses[i/coursePerSub]);
				classes[i+1] = new Courses("science", sclasses[i/coursePerSub]);
				classes[i+2] = new Courses("english", eclasses[i/coursePerSub]);
				classes[i+3] = new Courses("social", ssclasses[i/coursePerSub]);
				i=i+3;				
			}
			for(int i=0; i< 4*coursePerSub; i++){	//get count for each subject and their levels (20 total)
				classes[i].setUnits(getNumberOfStudents(classes[i].getSubject(), classes[i].getCourseName()));
	  			System.out.println("Number of Units for class: "+ classes[i].getUnits());
			}
			
			
			periodGenerator pg = new periodGenerator();
			pg.scheduele(classes);
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
}


