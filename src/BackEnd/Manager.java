package BackEnd;

import java.util.LinkedList;

/**
 * This class acts as the main system for the back end of the application.
 * It holds a list of all flights that are in the management system. 
 * 
 * @author Tevin Schmidt
 *
 */
public class Manager extends Thread{
		
		private LinkedList<Flight> flights;
		private Server theServer;
		
		public Manager(){
			this.flights = new LinkedList<Flight>();
		}
		
		
		public void run(){
			Task toPerform;
			
			while(true){
				synchronized (theServer.getQueue()) {
					while(theServer.getQueue().isEmpty()){
						try{
							theServer.getQueue().wait();
						}
						catch(InterruptedException e){
							System.err.println("An error occurred while queue is waiting.");
							System.err.println(e.getMessage());
						}
						
					}
					toPerform = theServer.getQueue().poll();
				}
				
				try{
					toPerform.run();
				}
				catch(RuntimeException e){
					System.err.println("Thread pool is interrupted due to an issue: " + e.getMessage());
				}
			}
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
