package FrontEnd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import BackEnd.Flight;

public class Client extends Thread {
	/**
	 * Strings needed for search method
	 */
	private static String source;
	private static String destination;
	private static String date;
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
	private BufferedReader socketIn;
	private static String input;
	private static  String output;
	/**
	 * Returned flights from search
	 */
	private LinkedList<Flight> flights;
	
	
	/**
	 * Client constructor 
	 * @param serverName
	 * @param portNumber
	 */
	public Client() {
		try{
			theSocket = new Socket("localhost", 7766);
			socketIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
			socketOut = new PrintWriter((theSocket.getOutputStream()), true);
			
		}catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	public void run() {
		input = "";
		output = "";
		System.out.println("Entered Run state.");
		while(true) {
			try{
				if(output.contentEquals("SEARCH")){
					System.out.println("Send search query.");
					socketOut.println(output + "\t" + source + "\t" + destination + "\t" + date);
					output = "";
					//input = socketIn.readLine();
				}
				else if(output.contentEquals("BOOK")){
					System.out.println("Send book query.");
					socketOut.println(output + "\t" + firstName + "\t" + lastName + "\t" + dob);
					output = "";
					//input = socketIn.readLine();
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
	
	public void deserialize() {
		//deserialize ticket
	}
	public static void search (String src, String dst, String dt){
		source = src;
		destination = dst;
		date = dt;	
		
		output = "SEARCH";
		
		System.out.println(source + "\n" + destination + "\n" + date + "\n" + output);
		
		
	} 
	
	public static void book (String fn, String ln, String db){
		firstName = fn;
		lastName = ln;
		dob = db;
		
		output = "BOOK";
	}

}
