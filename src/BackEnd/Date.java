package BackEnd;


/**
 * This class holds all information for the date. This includes
 * the day, time, month, and year as strings.
 * 
 * @author Tevin Schmidt
 *
 */
public class Date {
		
		private String day;
		private String time;
		private String month;
		private String year;
		
		public void setDay(String day){
			this.day = day;
		}
		
		public void setTime(String time){
			this.time = time;
		}
		
		public void setMonth(String month){
			this.month = month;
		}
		
		public void setYear(String year){
			this.year = year;
		}
		
		public String getDay(){
			return this.day;
		}
		
		public String getTime(){
			return this.time;
		}
		
		public String getMonth(){
			return this.month;
		}
		
		public String getYear(){
			return this.year;
		}
}
