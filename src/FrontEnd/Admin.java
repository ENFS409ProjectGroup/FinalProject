package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import BackEnd.Flight;
import BackEnd.Ticket;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Admin extends Client implements ListSelectionListener {
	
	/**
	 * Booking fields
	 */
	private String src;
	private String dst;
	private String date;
	private String duration;
	private String departTime;
	private int flightNumber;
	private String price;
	
	private String firstName;
	private String lastName;
	private String DOB;
	
	/**
	 * GUI frame and text fields
	 */
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * GUI buttons
	 */
	private JButton btnNewButton;
	private JButton btnCancelTicket;
	private JButton btnViewTicket;
	
	private JList list;
	private JList list_2;
	private DefaultListModel listModel;
	private DefaultListModel listModel_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			Admin window = new Admin();
			window.frame.setVisible(true);
			window.run();
	}

	/**
	 * Create the application.
	 */
	public Admin() {
		super();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 672, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Title
		JTextPane txtpnAirlineRegistration = new JTextPane();
		txtpnAirlineRegistration.setEditable(false);
		txtpnAirlineRegistration.setBackground(SystemColor.menu);
		txtpnAirlineRegistration.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnAirlineRegistration.setText("Airline Registration System (Administrator)");
		txtpnAirlineRegistration.setBounds(78, 11, 313, 27);
		frame.getContentPane().add(txtpnAirlineRegistration);
		
		//"Book Flights"
		JTextPane txtpnBookFlights = new JTextPane();
		txtpnBookFlights.setEditable(false);
		txtpnBookFlights.setBackground(SystemColor.menu);
		txtpnBookFlights.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnBookFlights.setText("Book Flights:");
		txtpnBookFlights.setBounds(10, 43, 100, 21);
		frame.getContentPane().add(txtpnBookFlights);
		
		//"From:"
		JTextPane txtpnFrom = new JTextPane();
		txtpnFrom.setEditable(false);
		txtpnFrom.setBackground(SystemColor.menu);
		txtpnFrom.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnFrom.setText("From:");
		txtpnFrom.setBounds(10, 67, 40, 21);
		frame.getContentPane().add(txtpnFrom);
		
		//From: input
		textField = new JTextField();
		textField.setBounds(94, 67, 117, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//"Destination:"
		JTextPane txtpnDestination = new JTextPane();
		txtpnDestination.setEditable(false);
		txtpnDestination.setBackground(SystemColor.menu);
		txtpnDestination.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnDestination.setText("Destination:");
		txtpnDestination.setBounds(10, 89, 81, 20);
		frame.getContentPane().add(txtpnDestination);
		
		//Destination: input
		textField_1 = new JTextField();
		textField_1.setBounds(94, 89, 117, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		//"Date:"
		JTextPane txtpnDate = new JTextPane();
		txtpnDate.setEditable(false);
		txtpnDate.setBackground(SystemColor.menu);
		txtpnDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnDate.setText("Date:");
		txtpnDate.setBounds(10, 111, 40, 20);
		frame.getContentPane().add(txtpnDate);
		
		//Date: input
		textField_2 = new JTextField();
		textField_2.setBounds(94, 111, 92, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		/**
		 * Displays flights based on search queries
		 */
		JButton btnGetFlights = new JButton("Get Flights");
		btnGetFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				src = textField.getText();
				dst = textField_1.getText();
				date = textField_2.getText();
				
				//ERROR checking to make sure all boxes are populated
				if(textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please make sure all fields are specified.");
					return;
				}
				
				search(src, dst, date); //Get search results from data base			
			}
		});
		btnGetFlights.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGetFlights.setBounds(153, 135, 117, 23);
		frame.getContentPane().add(btnGetFlights);
		
		//Pane where search results are displayed
		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(8);
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setBounds(10, 166, 313, 180);
		frame.getContentPane().add(listScrollPane);
		
		//"Edit flights"
		JTextPane txtpnEditFlights = new JTextPane();
		txtpnEditFlights.setEditable(false);
		txtpnEditFlights.setBackground(SystemColor.menu);
		txtpnEditFlights.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnEditFlights.setText("Edit Flights:");
		txtpnEditFlights.setBounds(345, 49, 81, 21);
		frame.getContentPane().add(txtpnEditFlights);
		
		//Browse Tickets button, populates dislay area with all tickets in the database
		JButton btnBrowseBookedTickets = new JButton("Browse Booked Tickets");
		btnBrowseBookedTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tickets.size() == 0){
					listModel.addElement("Sorry, there are no tickets to display");
					return;
				}
				for(int i = 0; i < tickets.size(); i++){
					listModel.addElement(tickets);
				}
				
			}
		});
		btnBrowseBookedTickets.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowseBookedTickets.setBounds(339, 70, 177, 23);
		frame.getContentPane().add(btnBrowseBookedTickets);
		
		/**
		 * Displays flights based on search query
		 */
		JButton btnNewButton = new JButton("View Flight Details");
		btnNewButton.setEnabled(true);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int index = list.getSelectedIndex();
				Flight value = flights.get(index);
				String temp = "Flight Number: " + value.getFlightNumber() + "  Date: " + value.getDate() + "  Time: " + value.getDepartureTime() + "  From: " + value.getSource() + "  To: " + value.getDestination() + "   Flight Length: " + value.getDuration()
										+ "  Seats Left: " + value.getSeatsAvailable() + "  Price: " + value.getPrice();
				
				UIManager.put("OptionPane.okButtonText", "Book Flight");
				
				//Display additional information from flight HERE
				
				int val = JOptionPane.showOptionDialog(null, temp, "Flight Booking", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if(val == JOptionPane.OK_OPTION){
					
					JPanel panel = new JPanel();
					JTextField field1 = new JTextField(10);
					panel.add(new JLabel("Enter First Name:"));
					panel.add(field1);
					JTextField field2 = new JTextField(10);
					panel.add(new JLabel("Enter Last Name:"));
					panel.add(field2);
					JTextField field3 = new JTextField(10);
					panel.add(new JLabel("Enter Date of Birth (mm-dd-yyyy):"));
					panel.add(field3);
					
					int val_2 = JOptionPane.showConfirmDialog(frame, panel, "Flight Booking", JOptionPane.OK_CANCEL_OPTION);
					
					if(val_2 == JOptionPane.OK_OPTION){
						firstName = field1.getText();
						lastName = field2.getText();
						DOB = field3.getText();
						flightNumber = value.getFlightNumber();
						
						//ERROR check if boxes are empty
						if(field1.getText().equals("") || field2.getText().equals("") || field3.getText().equals("")){
							UIManager.put("OptionPane.okButtonText", "OK");
							JOptionPane.showMessageDialog(null, "Please try again. Make sure all fields are specified."); 
							return;
						}
						
						book(firstName, lastName, DOB, flightNumber); //Send booking info to server

						UIManager.put("OptionPane.okButtonText", "OK");
						JOptionPane.showMessageDialog(null, "Your flight has been booked! Your flight ticket is being printed...");
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 353, 140, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add Flights");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file;
				file = JOptionPane.showInputDialog("Enter the file name:");
				
				
				//call method to read file
				//send contents to database
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(339, 100, 177, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		/**
		 * Allows admin to input a single flight into the database
		 */
		JButton btnNewButton_2 = new JButton("Add Individual Flight");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame addFlight = new JFrame("Add Flight");
				addFlight.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JTextField destination = new JTextField(10);
				JTextField source = new JTextField(10);
				JTextField dprt = new JTextField(10);
				JTextField length = new JTextField(10);		
				JTextField prce = new JTextField(10);
				
				JPanel panel = new JPanel(new BorderLayout(3,3));
				panel.setBorder(new EmptyBorder(5, 5, 5, 5));
				addFlight.setContentPane(panel);
				
				JPanel labels = new JPanel(new GridLayout(0, 1));
				JPanel controls = new JPanel(new GridLayout(0, 1));
				addFlight.getContentPane().add(labels, BorderLayout.WEST);
				addFlight.getContentPane().add(controls, BorderLayout.CENTER);
				
				labels.add(new JLabel("Destination: "));
				controls.add(destination);
				labels.add(new JLabel("Departing From: "));
				controls.add(source);
				labels.add(new JLabel("Departure Time: "));
				controls.add(dprt);
				labels.add(new JLabel("Flight Duration: "));
				controls.add(length);
				labels.add(new JLabel("Price: "));
				controls.add(prce);
				
				int value = JOptionPane.showConfirmDialog(frame, panel, "Add Flight", JOptionPane.OK_CANCEL_OPTION);
				if (value == JOptionPane.OK_OPTION)
				{
					dst = destination.getText();
					src = source.getText();
					duration = length.getText();
					departTime = dprt.getText();
					price = prce.getText();
					
					addFlight(dst, src, departTime, duration, price); //Add ticket to database
					
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(339, 129, 177, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		listModel_2 = new DefaultListModel();
		list_2 = new JList(listModel_2);
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_2.setSelectedIndex(0);
		list_2.addListSelectionListener(this);
		list_2.setVisibleRowCount(8);
		JScrollPane listScrollPane_1 = new JScrollPane(list_2);
		listScrollPane_1.setBounds(333, 166, 313, 180);
		frame.getContentPane().add(listScrollPane_1);		
		
		/**
		 * Cancels ticket and removes it from database. Increases seat count by 1
		 */
		btnCancelTicket = new JButton("Cancel Ticket");
		btnCancelTicket.setEnabled(false);
		btnCancelTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				
				//REmove ticket from data base
				
				tickets.remove(index);
				listModel.removeElementAt(index);
				
				
			}
		});
		btnCancelTicket.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelTicket.setBounds(462, 353, 107, 23);
		frame.getContentPane().add(btnCancelTicket);
		
		btnViewTicket = new JButton("View Ticket");
		btnViewTicket.setEnabled(false);
		btnViewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				
				//Ticket tick = tickets.get(index);
				
				JOptionPane.showMessageDialog(null, "Ticket Details");
			}
		});
		btnViewTicket.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewTicket.setBounds(345, 353, 107, 23);
		frame.getContentPane().add(btnViewTicket);	
		
		/**
		 * Clears user inputs and search results
		 */
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				
				listModel.clear();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClear.setBounds(181, 353, 89, 23);
		frame.getContentPane().add(btnClear);
	}
	
	/**
	 * List Selection method
	 */
	
	public void valueChanged(ListSelectionEvent e) {

	}
		/**
		 * Displays search results in the scroll pane
		 */
		public void updateFlights() {
			if(flights.size() == 0){
				listModel.addElement("Sorry, we do not offer a flight with your specific search results.");
				return;
			}
			for(int i = 0; i < flights.size(); i++){
				listModel.addElement(flights.get(i).getDepartureTime() + "    " + flights.get(i).getSource() + "    " + flights.get(i).getDestination());
			}
		}
		/**
		 * Run method
		 */
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
						
						updateFlights();
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
		
		 /**
		  * After a ticket is booked, ticket is printed to file
		  * @param ticket
		  */
		public static void printTicket(Ticket ticket){
			
			try{
				
				File fout = new File("AirlineTicket"); //Check if file exists
				if(!fout.exists()){
					fout.createNewFile();
				}
				FileWriter fw = new FileWriter(fout, true);
				BufferedWriter bw = new BufferedWriter(fw);
				
				bw.write("===== Airline Ticket Confirmation =====");//Print header
				bw.newLine();
				bw.newLine();
				bw.newLine();
				bw.write("Thank you for booking with us!!");
				bw.newLine();
				bw.newLine();
				bw.write("Flight Itinerary:");
				bw.newLine();
				bw.newLine();
				bw.write("Passenger: " + ticket.getLastName() + ",  " + ticket.getFirstName());
				bw.newLine();
				bw.write("Date of Birth: " + ticket.getDateOfBirth());
				bw.newLine();
				bw.write("Flight Deatils:");
				bw.newLine();
				bw.newLine();
				bw.write("Departure Time: " + ticket.getDepartureTime() + "  From: " + ticket.getSource() + "  To: " + ticket.getDestination());
				bw.newLine();
				bw.write("Flight Duration: " + ticket.getDuration());
				bw.newLine();
				bw.write("Seat Number: " + ticket.getSeatNumber());
				bw.newLine();
				bw.newLine();
				bw.write("Enjoy your flight!!");
				bw.close();
				
			}catch (IOException e){
				System.err.println("Error writing to file" + e.getMessage());
			}
		}
}
