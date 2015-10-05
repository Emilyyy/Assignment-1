import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ParkingPermitKiosk
{
	public static void main(String[] args)
	{
		System.out.println("Hello World!");

		ParkingPermitKioskFrame frame = new ParkingPermitKioskFrame();
		frame.setPreferredSize(new Dimension(600, 300));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

	} // end method main
	
	

} // end class ParkingPermitKiosk

class ParkingPermitKioskFrame extends JFrame implements ActionListener
{
	// fields
	JPanel mainPane; // main panel that consist everything
	JPanel inputPanel;
	JPanel buttonPanel;
	JPanel keyboardPanel;

	final int frameRow;
	final int frameCol;

	// constructor
	public ParkingPermitKioskFrame()
	{
		// define frame row
		frameRow = 3;

		// define frame column
		frameCol = 1;

		// construct main panel
		mainPane = new JPanel(new GridLayout(frameRow, frameCol));

		// construct inputPanel
		inputPanel = new JPanel();
		inputPanel.setBackground(Color.pink);
		JLabel label1 = new JLabel("inputPanel for all the fields and label");
		inputPanel.add(label1);

		// construct buttonPanel
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.yellow);
		JLabel label2 = new JLabel("buttonPanel for submit button");
		buttonPanel.add(label2);

		// construct keyboardPanel
		keyboardPanel = new JPanel();
		keyboardPanel.setBackground(Color.green);
		JLabel label3 = new JLabel("buttonPanel for on screen keyboard");
		keyboardPanel.add(label3);

		// add subpanel into the main panel
		mainPane.add(inputPanel);
		mainPane.add(buttonPanel);
		mainPane.add(keyboardPanel);

		// setup the main panel to be the main pane in frame
		setContentPane(mainPane);
	} // end constructor

	public void actionPerformed(ActionEvent e)
	{
	} // end method actionPerformed
	
	public void printParkingTicket(String expiryDate, String moneyPaid, String studentNumber, String plateNumber)
	{
		// ticket content
		String displayMsg = "";
		displayMsg += "Money Paid : " + moneyPaid + "\n";
		displayMsg += "Expiry Date : " + expiryDate + "\n";
		displayMsg += "Student Number : " + studentNumber + "\n";
		displayMsg += "Plate Number : " + plateNumber + "\n";

		// ticket title
		String ticketTitle = "Parking Ticket";
	
		JOptionPane.showMessageDialog(null, displayMsg, ticketTitle, JOptionPane.PLAIN_MESSAGE);
	} // end method printParkingTicket
} // end class 
