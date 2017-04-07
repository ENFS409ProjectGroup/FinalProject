package FrontEnd;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import BackEnd.Flight;
import BackEnd.Ticket;

public class Client extends Thread {
	
	private static final int PORTNUM = 3306;
	
	/**
	 * Strings needed for search method
	 */
	protected static String source;
	protected static String destination;
	protected static String date;
	protected static String duration;
	protected static String departTime;
	protected static String price;
	protected static int flightNumber;
	protected static String totalSeats;	
	protected static int seatNumber;
	/**
	 * Strings needed for book method
	 */
	
	protected static String firstName;
	protected static String lastName;
	protected static String dob;
	/**
	 * Socket variables	
	 */
	protected PrintWriter socketOut;
	protected Socket theSocket;
	protected ObjectInputStream socketIn;
	protected ObjectOutputStream sendFile;
	protected static  String output;
	protected static LinkedList<Flight> fromFile;
	/**
	 * Returned flights from search
	 */
	protected static LinkedList<Flight> flights;
	protected static LinkedList<Ticket> tickets;
	protected Ticket theTicket;
	/**
	 * Current Date
	 */
	protected static String currentDate;
	
	/**
	 * Client constructor 
	 * @param serverName
	 * @param portNumber
	 */
	public Client() {
		try{
			theSocket = new Socket("localhost", PORTNUM);
			sendFile = new ObjectOutputStream(theSocket.getOutputStream());
			socketIn = new ObjectInputStream(theSocket.getInputStream());
			socketOut = new PrintWriter((theSocket.getOutputStream()), true);
			
		}catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	/**
	 * Deserialize incoming flight list from server
	 */
	@SuppressWarnings("unchecked")
	public void deserializeFlightList() {
		try{
			Object flightIn = socketIn.readObject();
			flights = new LinkedList<Flight>( (LinkedList<Flight>) flightIn );
			
			
		}
		catch(IOException e){
			System.err.println("Error geting input.");
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
			System.err.println("Program terminating...");
			System.exit(1);
		}
		catch(ClassNotFoundException e){
			System.err.println("Error reconizing class for deserialization.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}
	/**
	 * Deserialize incoming ticket list from server
	 */
	@SuppressWarnings("unchecked")
	public void deserializeTicketList() {
		try{
			Object ticketIn = socketIn.readObject();
			tickets = new LinkedList<Ticket>( (LinkedList<Ticket>) ticketIn );
			
			
		}
		catch(IOException e){
			System.err.println("Error geting input.");
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
			System.err.println("Program terminating...");
			System.exit(1);
		}
		catch(ClassNotFoundException e){
			System.err.println("Error reconizing class for deserialization.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}

	public void deserializeTicket(){
		try{
			theTicket = (Ticket) socketIn.readObject();
		}
		catch(IOException e){
			System.err.println("Error geting input.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
		catch(ClassNotFoundException e){
			System.err.println("Error reconizing class for deserialization.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}
	
	/**
	 * Prompts server to search database for flight based on passenger and admin GUI search query
	 * @param src
	 * @param dst
	 * @param dt
	 */
	public static void search (String src, String dst, String dt){
		source = src;
		destination = dst;
		date = dt;	
		
		output = "SEARCH";	
		
	} 
	/**
	 * Prompts sever to return a linked list of flights
	 */
	public static void getTickets(){
		output = "TICKETS";
	}
	/**
	 * Prompts server to book a specified flight from passenger or admin GUI
	 * @param fn: First name of passenger
	 * @param ln: Last name of passenger
	 * @param db
	 * @param fnum
	 */
	public static void book (String fn, String ln, String db, int fnum){
		firstName = fn;
		lastName = ln;
		dob = db;
		flightNumber = fnum;
		
		output = "BOOK";
	}
	
	/**
	 * Prompts server to remove a specific ticket from database
	 * @param fn
	 * @param sn
	 */
	public static void removeTicket(int fn, int sn){
		flightNumber = fn;
		seatNumber = sn;	
		
		output = "REMOVE";
	}
	
	/**
	 * Creates string of flight info to send to server upon adding a new flight through Admin GUI
	 * @param dst
	 * @param src
	 * @param dprt
	 * @param dur
	 * @param prc
	 * @param theDate
	 * @param ts
	 */
	public static void addFlight(String dst, String src, String dprt, String dur, String prc, String theDate, String ts){
		destination = dst;
		source = src;
		departTime = dprt;
		duration = dur;
		price = prc;
		totalSeats = ts;
		date = theDate;
		
		output = "ADD";
	}
	
	/**
	 * Returns current date
	 * @return
	 */
	public static String getCurrentDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.now();
		String date = (dtf.format(localDate));
		
		return date;
	} 
	
	/**
	 * Informs server to disconnect client upon exiting GUI
	 */
	public void disconnect(){
		socketOut.println("END");
	}
	
	/**
	 * Serialize the flight list to send to the client
	 * @param toSend is the LnikeList of flights to send to client
	 */
	public void serializeFlights(LinkedList<Flight> toSend){
		try{
			output = "FILE";
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sendFile.writeObject(toSend);
			
		}
		catch(IOException e){
			System.err.println("Error writing to file.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}

}
