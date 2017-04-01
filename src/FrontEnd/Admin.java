package FrontEnd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;

public class Admin {

	private JFrame frame;

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
		frame.setBounds(100, 100, 505, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnAirlineRegistration = new JTextPane();
		txtpnAirlineRegistration.setBackground(SystemColor.menu);
		txtpnAirlineRegistration.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnAirlineRegistration.setText("Airline Registration System (Administrator)\r\n");
		txtpnAirlineRegistration.setBounds(78, 11, 313, 27);
		frame.getContentPane().add(txtpnAirlineRegistration);
	}

}
