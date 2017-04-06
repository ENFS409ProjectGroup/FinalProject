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
	protected static  String output;
	/**
	 * Returned flights from search
	 */
	protected static LinkedList<Flight> flights;
	protected static LinkedList<Ticket> tickets;
	protected Ticket theTicket;
	
	
	/**
	 * Client constructor 
	 * @param serverName
	 * @param portNumber
	 */
	public Client() {
		try{
			theSocket = new Socket("localhost", 3306);
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
	
	public static void search (String src, String dst, String dt){
		source = src;
		destination = dst;
		date = dt;	
		
		output = "SEARCH";	
		
	} 
	
	public static void book (String fn, String ln, String db, int fnum){
		firstName = fn;
		lastName = ln;
		dob = db;
		flightNumber = fnum;
		
		output = "BOOK";
	}
	
	public static void removeTicket(int fn, int sn){
		flightNumber = fn;
		seatNumber = sn;	
		
		output = "REMOVE";
	}
	public static void addFlight(String dst, String src, String dprt, String dur, String prc, String ts){
		destination = dst;
		source = src;
		departTime = dprt;
		duration = dur;
		price = prc;
		totalSeats = ts;
		
		output = "ADD";
	}

}
