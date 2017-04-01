package FrontEnd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import java.awt.event.ActionEvent;

public class Passenger implements ListSelectionListener{

	private String test = "Calgary Halifax 01-17-18";
	
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
					Passenger window = new Passenger();
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
	public Passenger() {
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
		
		//From input box
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
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList list = new JList(listModel);
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
				//ERROR check if boxes are empty
				//Display search results from database
				listModel.addElement(test);
			}
		});
		btnGetFlights.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGetFlights.setForeground(new Color(0, 0, 0));
		btnGetFlights.setBounds(194, 111, 162, 23);
		frame.getContentPane().add(btnGetFlights);
		
		JButton btnSelect = new JButton("View Flight Details");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UIManager.put("OptionPane.okButtonText", "Book Flight");
				//Display additional information from database
				int value = JOptionPane.showOptionDialog(null, test , "Flight Booking", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if(value == JOptionPane.OK_OPTION){
					JPanel panel = new JPanel();
					JTextField field1 = new JTextField(10);
					panel.add(new JLabel("Enter First Name:")); //Student ID field
					panel.add(field1);
					JTextField field2 = new JTextField(10);
					panel.add(new JLabel("Enter Last Name:")); //Faculty field
					panel.add(field2);
					JTextField field3 = new JTextField(10);
					panel.add(new JLabel("Enter Date of Birth (mm-dd-yyyy):")); //Major field
					panel.add(field3);
					int val = JOptionPane.showConfirmDialog(frame, panel, "Flight Booking", JOptionPane.OK_CANCEL_OPTION);
					if(val == JOptionPane.OK_OPTION){
						//ERROR check if boxes are empty
						UIManager.put("OptionPane.okButtonText", "OK");
						JOptionPane.showMessageDialog(null, "Your flight has been booked! Your flight ticket is being printed...");
						// create/ print ticket
					}
				}
			}
		});
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelect.setBounds(45, 343, 171, 23);
		frame.getContentPane().add(btnSelect);
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// must write method for selecting items in the list
		
	}
}
