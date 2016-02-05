import java.sql.*;
import java.util.ArrayList;

//This class handles all the mysql server requests 
public class mysqlHandler {
	static int columnsInLogin = 12;
	static int columnsInSchedules = 10;
	static int columnsInStudents = 10;
	
	public static Object[][] getTableData(String table, int rows) throws SQLException{
		
String query = null;
PreparedStatement preparedStmt;
ResultSet rs1 ;
Object [][] data;
int columns = 0;


//System.out.println(countStudents("name", "students"));


	if(table.equalsIgnoreCase("requests")){
		query = "SELECT * FROM login";//CHANGE THIS LATER 
		columns = columnsInLogin;
		rows = countStudents("id", "login");
		}else if(table.equalsIgnoreCase("schedules")){
			query = "SELECT * FROM schedules";//CHANGE THIS LATER 
			columns = columnsInSchedules;
			rows = countStudents("id", "schedules") ;
			}else if(table.equalsIgnoreCase("students")){
				query = "SELECT * FROM students";//CHANGE THIS LATER 
				columns = columnsInStudents;
				rows = countStudents("id", "students");
				}
		
	/*
	 * gets data from the specific mysql database and runs through it saving all the values to a table
	 */
	
	if(rows == 0) return data = new Object[0][0]; //if rows is bigger than 0, meaning there is data to show in the table then get it else return an empty array
	else {
		data = new Object [rows][columns];
	
	
	
	//gets the various tables from mysql
		try {
			preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute(); //execute the statement
			rs1 = preparedStmt.getResultSet(); //rs1 is the output from the query
			rs1.absolute(1);
		
		} catch (Exception e) { //catches an error
			System.out.println("herererererere");
			//debugging purposes tablelength is set to something
			System.out.println(e);
			rs1 = null;
		}		
		
		for(int y = 0; y < rows; y++){//for loop to loop through the rows of the result set
			for(int x = 0; x < columns; x++){
				//data[y][x]  = new Integer(1);
				
				//data[y][x] = rs1.getObject(x+1);
				data[y][x] = rs1.getObject(x+1);
				
			//       	if(rs1.wasNull()) break;
			}
			if(rs1.isLast()) break;
			else rs1.next();
		}
		
		
		return data;
	}
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
	public static void writeStudentToMysql(int id, String name, int g, String [] c){ //write the periods table to mysql
		try{
		String query = "insert into students (id, name, grade, math, science, social, language, pe, art, english)"
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
		System.out.println(e);
		System.out.println("problem writing student to mysql student table");
	}
	}
	
	public static void editStudentInMysql(int id, String name, int g, String [] c){ //edits the student i the mysql table
		try{
		String query = "replace into students (id, name, grade, math, science, social, language, pe, art, english)"
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
		System.out.println(e);
		System.out.println("problem writing student to mysql student table");
	}
	}
	
	public static void changeSchedule(int id, String name, int grade, String [] classes){//used to edit schedules in the schedules table in mysql
		try{
		String query = "replace into schedules (id, name, grade, pd1, pd2, pd3, pd4, pd5, pd6, pd7)"
		        + " values (?,?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		java.sql.PreparedStatement preparedStmt = pop.conn.prepareStatement(query);	//sends statement to mysql
			  preparedStmt.setInt(1, id);
			  preparedStmt.setString(2, name);
			  preparedStmt.setInt(3, grade);
		      preparedStmt.setString(4, classes[0]);
		      preparedStmt.setString(5, classes[1]);
		      preparedStmt.setString(6, classes[2]);
		      preparedStmt.setString(7, classes[3]);
		      preparedStmt.setString(8, classes[4]);
		      preparedStmt.setString(9, classes[5]);
		      preparedStmt.setString(10, classes[6]);
		      preparedStmt.execute();
		
	}catch(Exception e){ 
		System.out.println(e);
		System.out.println("problem changing student's schedule when sending it to mysql schedule table");
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
	public static int getGrade(int id, String table){
		PreparedStatement preparedStmt;
		ResultSet rs1;
		try{
		String query = "select grade from "+ table + " where id = " + id;
		preparedStmt = pop.conn.prepareStatement(query);
		preparedStmt.execute(); 
		rs1 = preparedStmt.getResultSet();
		rs1.next();//set it to the first row 
		return rs1.getInt(1);//Gets the grade from the resuult set
		
	}catch(Exception e){ 
		System.out.println(e);
		System.out.println("problem getting a student id: " + id +" grade from table " + table);
	}
		return 0;
	}
	public static void setLogin(String param, String value) {
		try{
			String query = "insert into login (" + param + ") values (" + value + ")"; 
			java.sql.PreparedStatement preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute(); 
		}catch(Exception e) {
			
		}
	}
	
	public static int countStudents(String param, String table){//counts the given amount of entries in a given column of a given field
		PreparedStatement preparedStmt;
		ResultSet rs1;
		try{
			String query = "select count("+ param + ") from " + table; 
			preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute(); 
			rs1 = preparedStmt.getResultSet();
			rs1.absolute(1);
			return rs1.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error in counting " +param + " from table " + table);
			return 0;
		}
		
	}
	
	public static ArrayList<String> readClasses(){//reads all the possible classes from Mysql
		PreparedStatement preparedStmt;
		ResultSet rs1;
		try{
			String query = "select * from classes"; 
			preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute(); 
			rs1 = preparedStmt.getResultSet();
			rs1.absolute(1);
			ArrayList<String> classes = new ArrayList<String>();//makes an array to hold all the classes we have
			
			while(rs1.isAfterLast() != true){
				classes.add(rs1.getString(1));
				rs1.next();
			}
			
			return classes;
		}catch(Exception e) {
			System.out.println("Error in getting all the available classes fom mysql");
			return null;
		}
		
	
	}
	public static ArrayList<String> readClasses(String subjectParam){//reads all the possible classes from Mysql
		PreparedStatement preparedStmt;
		ResultSet rs1;
		try{
			String query = "select * from classes where subject = \'" + subjectParam+"\'"; 
			preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute(); 
			rs1 = preparedStmt.getResultSet();
			rs1.absolute(1);
			ArrayList<String> classes = new ArrayList<String>();//makes an array to hold all the classes we have
			
			while(rs1.isAfterLast() != true){
				classes.add(rs1.getString(1));
				rs1.next();
			}
			
			return classes;
		}catch(Exception e) {
			System.out.println("Error in getting all the availble classes fom mysql for specific subject: " + subjectParam);
			return null;
		}
		
	
	}
	
	public static void deleteStudent(int id){ //write the periods table to mysql
		PreparedStatement preparedStmt;
		ResultSet rs1;
	
		try{
		String query = "delete from students where id = " + id;
		preparedStmt = pop.conn.prepareStatement(query);
		preparedStmt.execute(); 
		
	}catch(Exception e){ 
		System.out.println(e);
		System.out.println("problem deleting student with id: "+ id +" from students table");
	}
	}
		public static void deleteSchedule(int id){ //write the periods table to mysql
			PreparedStatement preparedStmt;
			ResultSet rs1;
		
			try{
			String query = "delete from schedules where id = " + id;
			preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute(); 
			
		}catch(Exception e){ 
			System.out.println(e);
			System.out.println("problem deleting schedule with id: "+ id +" from schedules table");
		}
	
	}
	
	public static void deleteRequest(int id){ //delete a request from the login page
		PreparedStatement preparedStmt;	
		try{
		String query = "delete from login where id = " + id;
		preparedStmt = pop.conn.prepareStatement(query);
		preparedStmt.execute(); 
		
	}catch(Exception e){ 
		System.out.println(e);
		System.out.println("problem deleting student request  with id: "+ id +" from login table");
	}
	
	}
	
	public static void deleteFrom(String table){
		PreparedStatement preparedStmt;
		ResultSet rs1;
	
		try{
		String query = "delete from "+table;
		preparedStmt = pop.conn.prepareStatement(query);
		preparedStmt.execute(); 
		
	}catch(Exception e){ 
		System.out.println(e);
		System.out.println("problem deleting from table: " +table);
	}
	}
	}


