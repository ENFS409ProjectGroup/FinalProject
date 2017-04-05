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
	private static String source;
	private static String destination;
	private static String date;
	private static String duration;
	private static String departTime;
	private static String price;
	private static String flightNumber;
	/**
	 * Strings needed for book method
	 */
	
	private static String firstName;
	private static String lastName;
	private static String dob;
	/**
	 * Socket variables	
	 */
	private PrintWriter socketOut;
	private Socket theSocket;
	private ObjectInputStream socketIn;
	private static  String output;
	/**
	 * Returned flights from search
	 */
	LinkedList<Flight> flights;
	private Ticket theTicket;
	
	
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
	public void run() {
		output = "";
		System.out.println("Entered Run state.");
		while(true) {
			try{
				if(output.contentEquals("SEARCH")){
					System.out.println("Send search query.");
					socketOut.println(output + "\t" + source + "\t" + destination + "\t" + date);
					output = "";
					deserializeFlightList();
				}
				else if(output.contentEquals("BOOK")){
					System.out.println("Send book query.");
					socketOut.println(output + "\t" + firstName + "\t" + lastName + "\t" + dob + "\t" + flightNumber);
					output = "";
					deserializeTicket();
				}
				else if(output.contentEquals("REMOVE")){
					System.out.println("Send remove ticket query.");
					socketOut.println(output);
					//more
				}
				else if(output.contentEquals("ADD")){
					System.out.println("Send Add Flight query.");
					socketOut.println(output);
					//more
				}
				else{
					System.out.println("Waiting...");
					sleep(1000);
				}
				
				
			}catch (Exception e){
				
				System.err.println(e.getMessage());
				e.printStackTrace(System.err);
				break;				
			}
		}
		try {
			socketIn.close();
			socketOut.close();
		}catch (IOException e){
			System.err.println("Error Closing: " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserializeFlightList() {
		try{
			Object flightIn = socketIn.readObject();
			flights = new LinkedList<Flight>( (LinkedList<Flight>) flightIn );
			//flights.get(0).seeFlight();
			
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
