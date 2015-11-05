
import java.sql.ResultSet;
public class periodGenerator {


	
	
	public void scheduele( Courses [ ] c){	
		int p[] ={ 0, 0, 0, 0, 0,0,0};
		int counter[] = new int[7];
		writeToMysql("", p[0], p[1], p[2], p[3], p[4],p[5], p[6]);
	try{
	     for(int j = 1; j < 2000; j++){
	    	 for(int i = 0; i < c.length; i++){
	    		 if(c[i].getUnits() == j){
	    			 for(int x = 0; x < 7; x++){
	    				 counter[x]  = (int)addColumns("pd" + (x+1));
	    				 System.out.println(counter[x]);
	    			 }
	    			 
	    			 for(int h = 0; h<c[i].getUnits(); h++) {
	    				 
	    				 int lower_period = 6;
		    			 for(int prev_period = 0; prev_period<7;prev_period++) {
		    				 int last = 0;
		    				 if(prev_period == 0) {
		    					 last = counter[prev_period];
		    				 } else if(counter[prev_period] < last) {
		    					 lower_period = prev_period;
		    				 }
		    			 }
		    			 System.out.println("Column Number: " + lower_period);
		    			 p[lower_period] = 1;
	    				 
		    			 
		    			 
	    			 }
	    			 
	    			 
	    			 writeToMysql(c[i].getCourseName(), p[0], p[1], p[2], p[3], p[4],p[5], p[6]);
	    			 }
	    		 
	    		 }
	    	 }
	    	 
	     
	}catch(Exception e){
		System.out.println(e);
		System.out.println("THERES A FUCKING ERROR BITCH");
	}
	}
	
	public void writeToMysql(String n, int pd1, int pd2, int pd3, int pd4, int pd5, int pd6, int pd7){
		try{
		String query = "insert into periods (className, pd1, pd2, pd3, pd4, pd5, pd6, pd7)"
		        + " values (?, ?, ?, ?, ?, ?, ?, ?)";
		java.sql.PreparedStatement preparedStmt = pop.conn.prepareStatement(query);	//sends statement to mysql
		
		      preparedStmt.setString (1, n);
		      preparedStmt.setInt(2, pd1);
		      preparedStmt.setInt(3, pd2);
		      preparedStmt.setInt(4, pd3);
		      preparedStmt.setInt(5, pd4);
		      preparedStmt.setInt(6, pd5);
		      preparedStmt.setInt(7, pd6);
		      preparedStmt.setInt(8, pd7);
		      preparedStmt.execute();
		
	}catch(Exception e){
		
	}
	}
	public static int addColumns(String period){
		String query;
		java.sql.PreparedStatement preparedStmt;
		ResultSet rs1;

		query = "SELECT  COUNT(" + period + ") FROM periods WHERE " + period + " = '1'";
		try {
			preparedStmt = pop.conn.prepareStatement(query);
			preparedStmt.execute();
			rs1 = preparedStmt.getResultSet();
			rs1.absolute(1);
			int a= rs1.getInt(1);
			return a;
		} catch (Exception e) {
			System.out.println(e);
		}		
	 return -1;	//returns -1 if there is an error
	}
}
