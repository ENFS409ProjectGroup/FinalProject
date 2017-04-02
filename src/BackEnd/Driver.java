package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Driver {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	private static String jdbcDriver =  "com.mysql.jdbc.Driver";
	private static String dbAddress = "jdbc:mysql://localhost:3306/";
	private static String theDB = "AirLineCatalogue";
	private static String userName = "Client";
	private static String password = "Client";
	
	private static final String FLIGHTS_TABLE_NAME = "flights";
	private static final String FLIGHTS_COLUMN_FLIGHTNUMBER = "flightNumber";
	private static final String FLIGHTS_COLUMN_DESTINATION = "destination";
	private static final String FLIGHTS_COLUMN_DEPARTURETIME = "departureTime";
	private static final String FLIGHTS_COLUMN_DURATION = "duration";
	private static final String FLIGHTS_COLUMN_SEATSAVAILABLE = "seatsAvailable";
	private static final String FLIGHTS_COLUMN_PRICE = "price";
	private static final String FLIGHTS_COLUMN_DATE = "date";
	
		
	private static final String TICKETS_TABLE_NAME = "tickets";
	private static final String TICKETS_COLUMN_FLIGHTNUMBER = "flightNumber";
	private static final String TICKETS_COLUMN_SEATNUMBER = "seatNumber";
	private static final String TICKETS_COLUMN_LASTNAME = "lastName";
	private static final String TICKETS_COLUMN_FIRSTNAME = "firstName";
	private static final String TICKETS_COLUMN_DESTINATION = "destination";
	private static final String TICKETS_COLUMN_DEPARTURETIME = "depatureTime";
	private static final String TICKETS_COLUMN_DURATION = "duration";
	private static final String TICKETS_COLUMN_DATE = "date";
	private static final String TICKETS_COLUMN_AVALIABLE = "avaliable";
	
	
	  private static final String EMPLOYEE_TABLE = "create table MyEmployees3 ( "
		      + "   id INT PRIMARY KEY, firstName VARCHAR(20), lastName VARCHAR(20), "
		      + "   title VARCHAR(20), salary INT )";
	
	public Driver(){
		try{
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(dbAddress, userName, password);
			statement = connection.createStatement();
			resultSet = null;
			statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + theDB + ";" );
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
				FLIGHTS_COLUMN_DEPARTURETIME + " TEXT, " +
				FLIGHTS_COLUMN_DURATION + " TEXT, " +
				FLIGHTS_COLUMN_SEATSAVAILABLE + " TEXT, " +
				FLIGHTS_COLUMN_PRICE + " TEXT, " +
				FLIGHTS_COLUMN_DATE + " TEXT " + ")");
		
		String query2 = new String("CREATE TABLE IF NOT EXISTS " + TICKETS_TABLE_NAME + "(" +
				TICKETS_COLUMN_FLIGHTNUMBER + " INTEGER PRIMARY KEY, " +
				TICKETS_COLUMN_SEATNUMBER + " INTEGER, " +
				TICKETS_COLUMN_LASTNAME + " TEXT, " + 
				TICKETS_COLUMN_FIRSTNAME + " TEXT, " + 
				TICKETS_COLUMN_DESTINATION + " TEXT, " +
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
}
