//This class handles all the mysql server requests 
public class mysqlHandler {

	
	
	
	
	
	
	
	
	public static Object[][] getTableData(){
		String[] firstRow = {"ID", "First Name", "Last Name", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7"};
		String [][] obj = {firstRow, {"hannah","hannah","hannah","hannah","hannah","hannah","hannah"}};
		return obj;
		
		//gets the data and passes it to the jtable in the GUI
	}
}
