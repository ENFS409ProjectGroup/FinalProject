package BackEnd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 * This class is used as a communicator to the thread pool. The Server 
 * will package a request from the client in a task and send it to the 
 * thread pool to be executed. The execution gets taken care of by the 
 * Manager class.
 * 
 * @author Tevin Schmidt
 * @author James Bews
 * @since March 31, 2017
 *
 */
public class Task extends Thread {
	
	/**
	 * The Keywords associated with the strings being read from the client
	 */
	private static final String SPLITCHAR = "\\t";
	private static final String SEARCH = "SEARCH";
	private static final String BOOK = "BOOK";
	private static final String DELETE = "DELETE";
	
	/**
	 * The reference to the server
	 */
	private Server theServer;
	
	/**
	 * The socket that the client is connected to
	 */
	private Socket theSocket;
	
	/**
	 * The input stream from the client
	 */
	private BufferedReader in;
	
	/**
	 * The output stream to the client
	 */
	private ObjectOutputStream out;
	
	/**
	 * Constructs a task that is linked to the server and a given socket
	 * @param theServer is the reference to the server
	 * @param theSocket is the socket for I/O with client
	 */
	public Task(Server theServer, Socket theSocket){
		this.theServer = theServer;
		this.theSocket = theSocket;
		
		try{
			out = new ObjectOutputStream( theSocket.getOutputStream());
			in = new BufferedReader( new InputStreamReader(theSocket.getInputStream()));
		}
		catch(IOException e){
			System.err.println("Problem creating ObjectOutputStream.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}
	
	/**
	 * Runs the Task Thread. It listens for input 
	 * from the client and decides what to do
	 */
	@Override
	public void run() {
		try{
			while(!theSocket.isClosed()){
				if(in.ready()){
					String newLine = in.readLine();
					System.out.println("Incoming message: " + newLine);
					
					String [] opps = newLine.split(SPLITCHAR);
					
					System.out.println("First keyWord & size: " + opps[0] + " " + opps[0].length());
					System.out.println("Second keyWord & size: " + opps[1] + " " + opps[1].length());
					System.out.println("Third keyWord & size: " + opps[2] + " " + opps[2].length());
					System.out.println("Fourth keyWord & size: " + opps[3] + " " + opps[3].length());


					
					
					if(opps[0].contentEquals(SEARCH)){
						System.out.println("PERFORM SEARCH");
						
						LinkedList<Flight> rv = theServer.search(opps[1], opps[2], opps[3]);
						
						for(int i = 0;  i < rv.size(); i++){
							rv.get(i).seeFlight();
						}
						serializeFlights(rv);
					}
					else if(opps[0].contentEquals(BOOK)){
						System.out.println("PERFROM BOOK");
						
						//Ticket temp = theServer.bookTicket(1, "Tevin", "Schmidt", "02/19/1997");
						
						
						Ticket rv = theServer.bookTicket(Integer.parseInt(opps[1]), opps[2], opps[3], opps[4]);
						serializeTicket(rv);
					}
					else if(opps[0].contentEquals(DELETE)){
						System.out.println("PERFORM DELETE");
						
						
					}
					
				}
			}
			
			System.out.println("Client disconected.");
		}
		catch(IOException e){
			System.err.println("Problem reading from client.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
		
		try{
			in.close();
			out.close();
			theSocket.close();
		}
		catch(IOException e){
			System.err.println("Error closing streams.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
		
	}
	
	/**
	 * Serialize the flight list to send to the client
	 * @param toSend is the LnikeList of flights to send to client
	 */
	public void serializeFlights(LinkedList<Flight> toSend){
		try{
			out.writeObject(toSend);
		}
		catch(IOException e){
			System.err.println("Error writing to file.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}
	
	/**
	 * Serialize a ticket to send to the client
	 * @param toSend is the Ticket to send to the client
	 */
	public void serializeTicket(Ticket toSend){
		try{
			out.writeObject(toSend);
		}
		catch(IOException e){
			System.err.println("Error writing to file.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
	}
	

}
