package populator;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
//import com.mysql.*;
//import com.mysql.jdbc.PreparedStatement;
public class pop {
	static Connection conn;
	public static void main(String args[]) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		
		
		conn = DriverManager
		          .getConnection("jdbc:mysql://10.144.81.21:3307/school?"
		              + "user=hannah&password=111998pw");
	
		
		Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      

	      
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
			math[i]="Math "+ (1 + (int)(Math.random()*5));
			social[i]="Social "+ (1 + (int)(Math.random()*5));
			science[i]="Science "+ (1 + (int)(Math.random()*5));
			english[i]="English "+ (1 + (int)(Math.random()*5));
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
			
			
			for(int i=1; i<6; i++){	//get count for each subject and their levels (20 total)
				getNumberOfClasses("math", ("Math "+ i));
				getNumberOfClasses("social", ("Social "+ i));
				getNumberOfClasses("science", ("Science "+ i));
				getNumberOfClasses("english", ("English "+ i));
			}
	
	}
	public static int getNumberOfClasses(String subject, String course){
		String query;
		java.sql.PreparedStatement preparedStmt;
		ResultSet rs1;

		query = "SELECT  COUNT(" + subject + ") FROM students WHERE " + subject + " = '" + course + "'";
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
			rs1 = preparedStmt.getResultSet();
			rs1.absolute(1);
			System.out.println("Number of classes for " + course + " : "+rs1.getInt("COUNT(" + subject + ")"));	
			int a= rs1.getInt("COUNT(" + subject + ")");
			return a;
		} catch (Exception e) {
			System.out.println(e);
		}		
	 return -1;	//returns -1 if there is an error
	}
}
