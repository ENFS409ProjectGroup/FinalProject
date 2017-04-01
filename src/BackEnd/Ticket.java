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

		private String lastName;
		private String firstName;
		private String destination;
		private String departureTime;
		private String duration;
		private int seatNumber;
		private Date date;
		private boolean available;
		
		/**
		 * Constructs a ticket with the given seat number assigned to it. Initializes all
		 * other members with data from flight.
		 * @param seatNumber is the seat number to be assigned to the ticket
		 * @param theFlight is the flight to initializes members with
		 */
		public Ticket(int seatNumber, Flight theFlight){
			
		}
		
		public void setLastName(String lastName){
			this.lastName = lastName;
		}
		
		public void setFirstName(String firstName){
			this.firstName = firstName;
		}
		
		public void setDestination(String destination){
			this.destination = destination;
		}
		
		public void setDepatureTime(String departureTime){
			this.departureTime = departureTime;
		}
		
		public void setDuration(String duration){
			this.duration = duration;
		}
		
		public void setSeatNumber(int seatNumber){
			this.seatNumber = seatNumber;
		}
		
		public void setDate(Date date){
			this.date = date;
		}
		
		public void setAvalable(boolean state){
			this.available = state;
		}
		
		public String getLastName(){
			return this.lastName;
		}
		
		public String getFirstName(){
			return this.firstName;
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
		
		public int getSeatNumber(){
			return this.seatNumber;
		}
		
		public Date getDate(){
			return this.date;
		}
		
		public boolean getAvailable(){
			return this.available;
		}
		
		/**
		 * Returns a string in the format of a printable ticket
		 * 
		 * *****The format needs to be decided********
		 * 
		 * 
		 * @return
		 */
		public String print(){
			return null;
		}
		
		
		
		
}
