import java.util.List;

public class Courses {
	static int mcounter = 0;
	static int sscounter = 0;
	static int ecounter = 0;
	static int elcounter = 0;
	static int scounter = 0;
	private String subject, courseName, className;
	private int preferedSize = 20;
	private int units;
	
	Courses(String s, String n, int p){
		subject = s;
		className = n;
		preferedSize = p;
		if(subject.equals("math")){
			mcounter++;
			courseName = "Math "+mcounter;
		}else if(subject.equals("social")){
			sscounter++;
			courseName = "Social "+sscounter;
		}else if(subject.equals("science")){
			scounter++;
			courseName = "Science " + scounter;
		}else if(subject.equals("english")){
			ecounter++;
			courseName = "English "+ecounter;
		}else{
			elcounter++;
			courseName = s+elcounter;
		}
	}
	
	Courses(String s, String n){
		subject = s;
		className = n;
		if(subject.equals("math")){
			mcounter++;
			courseName = "Math "+mcounter;
		}else if(subject.equals("social")){
			sscounter++;
			courseName = "Social "+sscounter;
		}else if(subject.equals("science")){
			scounter++;
			courseName = "Science " + scounter;
		}else if(subject.equals("english")){
			ecounter++;
			courseName = "English "+ecounter;
		}else{
			elcounter++;
			courseName = s+elcounter;
		}
		
	}
	
	int setUnits(int n){
		units = (int)(((double)n/preferedSize)+.5);
		if(units == 0){
			units=1;
		}
		
		return units;
	}

	int getUnits(){
		return units;
	}
	
	String getSubject(){
		return subject;
	}
	String getCourseName(){
		return courseName;
	}
	
	
	
	
}
