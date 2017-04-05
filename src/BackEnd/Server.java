package BackEnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
	
	static final int PORTNUM = 7766;
	
	private Driver driver;
	private ServerSocket serverSocket;
	private Socket socket;
	
	private LinkedList<Flight> flights;
	


	
	public Server(int numThreads){
		driver = new Driver();
		driver.initialize();
		flights = new LinkedList<Flight>();
		

		
	}
	
	public void run(){
		ThreadPool pool = new ThreadPool(6);
		
		try{
			serverSocket = new ServerSocket(PORTNUM);
		}
		catch(IOException e){
			System.err.println("There was an error creating the serverSocket.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
		
		while(true){
			try{
				System.out.println("Waiting For client.");
				socket = serverSocket.accept();
			}
			catch(IOException e){
				System.err.println("Error connecting to socket.");
				System.err.println(e.getMessage());
				System.err.println("Program terminating...");
				System.exit(1);
			}
			
			Task newTask = new Task(this, socket);
			System.out.println("Client has connected.");
			pool.execute(newTask);	
		
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
			
			insertFlights(flights);
			
			read.close();
			
		}
		catch(IOException e){
			System.err.println("Problem reading from file.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating.");
			System.exit(1);
		}
		
		
	}
	
	public void insertFlights(LinkedList<Flight> flights){
		for(int i = 0; i < flights.size(); i++){
			String [] toInsert = new String[8];
			toInsert[0] = flights.get(i).getDestination();
			toInsert[1] = flights.get(i).getSource();
			toInsert[2] = flights.get(i).getDepartureTime();
			toInsert[3] = flights.get(i).getDuration();
			toInsert[4] = Integer.toString(flights.get(i).getTotalSeats());
			toInsert[5] = Integer.toString(flights.get(i).getSeatsAvailable());
			toInsert[6] = Float.toString(flights.get(i).getPrice());
			toInsert[7] = flights.get(i).getDate();
			
			String sqlOut = "insert into flights"
					+ "(flightNumber, destination, source, departureTime, duration, totalSeats, seatsAvailable, price, date)"
					+ "values ('" + flights.get(i).getFlightNumber() + "', '"
					+ toInsert[0] + "', '" + toInsert[1] + "', '" + toInsert[2] + "', '" 
					+ toInsert[3] + "', '" + toInsert[4] + "', '" + toInsert[5] + "', '" 
					+ toInsert[6] + "', '" + toInsert[7] + "')";
			
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
	}
	
	public void insertTickets(LinkedList<Ticket> tickets, int flightNumber){
		for(int i = 0, j = 1; i < tickets.size(); i++, j++){
			String [] toInsert = new String[8];
			toInsert[0] = tickets.get(i).getLastName();
			toInsert[1] = tickets.get(i).getFirstName();
			toInsert[2] = null;
			toInsert[3] = tickets.get(i).getDestination();
			toInsert[4] = tickets.get(i).getSource();
			toInsert[5] = tickets.get(i).getDepartureTime();
			toInsert[6] = tickets.get(i).getDuration();
			toInsert[7] = tickets.get(i).getDate();
			
			String sqlOut = "insert into tickets"
					+ "(flightNumber, seatNumber, lastName, firstName, dateOfBirth, destination, source, departureTime, duration, date, available)"
					+ "values ('" + flightNumber + "', '" + j + "', '" + toInsert[0] + "', '" + toInsert[1] + "', '" 
					+ toInsert[2] + "', '" + toInsert[3] + "', '" + toInsert[4] + "', '"
					+ toInsert[5] + "', '" + toInsert[6] + "', '" + toInsert[7] + "', '" + 1 + "')";
		
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
		String sqlOut = "delete from flights where flightNumber='" + Integer.parseInt(toDelete) + "'";
		
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
	
	public void deleteFlightTickets(String toDelete){
		String sqlOut = "delete from tickets where flightNumber='" + Integer.parseInt(toDelete) + "'";
		
		try{
			driver.getState().executeUpdate(sqlOut);
		}
		catch(SQLException e){
			System.err.println("There was a problem deleting from tickets.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}
	
	public synchronized void updateFlightList(){
			flights = driver.returnFlights();
	}
	
	public synchronized LinkedList<Flight> search(String source, String destination, String date){
		LinkedList<Flight> rv = new LinkedList<Flight>();
		synchronized (flights) {
			updateFlightList();
			for(int i = 0; i < flights.size(); i++){
				Flight tempFlight = flights.get(i);
				if(tempFlight.getSource().contentEquals(source) 
						&& tempFlight.getDestination().contentEquals(destination) 
						&& tempFlight.getDate().contentEquals(date)){
					
					rv.add(tempFlight);
					
				}
			}
		}
		
		return rv;
	}
	
	public synchronized Ticket bookTicket(int flightNumber, String firstName, String lastName, String dateOfBirth){
/*		synchronized(flights) {
			updateFlightList();
			for()
			
		}*/
		return null;
	}
	
	public synchronized void deleteTicket(Ticket toDelete){
		
	}
	
	public static void main(String []args){
		Server theServer = new Server(10);
		if(args.length == 1){
			theServer.addFlightList(args[0]);
		}
		theServer.run();
	}
}
