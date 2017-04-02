package BackEnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This class deals with the transfer of data between the client, database, and the system.
 * It receives information and decides what action to take.
 * 
 * @author Tevin Schmidt
 *
 */
public class Server{
	private Driver driver;
	private Booking bookFlight;
	private LinkedList<Flight> flights;
	


	
	public Server(int numThreads){
		driver = new Driver();
		driver.initialize();
		
	}
	
	public void run(){
		ThreadPool pool = new ThreadPool(6);
		
		while(true){
			
		
		
		}
	}
	
	public void serialize(){
		
	}
	
	public void deserialize(){
		
	}
	
	public Driver getDriver(){
		return this.driver;
	}
	
	public void addFlightList(String inputName){
		
		try{
			FileReader reader = new FileReader(inputName + ".txt");
			BufferedReader read = new BufferedReader(reader);
			String current = read.readLine();
			
			while(true){
				if(current == null){
					break;
				}
				
				String[] values = current.split(";");
				
				
				
			}
			
			
			
			
			
			
			read.close();
			
		}
		catch(IOException e){
			System.err.println("Problem reading from file.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating.");
			System.exit(1);
		}
		
		
	}
	
	public static void main(String []args){
		Server theServer = new Server(10);
		theServer.run();
	}
}
