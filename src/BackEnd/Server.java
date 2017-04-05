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
	
	static final int PORTNUM = 3306;
	
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
		synchronized (flights){	
			flights = driver.returnFlights();
		}
	}
	
	public synchronized LinkedList<Flight> search(String source, String destination, String date){
		LinkedList<Flight> rv = new LinkedList<Flight>();
		synchronized (flights) {
			updateFlightList();
			System.out.println(flights.size());
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
		synchronized(flights) {
			updateFlightList();
			LinkedList<Ticket> tempTickets = new LinkedList<Ticket>();
			for(int i = 0; i < flights.size(); i++){
				Flight tempFlight = flights.get(i);
				if(tempFlight.getFlightNumber() == flightNumber){
					tempTickets = tempFlight.getTickets();
					break;
				}
			}
			
			for(int i = 0, j = 1; i < tempTickets.size(); i++, j++){
				Ticket tempTicket = tempTickets.get(i);
				if(tempTicket.getAvailable()){
					updateTicketInDB(j,flightNumber,firstName,lastName,dateOfBirth);
					return tempTicket;
				}
			}
			
		}
		return null;
	}
	
	//have to figure out how to decrement avaliavleSeats
	public synchronized void updateTicketInDB(int seatNum, int flightNumber, String firstName, String lastName, String dateOfBirth){
		Flight tempFlight = searchFlights(flightNumber);
		int seatsLeft = tempFlight.getSeatsAvailable();
		seatsLeft--;
		
		String sqlOut1 = "UPDATE tickets SET firstName='" + firstName + "' WHERE (flightNumber='" + flightNumber + "' AND seatNumber='" + seatNum +"')";
		String sqlOut2 = "UPDATE tickets SET lastName='" + lastName + "' WHERE (flightNumber='" + flightNumber + "' AND seatNumber='" + seatNum +"')";
		String sqlOut3 = "UPDATE tickets SET dateOfBirth='" + dateOfBirth + "' WHERE (flightNumber='" + flightNumber + "' AND seatNumber='" + seatNum +"')";
		String sqlOut4 = "UPDATE tickets SET available='" + 0 + "' WHERE (flightNumber='" + flightNumber + "' AND seatNumber='" + seatNum +"')";
		String sqlOut5 = "UPDATE flights SET seatsAvailable='" + seatsLeft + "' WHERE (flightNumber='" + flightNumber  +"')";
		
		try{
			driver.getState().executeUpdate(sqlOut1);
			driver.getState().executeUpdate(sqlOut2);
			driver.getState().executeUpdate(sqlOut3);
			driver.getState().executeUpdate(sqlOut4);
			driver.getState().executeUpdate(sqlOut5);
		}
		catch(SQLException e){
			System.err.println("There was a problem updating ticket.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}
	
	//have to figure out how to increment availableSeats
	public synchronized void deleteTicket(int flightNumber, int seatNum){	
		Flight tempFlight = searchFlights(flightNumber);
		int seatsLeft = tempFlight.getSeatsAvailable();
		seatsLeft++;
		
		String sqlOut1 = "UPDATE tickets SET firstName='" + null + "' WHERE (flightNumber='" + flightNumber + "' AND seatNumber='" + seatNum +"')";
		String sqlOut2 = "UPDATE tickets SET lastName='" + null + "' WHERE (flightNumber='" + flightNumber + "' AND seatNumber='" + seatNum +"')";
		String sqlOut3 = "UPDATE tickets SET dateOfBirth='" + null + "' WHERE (flightNumber='" + flightNumber + "' AND seatNumber='" + seatNum +"')";
		String sqlOut4 = "UPDATE tickets SET available='" + 1 + "' WHERE (flightNumber='" + flightNumber + "' AND seatNumber='" + seatNum +"')";
		String sqlOut5 = "UPDATE flights SET seatsAvailable='" + seatsLeft + "' WHERE (flightNumber='" + flightNumber  +"')";
		
		try{
			driver.getState().executeUpdate(sqlOut1);
			driver.getState().executeUpdate(sqlOut2);
			driver.getState().executeUpdate(sqlOut3);
			driver.getState().executeUpdate(sqlOut4);
			driver.getState().executeUpdate(sqlOut5);
		}
		catch(SQLException e){
			System.err.println("There was a problem updating ticket.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}
	
	private synchronized Flight searchFlights(int flightNumber){
		synchronized (flights){
			for(int i = 0; i < flights.size(); i++){
				Flight tempFlight = flights.get(i);
				if(tempFlight.getFlightNumber() == flightNumber){
					return tempFlight;
				}
			}
			
			return null;
			
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
