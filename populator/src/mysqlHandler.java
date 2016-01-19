import java.sql.*;
import java.util.ArrayList;

//This class handles all the mysql server requests 
public class mysqlHandler {

	public static Object[][] getTableData(String r) throws SQLException{
		int tableLength = 0;
		int tableWidth = 1;
		
//String[] firstRow = {"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"};

String query;
PreparedStatement preparedStmt;
ResultSet rs1 = null;

query = "SELECT * FROM periods"; //count amount of courses in a period where value is one (filled)
Object[][] data = new Object [9][500];


	if(r.equalsIgnoreCase("requests")){
			query = "SELECT * FROM students"; 
		}else if(r.equalsIgnoreCase("periods")){
			query = "SELECT * FROM periods"; 
		}else if(r.equalsIgnoreCase("schedules")){
			query = "SELECT * FROM schedules"; 
		}
		
	/*
	 * gets data from the specific mysql database and runs through it saving all the values to a table
	 */
	
		try {
			preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute(); //execute the statement
			rs1 = preparedStmt.getResultSet(); //rs1 is the output from the query
			rs1.last();
			tableLength = rs1.getRow();
		} catch (Exception e) { //catches an error
			System.out.println(e);
		}		
		
		
		ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
		
		
		
		for(int y = 1; y <= tableLength; y++){//for loop to loop through the rows of the result set
	
			//		result.add(new ArrayList<Object>());
			
			for(int x = 1; x <= tableWidth; x++){
				data[y-1][x-1] = rs1.getObject(x);
				if(rs1.wasNull()) break;
				else tableWidth++;
			}
			
			if(rs1.isLast()) break;
			else rs1.next();
			
		}
		
		
		return data;
		
		}
	}


