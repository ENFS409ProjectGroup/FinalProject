package BackEnd;

import java.util.LinkedList;

/**
 * This class holds all information for a given flight. It also contains
 * the list of available tickets that are able to be booked or unbooked.
 * 
 * @author Tevin Schmidt
 *
 */
public class Flight {
		
	private LinkedList<Ticket> tickets;
	private int flightNumber;
	private int seatsAvailable;
	private String source;
	private String destination;
	private String departureTime;
	private String duration;
	private float price;
	private Date date;
	
	public Flight(){
		
	}
	
	private void createTickets(int numberOfSeats){
		
	}
	
	public void addPassanger(Passenger newPass){
		
	}
	
	public void removePassanger(Passenger toRemove){
		
	}

}
