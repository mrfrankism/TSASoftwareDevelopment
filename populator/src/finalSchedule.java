import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class finalSchedule {
	{
	String query;
	ResultSet rs1; 
	java.sql.PreparedStatement preparedStmt; //sets up a query
	query = "SELECT * FROM students "; //sends query to mysql to count amount of students enrolled in a course
	
	try {
		preparedStmt = pop.conn.prepareStatement(query);
		preparedStmt.execute(); //run the statement
		rs1 = preparedStmt.getResultSet();
		rs1.absolute(1); //goes to first row
		int id = rs1.getInt("id");
		String name = rs1.getString("name");
		int grade = rs1.getInt("grade");
		String [] courses = new String [7];
		int [] rowVal = new int(7);
		for (int x = 0; x < courses.length; x++){
			courses[x] = rs1.getString(4+x);
			query = "SELECT row FROM periods where classname = " + courses[x];
			preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute(); //run the statement
			rowVal[x] = preparedStmt.getResultSet().getInt(1);
		}
		int lowestVal = 999999;
		for(int x = 0; x < rowVal.length; x++){
			int currentValue = rowVal[x];
			if(currentVal < lowestVal) lowestVal = currentVal;
		}
		
		
	} catch (Exception e) { //checks for error
		System.out.println(e);
	//returns -1 if there is an error
}

}}