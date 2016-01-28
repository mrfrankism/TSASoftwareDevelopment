import java.sql.*;
import java.util.ArrayList;

//This class handles all the mysql server requests 
public class mysqlHandler {
	static int columnsInLogin = 13;
	static int columnsInSchedules = 10;
	static int columnsInStudents = 10;
	public static Object[][] getTableData(String table, int rows) throws SQLException{
		int tableLength = 0;
		
//String[] firstRow = {"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"};

String query = null;
PreparedStatement preparedStmt;
ResultSet rs1 = null;
Object [][] data;
int columns = 0;



	if(table.equalsIgnoreCase("requests")){
			query = "SELECT * FROM login";
			columns = columnsInLogin;
		}else if(table.equalsIgnoreCase("schedules")){
			query = "SELECT * FROM schedules"; 
			columns = columnsInStudents;
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
			rs1.first();
			data = new Object [tableLength][columns];
			System.out.println("HERE");
		} catch (Exception e) { //catches an error
			System.out.println("herererererere");
			//debugging purposes tablelength is set to something
			tableLength = 10;
			System.out.println(e);
		}		
		
		tableLength = 10;
		data = new Object [columns][tableLength];
		
		//is data null at this point?
		for(int y = 0; y <= data.length; y++){//for loop to loop through the rows of the result set
			for(int x = 0; x <= data[y].length; x++){
				//data[y][x]  = 1;
				data[y][x] = rs1.getObject(x);
				if(rs1.wasNull()) break;
			}
			if(rs1.isLast()) break;
			else rs1.next();
		}
		
		
		return data;
		
		}
	
	public static void writeClassesToMysql(int id, String name, int g, String [] c){ //write the periods table to mysql
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
	
	public static int sumOfColumn(String period){
		String query;
		java.sql.PreparedStatement preparedStmt;
		ResultSet rs1;

		query = "SELECT  COUNT(" + period + ") FROM periods WHERE " + period + " = '1'"; //count amount of courses in a period where value is one (filled)
		try {
			preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute(); //execute the statement
			rs1 = preparedStmt.getResultSet(); //rs1 is the output from the query
			rs1.absolute(1);  //gets result from the first row
			int a= rs1.getInt(1); //gets the value of the first column's results added together
			return a; //returns the total of the classes with "1" in that period
		} catch (Exception e) { //catches an error
			System.out.println(e);
		}		
	 return -1;	//returns -1 if there is an error
	}
	
	public static void setLogin(String param, String value) {
		try{
			String query = "insert into login (" + param + ") values (" + value + ")"; 
			java.sql.PreparedStatement preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute(); 
		}catch(Exception e) {
			
		}
	}
	
	public static void countStudents(String param){
		
		
	}
	
	}


