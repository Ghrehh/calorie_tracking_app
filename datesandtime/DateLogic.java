package datesandtime;
import java.text.DateFormat;
import saves.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateLogic {
	
	DateFormat dYear;
	DateFormat dDay;
	DateFormat dMonth;
	Date date;
	
	public int year;
	public int month;
	public int day;
	
	
	public DateLogic() {
		setCurrentDate();



	}
	
	public void setCurrentDate(){
		dYear = new SimpleDateFormat("yyyy");
		dMonth = new SimpleDateFormat("MM");
		dDay = new SimpleDateFormat("dd");
		
		date = new Date(); 
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		year = Integer.parseInt(dYear.format(cal.getTime()));
		month = Integer.parseInt(dMonth.format(date));
		day = Integer.parseInt(dDay.format(date));
	}
	
	public void printDate(){
		System.out.println(Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year) ); //2014/08/06 15:59:48
		
	}

	public Day getDay(List<Year> yearArr, int searchYear, int searchMonth, int searchDay ){ //finds particular day in the years array if it exists, if not it creates it
		
		Year year = null;
		Month month = null;
		Day day = null;
		
		
		//year
		for (int i = 0; i < yearArr.size(); i++) { //tries to find the year in the array
			if (yearArr.get(i).number == searchYear) {
				year = yearArr.get(i);
				break;
			}
			
		}
		
		if (year == null) { 
			year = new Year(searchYear); //creates a new one if it is not found
			yearArr.add(year);
			System.out.println("Creating new year");
		}
		
		//month
		for (int i2 = 0; i2 < year.month_list.size(); i2++) { //tries to find the month in the array
			if (year.month_list.get(i2).number == searchMonth) {
				month = year.month_list.get(i2);
				break;
			}
			
		}
		
		if (month == null) { 
			month = new Month(searchMonth); //creates a new one if it is not found
			year.month_list.add(month);
			System.out.println("Creating new month");
		}
		
		//day
		for (int i3 = 0; i3 < month.day_list.size(); i3++) { //tries to find the year in the array
			if (month.day_list.get(i3).number == searchDay) {
				day = month.day_list.get(i3);
				break;
			}
			
		}
		
		if (day == null) { 
			day = new Day(searchDay); //creates a new one if it is not found
			month.day_list.add(day);
			System.out.println("Creating new day");
		}
		
		
		
		return day;
	}

	public String currentDate(){
		return Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
	}
}
