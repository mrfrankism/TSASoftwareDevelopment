import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
public class finalSchedule {
	String query;
	ResultSet rs1; 
	java.sql.PreparedStatement preparedStmt; //sets up a query
	
	
	public void makeSchedules(){
	
	
	
	 int [][] periodChart = periodGenerator.periodChart2;
	
	for(int g = 0; g < pop.size; g++){
	query = "SELECT * FROM students "; //sends query to mysql to count amount of students enrolled in a course
	
	try {
		preparedStmt = pop.conn.prepareStatement(query);
		preparedStmt.execute(); //run the statement
		rs1 = preparedStmt.getResultSet();
		rs1.next();
		rs1.absolute(1+g); //goes through the different rows of students
		
		int id = rs1.getInt("id");
		String name = rs1.getString("name");
		int grade = rs1.getInt("grade");
				
		String [] courses = new String [7];
		int [] rowVal = new int [7];
		for (int x = 0; x < courses.length; x++){
			courses[x] = rs1.getString(x+4);
		}
		for (int x = 0; x < courses.length; x++){
			
			query = "SELECT row FROM periods where classname = '" + courses[x] + "'";
			preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute(); //run the statement
			rs1 = preparedStmt.getResultSet();
			rs1.next();
			rowVal[x] = rs1.getInt(1);
		}
		
		String [] newCourses = new String [7];
		int [] newRowVal = rowVal;
		
		Arrays.sort(newRowVal);
	
		for(int x = 0; x < rowVal.length; x++){
			for(int y = 0; y < rowVal.length; y++){
				if (newRowVal[x]==rowVal[y]){
					newCourses[x]=courses[y];
				}
			}
		}

		String [] classes = new String[pop.numPeriods];
		
		for(int h = 0; h < newCourses.length; h++){//looping through hannah's courses
			int temp = 999;
			
			
			for(int j = 0; j < pop.numPeriods; j++){//looping through periods of hannah's courses
				int poop=periodChart[newRowVal[h]][j];
				System.out.println(poop);
			if(periodChart[newRowVal[h]][j] != -1 && (classes[j] == "" ||classes[j] == null)){//if value in the chart is not equal to -1 and that place in the person's scheduele is not taken
				if(periodChart[newRowVal[h]][j] < temp) temp = periodChart[newRowVal[h]][j];
			}		
		}
			
			for (int j = 0; j < pop.numPeriods; j++){
				if(periodChart[newRowVal[h]][j] == temp){
					//periodChart[newRowVal[h]][j]++;
					classes[j] = newCourses[h];
					break;
					
				}
			}
			
		}
		writeClassesToMysql(id, name, grade, classes);	
	} catch (Exception e) { //checks for error
		System.out.println(e);
	//returns -1 if there is an error
}

	}

}	public void writeClassesToMysql(int id, String name, int g, String [] c){ //write the periods table to mysql
	try{
	String query = "insert into schedules (id, name, grade, pd1, pd2, pd3, pd4, pd5, pd6, pd7)"
	        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	java.sql.PreparedStatement preparedStmt = pop.conn.prepareStatement(query);	//sends statement to mysql
	      preparedStmt.setInt (1, id);
	      preparedStmt.setString(2, name);
	      preparedStmt.setInt(3, g);
	      preparedStmt.setString(4, c[0]);
	      preparedStmt.setString(5, c[1]);
	      preparedStmt.setString(6, c[2]);
	      preparedStmt.setString(7, c[3]);
	      preparedStmt.setString(8, c[4]);
	      preparedStmt.setString(9, c[5]);
	      preparedStmt.setString(10, c[6]);
	      preparedStmt.execute();
	
}catch(Exception e){ 
	System.out.println("SOMETHING WENT WRONG");
}
}
}