package FrontEnd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin {
	
	private String src;
	private String dst;
	private String date;
	
	private String firstName;
	private String lastName;
	private String DOB;

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Admin() {
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
		txtpnAirlineRegistration.setBackground(SystemColor.menu);
		txtpnAirlineRegistration.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnAirlineRegistration.setText("Airline Registration System (Administrator)\r\n");
		txtpnAirlineRegistration.setBounds(78, 11, 313, 27);
		frame.getContentPane().add(txtpnAirlineRegistration);
		
		//"Book Flights"
		JTextPane txtpnBookFlights = new JTextPane();
		txtpnBookFlights.setBackground(SystemColor.menu);
		txtpnBookFlights.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnBookFlights.setText("Book Flights:");
		txtpnBookFlights.setBounds(10, 43, 100, 21);
		frame.getContentPane().add(txtpnBookFlights);
		
		//"From:"
		JTextPane txtpnFrom = new JTextPane();
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
		
		//Get Flights button
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

				//System.out.println(src + dst + date);
				//Send Strings to database
				//Display search results from database
				//listModel.addElement(test);
			}
		});
		btnGetFlights.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGetFlights.setBounds(153, 135, 117, 23);
		frame.getContentPane().add(btnGetFlights);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 166, 313, 180);
		frame.getContentPane().add(textPane);
		
		//"Edit flights"
		JTextPane txtpnEditFlights = new JTextPane();
		txtpnEditFlights.setBackground(SystemColor.menu);
		txtpnEditFlights.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnEditFlights.setText("Edit Flights:");
		txtpnEditFlights.setBounds(345, 49, 81, 21);
		frame.getContentPane().add(txtpnEditFlights);
		
		JButton btnBrowseBookedTickets = new JButton("Browse Booked Tickets");
		btnBrowseBookedTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBrowseBookedTickets.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowseBookedTickets.setBounds(339, 70, 177, 23);
		frame.getContentPane().add(btnBrowseBookedTickets);
		
		JButton btnNewButton = new JButton("View Flight Details");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 353, 140, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add Flights\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(339, 100, 177, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
