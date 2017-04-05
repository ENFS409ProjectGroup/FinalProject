package BackEnd;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * This class holds all information for a given flight. It also contains
 * the list of available tickets that are able to be booked or unbooked.
 * 
 * @author Tevin Schmidt
 *
 */
public class Flight implements Serializable{
		
	private static final long serialVersionUID = 4748L;
	
	private LinkedList<Ticket> tickets;
	private int flightNumber;
	private int totalSeats;
	private int seatsAvailable;
	private String source;
	private String destination;
	private String departureTime;
	private String duration;
	private float price;
	private String date;
	
	public Flight(){
		this.tickets = new LinkedList<Ticket>();
	}
	
	public Flight(String [] methods){
		if(methods.length == 9){
			this.flightNumber = Integer.parseInt(methods[0]);
			this.destination = methods[1];
			this.source = methods[2];
			this.departureTime = methods[3];
			this.duration = methods[4];
			this.totalSeats = Integer.parseInt(methods[5]);
			this.seatsAvailable = Integer.parseInt(methods[6]);
			this.price = Float.parseFloat(methods[7]);
			this.date = methods[8];
			
			createTickets(totalSeats);

		}
	}
	
	public void setFlightNumber(int flightNumber){
		this.flightNumber = flightNumber;
	}
	
	public void setTotalSeats(int totalSeats){
		this.totalSeats = totalSeats;
	}
	
	public void setAvailable(int seatsAvailable){
		this.seatsAvailable = seatsAvailable;
	}
	
	public void setSource(String source){
		this.source = source;
	}
	
	public void setDestination(String destination){
		this.destination = destination;
	}
	
	public void setDepartureTime(String departureTime){
		this.departureTime = departureTime;
	}
	
	public void setDuration(String duration){
		this.duration = duration;
	}
	
	public void setPrice(float price){
		this.price = price;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setTickets(LinkedList<Ticket> tickets){
		this.tickets = tickets;
	}
	
	public int getFlightNumber(){
		return this.flightNumber;
	}
	
	public int getTotalSeats(){
		return this.totalSeats;
	}
	
	public int getSeatsAvailable(){
		return this.seatsAvailable;
	}
	
	public String getSource(){
		return this.source;
	}
	
	public String getDestination(){
		return this.destination;
	}
	
	public String getDepartureTime(){
		return this.departureTime;
	}
	
	public String getDuration(){
		return this.duration;
	}
	
	public float getPrice(){
		return this.price;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public LinkedList<Ticket> getTickets(){
		return this.tickets;
	}
	
	private void createTickets(int numberOfSeats){
		tickets = new LinkedList<Ticket>();
		for(int i = 1; i <= numberOfSeats; i++){
			tickets.add(new Ticket(i, this));
		}
	}
	
	public void seeFlight(){
		System.out.println(flightNumber + " " + source + " " + destination + " " + date + "SEE");
	}

}
