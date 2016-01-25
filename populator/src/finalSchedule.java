public class finalSchedule {
	String query;
	java.sql.ResultSet rs1; 
	java.sql.PreparedStatement preparedStmt; //sets up a query
	
	int[][] periodChart = 
			periodGenerator.periodChart2;
	public void makeSchedules(){
		
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
		newCourses = courses;

		
	
		String [] classes = new String[pop.numPeriods];
		
		for(int h = 0; h < newCourses.length; h++){//looping through hannah's courses
			int temp = 999;
			

			innerloop:
			for (int j = 0; j < pop.numPeriods; j++){
				if((periodChart[newRowVal[h]][j] != temp) && (periodChart[newRowVal[h]][j] > -1) && ((classes[j] == "" )||(classes[j] == null))){

					periodChart[newRowVal[h]][j] += 1;		
					classes[j] = newCourses[h];
					break innerloop;
					
				}
			}
			
		}
		
		
		
		mysqlHandler.writeClassesToMysql(id, name, grade, classes);	
	} catch (Exception e) { //checks for error
		System.out.println(e);
		System.out.println("Error here ");
	//returns -1 if there is an error
}

	}

}	
}