package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


public class Driver {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	private static String jdbcDriver =  "com.mysql.jdbc.Driver";
	private static String dbAddress = "jdbc:mysql://localhost:7766/";
	private static String SSL = "/?autoReconnect=true&useSSL=false";
	private static String theDB = "AirLineCatalogue";
	private static String userName = "root";
	private static String password = "1993MF1967";
	
	private static final String FLIGHTS_TABLE_NAME = "flights";
	private static final String FLIGHTS_COLUMN_FLIGHTNUMBER = "flightNumber";
	private static final String FLIGHTS_COLUMN_DESTINATION = "destination";
	private static final String FLIGHTS_COLUMN_SOURCE = "source";
	private static final String FLIGHTS_COLUMN_DEPARTURETIME = "departureTime";
	private static final String FLIGHTS_COLUMN_DURATION = "duration";
	private static final String FLIGHTS_COLUMN_TOTALSEATS = "totalSeats";
	private static final String FLIGHTS_COLUMN_SEATSAVAILABLE = "seatsAvailable";
	private static final String FLIGHTS_COLUMN_PRICE = "price";
	private static final String FLIGHTS_COLUMN_DATE = "date";
	
		
	private static final String TICKETS_TABLE_NAME = "tickets";
	private static final String TICKETS_COLUMN_REFNUM = "refNum";
	private static final String TICKETS_COLUMN_FLIGHTNUMBER = "flightNumber";
	private static final String TICKETS_COLUMN_SEATNUMBER = "seatNumber";
	private static final String TICKETS_COLUMN_LASTNAME = "lastName";
	private static final String TICKETS_COLUMN_FIRSTNAME = "firstName";
	private static final String TICKETS_COLUMN_DATEOFBIRTH = "dateOfBirth";
	private static final String TICKETS_COLUMN_DESTINATION = "destination";
	private static final String TICKETS_COLUMN_SOURCE = "source";
	private static final String TICKETS_COLUMN_DEPARTURETIME = "departureTime";
	private static final String TICKETS_COLUMN_DURATION = "duration";
	private static final String TICKETS_COLUMN_DATE = "date";
	private static final String TICKETS_COLUMN_AVALIABLE = "available";
	
	
	public Driver(){
		try{
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(dbAddress, userName, password);
			statement = connection.createStatement();
			resultSet = null;
			statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + theDB +";" );
			connection = DriverManager.getConnection(dbAddress + theDB, userName, password);
			statement = connection.createStatement();
		}
		catch(ClassNotFoundException e){
			System.err.println("Error locating MySQl connector." );
			System.err.println(e.getMessage());
			System.err.println("Connection to data base could not be made, program terminating...");
			System.exit(1);
		}
		catch(SQLException e){
			System.err.println("Error connecting to MySQL.");
			System.err.println(e.getMessage());
			//e.printStackTrace();
			System.err.println("Conection to data base could not be made, program terminating...");
			System.exit(1);
		}
	}
	
	public Connection getCon(){
		return connection;
	}
	
	public Statement getState(){
		return statement;
	}
	
	public ResultSet getResult(){
		return resultSet;
	}
	
	public void setCon(Connection connection){
		this.connection = connection;
	}
	
	public void setState(Statement statement){
		this.statement = statement;
	}
	
	public void setResult(ResultSet resultSet){
		this.resultSet = resultSet;
	}

	public void initialize() {
		
		String query1 = new String("CREATE TABLE IF NOT EXISTS " + FLIGHTS_TABLE_NAME + " (" +
				FLIGHTS_COLUMN_FLIGHTNUMBER + " INTEGER PRIMARY KEY, " +
				FLIGHTS_COLUMN_DESTINATION + " TEXT, " +
				FLIGHTS_COLUMN_SOURCE + " TEXT, " +
				FLIGHTS_COLUMN_DEPARTURETIME + " TEXT, " +
				FLIGHTS_COLUMN_DURATION + " TEXT, " +
				FLIGHTS_COLUMN_TOTALSEATS + " TEXT, " +
				FLIGHTS_COLUMN_SEATSAVAILABLE + " TEXT, " +
				FLIGHTS_COLUMN_PRICE + " TEXT, " +
				FLIGHTS_COLUMN_DATE + " TEXT " + ")");
		
		String query2 = new String("CREATE TABLE IF NOT EXISTS " + TICKETS_TABLE_NAME + "(" +
				TICKETS_COLUMN_REFNUM + " INTEGER PRIMARY KEY AUTO_INCREMENT, " +
				TICKETS_COLUMN_FLIGHTNUMBER + " INTEGER, " +
				TICKETS_COLUMN_SEATNUMBER + " INTEGER, " +
				TICKETS_COLUMN_LASTNAME + " TEXT, " + 
				TICKETS_COLUMN_FIRSTNAME + " TEXT, " + 
				TICKETS_COLUMN_DATEOFBIRTH + " TEXT, " +
				TICKETS_COLUMN_DESTINATION + " TEXT, " +
				TICKETS_COLUMN_SOURCE + " TEXT, " +
				TICKETS_COLUMN_DEPARTURETIME + " TEXT, " +
				TICKETS_COLUMN_DURATION + " TEXT, " +
				TICKETS_COLUMN_DATE + " TEXT, " +
				TICKETS_COLUMN_AVALIABLE + " INTEGER " + ")");
		
		try{
			statement.executeUpdate(query1);
			statement.executeUpdate(query2);
		}
		catch(SQLException e){
			System.err.println("Problem creating tables.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}		
		
	}
	
	public LinkedList<Flight> returnFlights(){
		LinkedList<Flight> rv = new LinkedList<Flight>();
		try{
			resultSet = statement.executeQuery("select * from flights");
			while(resultSet.next()){
				Flight tempFlight = new Flight();
				tempFlight.setFlightNumber(resultSet.getInt("flightNumber"));
				tempFlight.setDestination(resultSet.getString("destination"));
				tempFlight.setSource(resultSet.getString("source"));
				tempFlight.setDepartureTime(resultSet.getString("departureTime"));
				tempFlight.setDuration(resultSet.getString("duration"));
				tempFlight.setTotalSeats(Integer.parseInt(resultSet.getString("totalSeats")));
				tempFlight.setAvailable(Integer.parseInt(resultSet.getString("seatsAvailable")));
				tempFlight.setPrice(Float.parseFloat(resultSet.getString("price")));
				tempFlight.setDate(resultSet.getString("date"));
				
				tempFlight.setTickets(returnTickets(tempFlight.getFlightNumber()));
				
				rv.add(tempFlight);
			}
		}
		catch(SQLException e){
			System.err.println("Problem updating from database.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
		
		return rv;
	}
	
	public LinkedList<Ticket> returnTickets(int flightNumber){
		LinkedList<Ticket> rv = new LinkedList<Ticket>();
		try{
			resultSet = statement.executeQuery("select * from tickets");
			while(resultSet.next()){
				if(resultSet.getInt("flightNumber") == flightNumber ){
					Ticket tempTicket = new Ticket();
					tempTicket.setSeatNumber(resultSet.getInt("seatNumber"));
					tempTicket.setLastName(resultSet.getString("lastName"));
					tempTicket.setFirstName(resultSet.getString("firstName"));
					tempTicket.setDateOfBirth(resultSet.getString("dateOfBirth"));
					tempTicket.setDestination(resultSet.getString("destination"));
					tempTicket.setSource(resultSet.getString("departureTime"));
					tempTicket.setDuration(resultSet.getString("duration"));
					tempTicket.setDate(resultSet.getString("date"));
					if(resultSet.getInt("available") == 1){
						tempTicket.setAvalable(true);
					}
					else{
						tempTicket.setAvalable(false);
					}
					
					rv.add(tempTicket);
				}
			}
			
		}
		catch(SQLException e){
			System.err.println("Problem updating from database.");
			System.err.println(e.getMessage());
			System.err.println("Program terminating...");
			System.exit(1);
		}
		
		return rv;
	}
}
