import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class ParkingPermitKiosk
{
	public static void main(String[] args)
	{
		System.out.println("Hello World!");
		ParkingPermitKioskModel.readStudentDatabase();
		ParkingPermitKioskModel.readCompanyDatabase();
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
	JPanel buttonPanel; //
	JPanel keyboardPanel; // virutal keyboard panel
	
	// progress action buttons
	JButton submitButton, backButton, nextButton, clearButton;
	
	// keyboard panels
	JPanel letterKeyboard;
	JPanel numKeyboardPanel;
	
	// map to find char from virtual keyboards
	Map<JButton, String> allKeyboardButtonMap;
	
	JLabel studentNumberLabel;
	JTextField studentNumberInput;
	JLabel PINLabel;
	JTextField PINInput;
	JLabel EmailLabel;
	JTextField EmailInput;
	JLabel vehicleMakeLabel;
	//JTextField vehicleMakeInput;
	JLabel vehicleModelLabel;
	//JTextField vehicleColorInput;
	JLabel plateNumberLabel;
	JTextField plateNumberInput;
	JLabel insuranceNumberLabel;
	JTextField insuranceNumberInput;
	
	JList insuranceCompanyList;
	
	Map<String,ArrayList<String>> vehicleMap;
	
	// expiry date panel
	JPanel expirydayPanel;
	
	// expiry date combo box
	JComboBox<Integer> month, day;

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
		mainPane.add(inputPanel);
		
		//inputPanel.setLayout(new GridLayout(1,2));
		inputPanel.add(insurancePanel());		

		// construct buttonPanel
		setupSubmittPanel();

		// construct keyboardPanel
		keyboardPanel = new JPanel();
		keyboardPanel.setBackground(Color.green);
		setupAllKeyboards();

		// add subpanel into the main panel
		mainPane.add(keyboardPanel);

		// setup the main panel to be the main pane in frame
		setContentPane(mainPane);
		
		// test!!!
		expiryDatePanel();
	} // end constructor
	
	private void setupSubmittPanel()
	{
		// define propoerties of button panel
		final int ROW = 1;
		final int COL = 4;
		
		backButton = new JButton("Back");
		nextButton = new JButton("Next");
		submitButton = new JButton("Submit");
		clearButton = new JButton("Clear");
		
		// setup button panel
		buttonPanel = new JPanel(new GridLayout(ROW, COL));
		mainPane.add(buttonPanel);
		
		// add buttons to panel
		buttonPanel.add(backButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(submitButton);
		buttonPanel.add(clearButton);
		
		// set button action
		backButton.addActionListener(this);
		nextButton.addActionListener(this);
		clearButton.addActionListener(this);
		submitButton.addActionListener(this);
		
	} // end method setupSubmittPanel
	
	private void expiryDatePanel()
	{
		// define panel properties
		final int ROW = 1;
		final int COL = 2;

		// setup Panel
		expirydayPanel = new JPanel(new GridLayout(ROW, COL));
		
		// possible month
		Integer[] monthRange = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		
		// setup reuqired compoments
		month =new JComboBox<>(monthRange);
		day = new JComboBox<>();
		month.addActionListener(this);
		day.addActionListener(this);

		// add combo boxes to panel
		expirydayPanel.add(month);
		expirydayPanel.add(day);
		
		//test!!!
		mainPane.remove(keyboardPanel);
		mainPane.add(expirydayPanel);
		
	} // end method expiryDatePanel
	
	public JPanel studentNumberPanel()
	{
		studentNumberLabel=new JLabel("Student Number: ");
		studentNumberInput = new JTextField(9);
		PINLabel = new JLabel("PIN: ");
		PINInput = new JTextField(4);
		
		//add listeners
		studentNumberInput.addActionListener(this);
		PINInput.addActionListener(this);
		
		//add components to panel
		JPanel studentNumber = new JPanel();
		studentNumber.add(studentNumberLabel);
		studentNumber.add(studentNumberInput);

		JPanel PIN = new JPanel();
		PIN.add(PINLabel);
		PIN.add(PINInput);
		
		JPanel studentInfoPanel = new JPanel();
		
		studentInfoPanel.setLayout(new GridLayout(2,1));
		studentInfoPanel.add(studentNumber);
		studentInfoPanel.add(PIN);
		
		this.setContentPane(studentInfoPanel);
		
		return studentInfoPanel;
		
	}
	
	public JPanel emailPanel()
	{
		EmailLabel = new JLabel("Email(Optional): ");
		EmailInput = new JTextField(20);
		
		JPanel emailInfo = new JPanel();
		emailInfo.setLayout(new GridLayout(2,1));
		emailInfo.add(EmailLabel);
		emailInfo.add(EmailInput);		
		
		JPanel emailInfoPanel = new JPanel();
		emailInfoPanel.add(emailInfo);
		
		return emailInfoPanel;
	}
	
	/*public Map<String,ArrayList<String>> importVehicleInfo()
	{
		
		
		return vehicleMap;
	}*/
	
	/*public JPanel VehiclePanel()
	{
		vehicleMakeLabel = new JLabel("Vehicle Make: ");
		
		JPanel vehicleMakePanel = new JPanel();
		
		
	}*/
	
	public JPanel insurancePanel()
	{
		JPanel insuranceInfoPanel = new JPanel();
		JPanel policyNumberPanel = new JPanel();
		insuranceNumberLabel = new JLabel("Policy Number: ");
		insuranceNumberInput = new JTextField(10);
		
		policyNumberPanel.add(insuranceNumberLabel);
		policyNumberPanel.add(insuranceNumberInput);
		
		JPanel listPanel = new JPanel();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(int i=0;i<ParkingPermitKioskModel.companyDatabase.size();i++)
		{
			listModel.addElement(ParkingPermitKioskModel.companyDatabase.get(i));
		}
		
		insuranceCompanyList = new JList<>(listModel);
		
		add(new JScrollPane(insuranceCompanyList));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200,200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		listPanel.add(insuranceCompanyList);
		
		insuranceInfoPanel.setLayout(new GridLayout(2,1));
		insuranceInfoPanel.add(policyNumberPanel);
		insuranceInfoPanel.add(listPanel);
		
		
		return insuranceInfoPanel;
	}

	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
		
		String ch = allKeyboardButtonMap.get(obj);
		
		// case 1: button clicked is a virtual keyboard key
		if (ch != null)
		{
			JOptionPane.showMessageDialog(null, ch, "", JOptionPane.PLAIN_MESSAGE);
		}
		// case : exipiry month selected
		else if (obj == month)
		{
//			JOptionPane.showMessageDialog(null, month.getSelectedItem().toString(), "", JOptionPane.PLAIN_MESSAGE);
			int monthInt = Integer.parseInt(month.getSelectedItem().toString());
			setDateForMonth(monthInt);
		}
			
//		
//		System.out.println("Clicked!");
		
	} // end method actionPerformed
	
	private void setDateForMonth(int month)
	{
		ArrayList<Integer> dayList = new ArrayList<>();
		
		switch(month)
		{
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				for (int i = 1; i <= 31; i++)	dayList.add(i);
				break;
			case 2:
				for (int i = 1; i <= 28; i++)	dayList.add(i);
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				for (int i = 1; i <= 30; i++)	dayList.add(i);
				break;
		}
		
		// add days into combobox
		day.removeAllItems();
		for (int i = 0; i < dayList.size(); i++)	day.addItem(dayList.get(i));
		
	} // end method setDateForMonth
	
	private void setupAllKeyboards()
	{
		// create map for button matching
		allKeyboardButtonMap = new HashMap<JButton, String>();
		
		// setup sub keyboards
		setupLetterKeyboard();
		setupNumKeyboard();
		
//		keyboardPanel.add(letterKeyboard);
		keyboardPanel.add(numKeyboardPanel);

		
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
			allKeyboardButtonMap.put(button, firstRow[i]);
			button.addActionListener(this);
		} // end for add first row
		
		// add second row keyboard
		for (int i = 0; i < secondRow.length; i++)
		{
			JButton b = new JButton(secondRow[i]);
			letterKeyboardPanel2.add(b);
			allKeyboardButtonMap.put(b, secondRow[i]);
			b.addActionListener(this);
		} // end for add second row
		
		// add third row keyboard
		for (int i = 0; i < thirdRow.length; i++)
		{
			JButton b = new JButton(thirdRow[i]);
			letterKeyboardPanel3.add(b);
			allKeyboardButtonMap.put(b, thirdRow[i]);
			b.addActionListener(this);
		} // end for add third row
		
		// add forth row keyboard
		for (int i = 0; i < fourthRow.length; i++)
		{
			JButton b = new JButton(fourthRow[i]);
			letterKeyboardPanel4.add(b);
			allKeyboardButtonMap.put(b, fourthRow[i]);
			b.addActionListener(this);
		} // end for add 4th row
		
		// add 5th row keyboard
		for (int i = 0; i < fifthRow.length; i++)
		{
			JButton b = new JButton(fifthRow[i]);
			letterKeyboardPanel5.add(b);
			allKeyboardButtonMap.put(b, fifthRow[i]);
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
			
			
			allKeyboardButtonMap.put(b, num[i]);
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
