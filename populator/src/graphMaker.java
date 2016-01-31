import java.awt.Color;
import java.io.*;
import java.text.DecimalFormat;

import org.jfree.chart.JFreeChart; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartUtilities; 
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Font;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;


public class graphMaker
{
   public static void getGraphs() throws IOException
   {
	   
	   

	    String[][] subs = new String[][]{
	    	  { "math", "Pre-calc", "Ap-Calc", "Calc Hrs", "Algebra 2", "Geometry"},
	    	  { "science", "Chemis", "Bio", "Physics", "Physical", "DE Geo" },
	    	  { "social","US History", "World Hist", "Human Geo", "Gov", "Econ" },
	    	  { "english","Ap-Lit", "Ap-Lang", "Lit Hrs", "Lang Hrs", "World Lit" },
	    	  { "art", "3d art", "fabrics and fibers", "2d art", "sculpting", "art hist" },
	    	  { "pe", "scuba", "gym", "sports1", "swim", "sailing" },
	    	  { "language", "german", "spanish", "chineese", "hebrew", "portuge" }
	    	};

	    	
	 for(int x=0; x<subs.length; x++){
		 String subject=subs[x][0];
		 DefaultPieDataset dataset = new DefaultPieDataset();
		 for(int y=1; y<subs[x].length; y++){
			 dataset.setValue(subs[x][y], new Integer(pop.getNumberOfStudents(subject, subs[x][y])));

		 }
	      JFreeChart pieChartObject = ChartFactory.createPieChart(
	    	         (subject.toUpperCase() +" CLASSES"),dataset, true, true, true);
	    	      pieChartObject.setBackgroundPaint(new Color(229,229, 229));
	    	      
	    	      final PiePlot plot = (PiePlot) pieChartObject.getPlot();
	    	      plot.setLabelFont(new Font("Lucida Grande", Font.PLAIN, 12));
	    	      PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator( 
	    	    		  "{0} ({1})", new DecimalFormat("0"), new DecimalFormat("0")); 
	    	    		  plot.setLabelGenerator(generator); 
	    	      
	    	      int width = 300; /* Width of the image */
	    	      int height = 250; /* Height of the image */ 
	    	      File pieChart = new File( subject+"PieChart.png" );
	    	      ChartUtilities.saveChartAsPNG(pieChart , pieChartObject, width ,height);
	 }   

   }

  
}