package BackEnd;

import java.util.LinkedList;

/**
 * This class acts as the main system for the back end of the application.
 * It holds a list of all flights that are in the management system. 
 * 
 * @author Tevin Schmidt
 *
 */
public class System {
		
		private LinkedList<Flight> flights;
		
		public System(){
			this.flights = new LinkedList<Flight>();
		}
		
		public Flight searchDate(Date theDate){
			return null;
		}
		
		public Flight searchSource(String theSource){
			return null;
		}
		
		public Flight searchDestination(String theDestination){
			return null;
		}
		
	
}
