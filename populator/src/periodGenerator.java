import java.util.*;
public class periodGenerator {
HashMap <String, Integer> courses = new HashMap<String, Integer>();
	String[][] table = new String[8][4*pop.coursePerSub];
	{
	for(int i =0; i < pop.coursePerSub * 4; i++){
		table[0][i] = "Math " + i;
		table[0][i+1] = "Science " + i;
		table[0][i+2] = "Social " + i;
		table[0][i+3] = "English " + i;
		i=i+3;
	}
	
	
	}
	public static void InsertionSort( int [ ] num)
	{
	     int j;                     // the number of items sorted so far
	     int key;                // the item to be inserted
	     int i;  
	     for (j = 1; j < num.length; j++)    // Start with 1 (not 0)
	    {
	           key = num[ j ];
	           for(i = j - 1; (i >= 0) && (num[ i ] < key); i--)   // Smaller values are moving up
	          {
	               for (x=)  
	        	   num[ i+1 ] = num[ i ];
	          }
	         num[ i+1 ] = key;    // Put the key in its proper location
	     }
	}
}

def insertion_sort(items, col, row):
    for j in range(0,row):
        while j > 0 and items[j][col] < items[j-1][col]:
            for x in range(0,4):
                items[j][x], items[j-1][x] = items[j-1][x], items[j][x]
            j -= 1