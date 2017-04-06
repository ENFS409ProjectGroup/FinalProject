package FrontEnd;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import BackEnd.Flight;
import BackEnd.Ticket;

public class Client extends Thread {
	/**
	 * Strings needed for search method
	 */
	protected static String source;
	protected static String destination;
	protected static String date;
	protected static String duration;
	protected static String departTime;
	protected static String price;
	protected static String flightNumber;
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
	protected static  String output;
	/**
	 * Returned flights from search
	 */
	protected LinkedList<Flight> flights;
	protected Ticket theTicket;
	
	
	/**
	 * Client constructor 
	 * @param serverName
	 * @param portNumber
	 */
	public Client() {
		try{
			theSocket = new Socket("localhost", 7766);
			socketIn = new ObjectInputStream(theSocket.getInputStream());
			socketOut = new PrintWriter((theSocket.getOutputStream()), true);
			
		}catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void deserializeFlightList() {
		try{
			Object flightIn = socketIn.readObject();
			flights =  new LinkedList<Flight>((LinkedList<Flight>) flightIn );
			flights.get(0).seeFlight();
			System.out.println(flights.get(0).getFlightNumber());
			System.out.println(flights.get(0).getDestination());
			
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
	
	public LinkedList<Flight> getFlights(){
		return flights;
	}
	public static void search (String src, String dst, String dt){
		source = src;
		destination = dst;
		date = dt;	
		
		output = "SEARCH";
		
		System.out.println(source + "\n" + destination + "\n" + date + "\n" + output);
		
		
	} 
	
	// TODO Can you change this so you give me a flight number too, 
	// cuz I need to know which flight to access to book the ticket
	public static void book (String fn, String ln, String db, String fnum){
		firstName = fn;
		lastName = ln;
		dob = db;
		flightNumber = fnum;
		
		output = "BOOK";
	}
	
	public static void removeTicket(String tckt){
		//Will remove string??
		
		output = "REMOVE";
	}
	public static void addFlight(String dst, String src, String dprt, String dur, String prc){
		destination = dst;
		source = src;
		departTime = dprt;
		duration = dur;
		price = prc;
		
		output = "ADD";
	}

}
