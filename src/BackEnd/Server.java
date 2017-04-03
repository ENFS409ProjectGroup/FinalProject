package BackEnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
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
		flights = new LinkedList<Flight>();
		
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
				Flight newFlight = new Flight(values);
				flights.add(newFlight);
				insertTickets(newFlight.getTickets(), newFlight.getFlightNumber());
				
				current = read.readLine();
				
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
	
	public void insertTickets(LinkedList<Ticket> tickets, int flightNumber){
		for(int i = 1; i <= tickets.size(); i++){
			String [] toInsert = new String[7];
			toInsert[0] = tickets.get(i).getLastName();
			toInsert[1] = tickets.get(i).getFirstName();
			toInsert[2] = tickets.get(i).getDestination();
			toInsert[3] = tickets.get(i).getSource();
			toInsert[4] = tickets.get(i).getDepartureTime();
			toInsert[5] = tickets.get(i).getDuration();
			toInsert[6] = tickets.get(i).getDate().printDate();
			
			String sqlOut = "insert into tickets"
					+ "(flightNumber, seatNumber, lastName, firstName, destination, source, departureTime, duration, date, availiable)"
					+ "values ('" + flightNumber + "', '" + i + "', '" + toInsert[0] + "', '" + toInsert[1] + "', " 
					+ toInsert[2] + "', '" + toInsert[3] + "', '" + toInsert[4] + "', '"
					+ toInsert[5] + "', '" + toInsert[6] + "', '" + 1 + "')";
		
			try{
				driver.getState().executeUpdate(sqlOut);
			}
			catch(SQLException e){
				System.err.println("There was a problem inserting to tickets.");
				System.err.println(e.getMessage());
				System.err.println("Program terminating...");
				System.exit(1);
			}
		}
	}
	
	public void insertFlight(String[] toInsert){
		String sqlOut = "insert into flights"
				+ " (flightNumber, destination, source, departureTime, duration, totalSeats, seatAvailable, price, date)"
				+ "values ('" + Integer.parseInt(toInsert[0]) + "', '" + toInsert[1] + "', '" 
				+ toInsert[2] + "', '" + toInsert[3] + "', '" + toInsert[4] + "', '"
				+ toInsert[5] + "', '" + toInsert[6] + "', '" + toInsert[7] + "', '"
				+ toInsert[8] + "')";
		
		try{
			driver.getState().executeUpdate(sqlOut);
		}
		catch(SQLException e){
			System.err.println("There was a problem inserting to flights.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}
	
	public void deleteFlight(String toDelete){
		String sqlOut = "delete from flights where flightNumber='" + toDelete + "'";
		
		try{
			driver.getState().executeUpdate(sqlOut);
		}
		catch(SQLException e){
			System.err.println("There was a problem deleting from flights.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}
	
	public static void main(String []args){
		Server theServer = new Server(10);
		if(args.length == 1){
			theServer.addFlightList(args[0]);
		}
		theServer.run();
	}
}
