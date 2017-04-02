package BackEnd;

import java.util.LinkedList;

/**
 * This class acts as the main system for the back end of the application.
 * It holds a list of all flights that are in the management system. 
 * 
 * @author Tevin Schmidt
 *
 */
public class Manager {
		
		private LinkedList<Flight> flights;
		private Server theServer;
		
		public Manager(){
			this.flights = new LinkedList<Flight>();
		}
		
		
		public void run(){
			Task toPerform;
			
			
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
