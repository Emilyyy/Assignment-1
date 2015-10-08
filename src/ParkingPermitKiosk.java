import javax.swing.*;
import javax.swing.Box.*;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class ParkingPermitKiosk
{
	public static void main(String[] args)
	{
		System.out.println("Hello World!");

		ParkingPermitKioskFrame frame = new ParkingPermitKioskFrame();
//		frame.setPreferredSize(new Dimension(600, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

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
	
	// keyboard panels
	JPanel letterKeyboard;
	JPanel numKeyboardPanel;
	
	// map to find char from button
	Map<JButton, String> allButtonMap;
	Map<JButton, String> letterButtonMap;
	Map<JButton, String> numButtonMap;
	
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
		setupAllKeyboards();

		// add subpanel into the main panel
		mainPane.add(inputPanel);
		mainPane.add(buttonPanel);
		mainPane.add(keyboardPanel);

		// setup the main panel to be the main pane in frame
		setContentPane(mainPane);
		
		// setup Letter Keyboard
		// test!!!
		
	} // end constructor

	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
		
		String ch = allButtonMap.get(obj);
		
		JOptionPane.showMessageDialog(null, ch, "", JOptionPane.PLAIN_MESSAGE);
	} // end method actionPerformed
	
	private void setupAllKeyboards()
	{
		// create map for button matching
		allButtonMap = new HashMap<JButton, String>();
		
		// setup sub keyboards
		setupLetterKeyboard();
		setupNumKeyboard();
		
//		keyboardPanel.add(letterKeyboard);
		keyboardPanel.add(numKeyboardPanel);

		// test!!!
//		allButtonMap.putAll(letterButtonMap);
//		allButtonMap.putAll(numButtonMap);
		
	} // end method setupAllKeyboards
	
	private void setupLetterKeyboard()
	{
		// define keyboard properties
		int rowNum = 5;
		
		// keyboard data
		String firstRow[] = {"~","1","2","3","4","5","6","7","8","9","0","-","+","<<<<<"};
		String secondRow[] = {"Tab","Q","W","E","R","T","Y","U","I","O","P","[","]","\\"};
		String thirdRow[] = {"A","S","D","F","G","H","J","K","L",":","\"","Enter"};
		String fourthRow[] = {"Shift","Z","X","C","V","B","N","M",",",".","?"};
		String fifthRow[]={" " ,"<" ,"\\/",">" };
		
		// main letter keyboard panel
		letterKeyboard = new JPanel(new GridLayout(rowNum, 1));

		// subpanel for letter keyboard for the last row
		JPanel letterKeyboardPanel1 = new JPanel(new GridLayout(1, firstRow.length));
		JPanel letterKeyboardPanel2 = new JPanel(new GridLayout(1, secondRow.length));
		JPanel letterKeyboardPanel3 = new JPanel(new GridLayout(1, thirdRow.length));
		JPanel letterKeyboardPanel4 = new JPanel(new GridLayout(1, fourthRow.length));
		JPanel letterKeyboardPanel5 = new JPanel(new GridLayout(1, fifthRow.length));
		
		// add subpanels to main letter panel
		letterKeyboard.add(letterKeyboardPanel1);
		letterKeyboard.add(letterKeyboardPanel2);
		letterKeyboard.add(letterKeyboardPanel3);
		letterKeyboard.add(letterKeyboardPanel4);
		letterKeyboard.add(letterKeyboardPanel5);
		
		for (int i = 0; i < firstRow.length; i++)
		{
			JButton button = new JButton(firstRow[i]);
			letterKeyboardPanel1.add(button);
			allButtonMap.put(button, firstRow[i]);
			button.addActionListener(this);
		} // end for add first row
		
		// add second row keyboard
		for (int i = 0; i < secondRow.length; i++)
		{
			JButton b = new JButton(secondRow[i]);
			letterKeyboardPanel2.add(b);
			allButtonMap.put(b, secondRow[i]);
			b.addActionListener(this);
		} // end for add second row
		
		// add third row keyboard
		for (int i = 0; i < thirdRow.length; i++)
		{
			JButton b = new JButton(thirdRow[i]);
			letterKeyboardPanel3.add(b);
			allButtonMap.put(b, thirdRow[i]);
			b.addActionListener(this);
		} // end for add third row
		
		// add forth row keyboard
		for (int i = 0; i < fourthRow.length; i++)
		{
			JButton b = new JButton(fourthRow[i]);
			letterKeyboardPanel4.add(b);
			allButtonMap.put(b, fourthRow[i]);
			b.addActionListener(this);
		} // end for add 4th row
		
		// add 5th row keyboard
		for (int i = 0; i < fifthRow.length; i++)
		{
			JButton b = new JButton(fifthRow[i]);
			letterKeyboardPanel5.add(b);
			allButtonMap.put(b, fifthRow[i]);
			b.addActionListener(this);
		} // end for add 4th row
		
		
	} // end method setupLetterKeyboard
	
	private void setupNumKeyboard()
	{
		// define properties of keyboard 
		final int ROW = 4;
		final int COL = 3;
		
		// define keyboard data
		String[] num = {"1", "2", "3", "4", "5", "6","7", "8", "9", "BOX", "0", "Bk"};

		// define keyboard
		numKeyboardPanel = new JPanel(new GridLayout(ROW, COL));

		for (int i = 0 ; i < num.length; i++)
		{
			JButton b = new JButton(num[i]);
			numKeyboardPanel.add(b);
			
			// create empty box to position 90 in middle
			if (num[i].equals("BOX"))
			{
				b.setEnabled(false);
				b.setText("");
			} // end if create empty box
			
			b.addActionListener(this);
			
			
			allButtonMap.put(b, num[i]);
		} // end for add keys into keyboard
		
	} // end method setupNumKeyboard
	
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
