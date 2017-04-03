package BackEnd;


/**
 * This class holds all information for the date. This includes
 * the day, time, month, and year as strings.
 * 
 * @author Tevin Schmidt
 *
 */
public class Date {
		/**
		 * The day in the following format: 
		 * 01 for single digits, 19 for double digits
		 */
		private String day;
		
		/**
		 * The month in the following format:
		 * 01 for single digits, 10 for double digits
		 */
		private String month;
		
		/**
		 * The year as a four digit int:
		 * 2017 or 1995
		 */
		private String year;
		
		public Date(String day, String month, String year){
			this.day = day;
			this.month = month;
			this.year = year;
			
		}
		
		public void setDay(String day){
			this.day = day;
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
		
		public String getMonth(){
			return this.month;
		}
		
		public String getYear(){
			return this.year;
		}
		
		/**
		 * Returns the date as a string in the following format:
		 * DD/MM/YYYY
		 * @return
		 */
		public String printDate(){
			String temp = new String(day + "/" + month + "/" + year);
			return temp;
		}
		
		public void convertFromString(String theDate){
			String [] temp = theDate.split("/");
			this.day = temp[0];
			this.month = temp[1];
			this.year = temp[2];
		}
}
