package BackEnd;

import java.io.Serializable;

/**
 * This class holds all ticket information. This includes the ticket holder
 * information, the Date of the flight along with destination, departure time,
 * duration, and seat number. Is a serializable object, so it can be sent to
 * the clients
 * 
 * @author Tevin Schmidt
 *
 */
public class Ticket implements Serializable {


		/**
		 * The serial version number for Ticket
		 */
		private static final long serialVersionUID = 464748L;
		
		/**
		 * The last name of the passenger associated with the ticket
		 */
		private String lastName;
		
		/**
		 * The first name of the passenger associated with the ticket
		 */
		private String firstName;
		
		/**
		 * The date of birth of the passenger associated with the ticket
		 */
		private String dateOfBirth;
		
		/**
		 * The destination associated with the ticket
		 */
		private String destination;
		
		/**
		 * The source of the flight associated with the ticket
		 */
		private String source;
		
		/**
		 * The departure time of the flight associated with the ticket
		 */
		private String departureTime;
		
		/**
		 * The duration of the flight associated with the ticket
		 */
		private String duration;
		
		/**
		 * The seat number associated with the ticket
		 */
		private int seatNumber;
		
		/**
		 * The flight number of the flight associated with the ticket
		 */
		private int flightNumber;
		
		/**
		 * The date of the flight associated with the ticket
		 */
		private String date;
		
		/**
		 * The sate of the ticket, True for available false for not available
		 */
		private boolean available;
		
		/**
		 * Constructs a default Ticket that is empty
		 */
		public Ticket(){
			
		}
		
		/**
		 * Constructs a ticket with the given seat number assigned to it. Initializes all
		 * other members with data from flight.
		 * @param seatNumber is the seat number to be assigned to the ticket
		 * @param theFlight is the flight to initializes members with
		 */
		public Ticket(int seatNumber, Flight theFlight){
			this.lastName = null;
			this.firstName = null;
			this.dateOfBirth = null;
			this.destination = theFlight.getDestination();
			this.source = theFlight.getSource();
			this.departureTime = theFlight.getDepartureTime();
			this.duration = theFlight.getDuration();
			this.seatNumber = seatNumber;
			this.date = theFlight.getDate();
			this.available = true;
			this.flightNumber = theFlight.getFlightNumber();
		}
		
		/**
		 * Sets the last name of the passenger associated with the Ticket
		 * @param lastName is the desired last name
		 */
		public void setLastName(String lastName){
			if(lastName == null){
				this.lastName = null;
			}
			else{
				this.lastName = lastName;
			}
		}
		
		/**
		 * Sets the first name of the passenger associated with the Ticket
		 * @param firstName is the desired first name
		 */
		public void setFirstName(String firstName){
			if(firstName == null){
				this.firstName = null;
			}
			else{
				this.firstName = firstName;
			}
		}
		
		/**
		 * Sets the date of birth of the passenger associated with the Ticket
		 * @param date is the desired date of birth
		 */
		public void setDateOfBirth(String date){
			if(date == null){
				this.dateOfBirth = null;
			}
			else{
				this.dateOfBirth = date;
			}
		}
		
		/**
		 * Sets the destination associated with the Ticket
		 * @param destination is the desired destination
		 */
		public void setDestination(String destination){
			this.destination = destination;
		}
		
		/**
		 * Sets the source associated with the Ticket
		 * @param source is the desired source 
		 */
		public void setSource(String source){
			this.source = source;
		}
		
		/**
		 * Sets the departure time associated with the Ticket
		 * @param departureTime is the desired departure time
		 */
		public void setDepatureTime(String departureTime){
			this.departureTime = departureTime;
		}
		
		/**
		 * Sets the duration associated with the Ticket
		 * @param duration is the desired duration
		 */
		public void setDuration(String duration){
			this.duration = duration;
		}
		
		/**
		 * Sets the seat number associated with the Ticket
		 * @param seatNumber is the desired seat number
		 */
		public void setSeatNumber(int seatNumber){
			this.seatNumber = seatNumber;
		}
		
		/**
		 * Sets the flight number associated with the Ticket
		 * @param flightNumber is the desired flight number
		 */
		public void setFlightNumber(int flightNumber){
			this.flightNumber = flightNumber;
		}
		
		/**
		 * Sets the date associated with the Ticket
		 * @param date
		 */
		public void setDate(String date){
			this.date = date;
		}
		
		/**
		 * Sets the state of the Ticket 
		 * @param state is the desired state of the Ticket
		 */
		public void setAvalable(boolean state){
			this.available = state;
		}
		
		/**
		 * Gets the last name associated with the Ticket
		 * @return the last name associated to the Ticket
		 */
		public String getLastName(){
			return this.lastName;
		}
		
		/**
		 * Gets the first name associated with the Ticket
		 * @return the first name associated with the Ticket
		 */
		public String getFirstName(){
			return this.firstName;
		}
		
		/**
		 * Gets the date of birth associated with the Ticket
		 * @return the date of birth associated with the Ticket
		 */
		public String getDateOfBirth(){
			return this.dateOfBirth;
		}
		
		/**
		 * Gets the destination associated with the Ticket
		 * @return the destination associated with the Ticket
		 */
		public String getDestination(){
			return this.destination;
		}
		
		/**
		 * Gets the source associated with the Ticket
		 * @return the source associated with the Ticket
		 */
		public String getSource(){
			return this.source;
		}
		
		/**
		 * Gets the departure time associated with the Ticket
		 * @return the departure time associated with the Ticket
		 */
		public String getDepartureTime(){
			return this.departureTime;
		}
		
		/**
		 * Gets the duration associated with the Ticket
		 * @return the duration associated with the Ticket
		 */
		public String getDuration(){
			return this.duration;
		}
		
		/**
		 * Gets the seat number associated with the Ticket
		 * @return the seat number associated with the Ticket
		 */
		public int getSeatNumber(){
			return this.seatNumber;
		}
		
		/**
		 * Gets the flight number associated with the Ticket
		 * @return the flight number associated with the Ticket 
		 */
		public int getFlightNumber() {
			return this.flightNumber;
		}
		
		/**
		 * Gets the date associated with the Ticket
		 * @return the date associated with the Ticket
		 */
		public String getDate(){
			return this.date;
		}
		
		/**
		 * Gets the state of the ticket True for an available ticket
		 * false for a booked ticket
		 * @return
		 */
		public boolean getAvailable(){
			return this.available;
		}
			
		
}
