package BackEnd;


/**
 * This class processes the passenger info, and books a ticket in a flight
 * 
 * @author Tevin Schmidt
 *
 */
public class Booking {
		
	
		/**
		 * This books the ticket by only letting one person book at a time, returns the ticket to 
		 * sent to the client.
		 * @param flight
		 * @param passanger
		 * @return
		 */
		public synchronized Ticket book(Flight flight, Passenger passanger){
			return null;
		}
}
