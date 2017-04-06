package FrontEnd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import BackEnd.Flight;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class Passenger extends Client implements ListSelectionListener{

	private String test = "Calgary Halifax 01/17/18";
	
	private Client theClient;
	
	//Input from search query
	private String src;
	private String dst;
	private String date;
	
	//Input from booking ticket query
	private String firstName;
	private String lastName;
	private String DOB;
		
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private JButton btnSelect;
	
	private JList list;
	private DefaultListModel listModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Passenger window = new Passenger();					
		window.frame.setVisible(true);
		window.run();


	}

	/**
	 * Create the application.
	 */
	public Passenger() {
		super();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 505, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Display header
		JTextPane txtpnWelcomeToAirline = new JTextPane();
		txtpnWelcomeToAirline.setBackground(SystemColor.menu);
		txtpnWelcomeToAirline.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnWelcomeToAirline.setText("Welcome to Airline Registration System");
		txtpnWelcomeToAirline.setBounds(100, 11, 282, 25);
		frame.getContentPane().add(txtpnWelcomeToAirline);
		
		//Display title
		JTextPane txtpnBookAFlight = new JTextPane();
		txtpnBookAFlight.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnBookAFlight.setBackground(SystemColor.menu);
		txtpnBookAFlight.setText("Book a flight:");
		txtpnBookAFlight.setBounds(10, 50, 89, 21);
		frame.getContentPane().add(txtpnBookAFlight);
		
		// Display "From:"
		JTextPane txtpnDeparture = new JTextPane();
		txtpnDeparture.setForeground(new Color(0, 0, 0));
		txtpnDeparture.setBackground(SystemColor.menu);
		txtpnDeparture.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnDeparture.setText("From:");
		txtpnDeparture.setBounds(10, 82, 40, 20);
		frame.getContentPane().add(txtpnDeparture);
		
		//Display "Destination:"
		JTextPane txtpnTo = new JTextPane();
		txtpnTo.setBackground(SystemColor.menu);
		txtpnTo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnTo.setText("Destination:");
		txtpnTo.setBounds(206, 82, 81, 25);
		frame.getContentPane().add(txtpnTo);
		
		//"From:" input box
		textField = new JTextField();
		textField.setBounds(52, 82, 150, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//Destination input box
		textField_1 = new JTextField();
		textField_1.setBounds(290, 82, 134, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		//Display "Date:"
		JTextPane txtpnDate = new JTextPane();
		txtpnDate.setBackground(SystemColor.menu);
		txtpnDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnDate.setText("Date:");
		txtpnDate.setBounds(10, 113, 40, 21);
		frame.getContentPane().add(txtpnDate);
		
		//Date input box
		textField_2 = new JTextField();
		textField_2.setBounds(52, 113, 96, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		//Window for search results
		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(8);
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setBounds(49, 161, 389, 177);
		frame.getContentPane().add(listScrollPane);

		
		//Get flights button, displays matched flights
		JButton btnGetFlights = new JButton("Get Flights");
		btnGetFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				src = textField.getText();
				dst = textField_1.getText();
				date = textField_2.getText();
				//ERROR checking to make sure all boxes are populated
				if(textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("")){
					UIManager.put("OptionPane.okButtonText", "OK");
					JOptionPane.showMessageDialog(null, "Please make sure all fields are specified.");
					return;
				}
				search(src, dst, date); //Send strings to client class to send to server
				//flights = new LinkedList<Flight> (getFlights());
				
				//flights.get(0).seeFlight();

				
				//if(flights.size() == null){
					//JOptionPane.showMessageDialog(null, "Sorry we do not have a flight that matches your search");
				//}
				//System.out.println(flights.get(0).getSource() + "is here!!!!");
				
				//for(int i = 0; i < flights.size(); i++){
					//listModel.addElement(flights.get(i));
				//} 
					
				//listModel.addElement(test); //Display results from search in window HERE
			}
		});
		btnGetFlights.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGetFlights.setForeground(new Color(0, 0, 0));
		btnGetFlights.setBounds(185, 111, 130, 23);
		frame.getContentPane().add(btnGetFlights);
		
		btnSelect = new JButton("View Flight Details");
		btnSelect.setEnabled(false);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				String value = (String) listModel.getElementAt(index);
				System.out.println("Element at:" + value);
				
				UIManager.put("OptionPane.okButtonText", "Book Flight");
				
				//Display additional information from flight HERE
				
				int val = JOptionPane.showOptionDialog(null, value , "Flight Booking", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if(val == JOptionPane.OK_OPTION){
					JPanel panel = new JPanel();
					JTextField field1 = new JTextField(10);
					panel.add(new JLabel("Enter First Name:"));
					panel.add(field1);
					JTextField field2 = new JTextField(10);
					panel.add(new JLabel("Enter Last Name:"));
					panel.add(field2);
					JTextField field3 = new JTextField(10);
					panel.add(new JLabel("Enter Date of Birth (mm/dd/yyyy):"));
					panel.add(field3);
					int val_2 = JOptionPane.showConfirmDialog(frame, panel, "Flight Booking", JOptionPane.OK_CANCEL_OPTION);
					if(val_2 == JOptionPane.OK_OPTION){
						firstName = field1.getText();
						lastName = field2.getText();
						DOB = field3.getText();
						//ERROR check if boxes are empty
						if(field1.getText().equals("") || field2.getText().equals("") || field3.getText().equals("")){
							UIManager.put("OptionPane.okButtonText", "OK");
							JOptionPane.showMessageDialog(null, "Please try again. Make sure all fields are specified."); 
							return;
						}
						//book(firstName, lastName, DOB); //Send booking info to server

						UIManager.put("OptionPane.okButtonText", "OK");
						JOptionPane.showMessageDialog(null, "Your flight has been booked! Your flight ticket is being printed...");
					}
				}
			}
		});
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelect.setBounds(45, 343, 171, 23);
		frame.getContentPane().add(btnSelect);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent arg0){
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				
				listModel.clear();				
			}
		});
		clear.setFont(new Font("Tahoma", Font.BOLD, 11));
		clear.setBounds(330, 111, 93, 23);
		frame.getContentPane().add(clear);
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			if(list.getSelectedIndex() == -1) {
				btnSelect.setEnabled(false);
			}else{
				btnSelect.setEnabled(true);
			}
		}
	}
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
					doStuff();
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
	
	public void doStuff(){
		flights.get(0).seeFlight();
	}
			
}



