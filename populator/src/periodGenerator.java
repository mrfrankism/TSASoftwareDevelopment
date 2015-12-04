
import java.sql.ResultSet;
public class periodGenerator {


	static int [][] periodChart2 = new int[pop.numPeriods*(pop.subOptions)][pop.numPeriods];
	
	public void scheduele( Courses [ ] c){	
		
		int p[] ={ 0, 0, 0, 0, 0,0,0};//set array for p values of 1 or 0 to represent if class in that period is filled or not
		int counter[] = new int[7]; //make an empty array for each of the periods
		int rowNumber = 0;
		writeToMysql(rowNumber, "", p[0], p[1], p[2], p[3], p[4],p[5], p[6]);//must write first row of zeros for the rest to run
	try{
	     for(int j = 1; j < 2000; j++){
	    	 for(int i = 0; i < c.length; i++){ //loops through the actual course names (provided by array from pop)
	    		 if(c[i].getUnits() == j){ //checks to see if the amount of units of the class is the least amount(starts at 1, goes until maximum course with maximum amount of units is looped through), currently 2000
	    			 for(int x = 0; x < 7; x++){ //goes through the 7 columns accounting for each period
	    				 counter[x]  = (int)addColumns("pd" + (x+1)); //the value in the counter array is equal to the amount of the rest of the values(0 and 1) added together
	    				 //System.out.println(counter[x]); //print the value of the total courses being filled in that period
	    			 }
	    			 
	    			 //now we have a course selected and we know how many units are in each column
    				 for(int yolo = 0; yolo<p.length;yolo++) {
    					 p[yolo] = 0;
    				 }
	    			 for(int h = 0; h<c[i].getUnits(); h++) {  //loops through the number of units in that course
	    				 
	    				 int lowest=0;
	    				 int lowest_period = 0;
	    				 
	    				 for(int xx = 0; xx<counter.length; xx++) {
	    					 
	    					 if(xx == 0) {
	    						 lowest = counter[xx];
	    					 } else if(counter[xx] < lowest) {
	    						 lowest = counter[xx];
	    						 lowest_period = xx;
	    					 }
	    					 
	    				 }
	    				 	 
	    				 counter[lowest_period]++;
	    				 p[lowest_period]=1;
	    				 
	    				 				
	    			 }
	    			 rowNumber++;
	    			 writeToMysql(rowNumber, c[i].getClassName(), p[0], p[1], p[2], p[3], p[4],p[5], p[6]); //append the row to mysql
	    			 for(int f = 0; f < pop.numPeriods; f++){
	    				 if( p[f]==0) {
	    					 periodChart2[i][f] = -2;
	    				 
	    				 }
	    				 else periodChart2[i][f] = 0;
	    				 System.out.println(periodChart2[i][f]);
	    			 }
	    			 }
	    		 
	    		 }
	    	 }
	    	 
	     
	}catch(Exception e){ //catches errors
		System.out.println(e);
		System.out.println("THERES A FUCKING ERROR BITCH");//you already know it
	}
	}
	
	public void writeToMysql(int rowNum, String n, int pd1, int pd2, int pd3, int pd4, int pd5, int pd6, int pd7){ //write the periods table to mysql
		try{
		String query = "insert into periods (className, pd1, pd2, pd3, pd4, pd5, pd6, pd7, row)"
		        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		java.sql.PreparedStatement preparedStmt = pop.conn.prepareStatement(query);	//sends statement to mysql
		      preparedStmt.setString (1, n);
		      preparedStmt.setInt(2, pd1);
		      preparedStmt.setInt(3, pd2);
		      preparedStmt.setInt(4, pd3);
		      preparedStmt.setInt(5, pd4);
		      preparedStmt.setInt(6, pd5);
		      preparedStmt.setInt(7, pd6);
		      preparedStmt.setInt(8, pd7);
		      preparedStmt.setInt(9, rowNum);
		      preparedStmt.execute();
		
	}catch(Exception e){ 
		
	}
	}
	public static int addColumns(String period){
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
}
