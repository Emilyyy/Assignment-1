import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//----------
// This class provide the logic of the main program
//----------
public class ParkingPermitKioskModel
{
	public static HashMap<String,HashMap> studentMap = new HashMap<String,HashMap>();
	private static Pattern pattern;
	private static Matcher matcher;
	public static List<String> companyDatabase = new ArrayList<String>();
	
	
	public static HashMap<String,HashMap> readStudentDatabase()
	{
		File file = new File("students.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		
		try
		{
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			
			String str;
			while(dis.available() != 0)
			{
				HashMap<String,String> informationMap = new HashMap<String,String>();
				str = dis.readLine();
				
				String[] line = str.split(",");
				informationMap.put("PIN", line[1].trim());
				informationMap.put("FmailyName",line[2].trim());
				informationMap.put("GivenName", line[3].trim());
				informationMap.put("status", line[4].trim());
				studentMap.put(line[0].trim(), informationMap);
				
				
			}
		}catch(IOException e)
		{
			System.out.println("File Error");
		}
		
		System.out.println(studentMap.toString());

		return studentMap;
		
	}
	
	public static boolean studenNumberInDatabase(String studentNumberInput)
	{
		pattern = Pattern.compile("^[0-9]{9}$");
		matcher = pattern.matcher(studentNumberInput);
		
		if(studentMap.containsKey(studentNumberInput.trim())&&matcher.matches())
		{
			System.out.println("studenNumberInDatabase: true");
			return true;
		}
		else
		{
			System.out.println("studenNumberInDatabase: false");
			return false;
		}
	}
	
	public static boolean studentNumberAndPINMatches(String studentNumberInput,String PINInput)
	{
		if(studenNumberInDatabase(studentNumberInput) && studentMap.get(studentNumberInput).get("PIN").equals(PINInput))
		{
			System.out.println("studentNumberAndPINMatches: true");
			return true;

			
		}
		else
		{
			System.out.println("studentNumberAndPINMatches: false");
			return false;

		}
	}
	
	public static boolean emailValid(String emailInput)
	{
		pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		matcher = pattern.matcher(emailInput.trim());
		if(emailInput.isEmpty())
		{
			System.out.println("Email Empty: true");
			return true;
		}
		else if(matcher.matches())
		{
			System.out.println("Email maches: true");
			return true;
		}
		else
		{
			System.out.println("Email Input Error");
			return false;
		}
	}
	
	public static boolean hasOutstandingFine(String studentNumberInput) //use student number to check whether student has outstanding fine
	{
		if(studentMap.get(studentNumberInput).get("status").equals("ok"))
		{
			System.out.println("Has NO outstanding fine");
			return true;
		}
		else
		{
			System.out.println("Has outstanding Fine, permit not issued");
			return false;
		}
	}
	
	public static List<String> readCompanyDatabase()
	{
		
		File file = new File("companies.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		
		try
		{
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			
			String str;
			while(dis.available() != 0)
			{
				str = dis.readLine();
				
				companyDatabase.add(str);
			}
		}catch(IOException e)
		{
			System.out.println("File Error");
		}
		
		
		/*for(int i =0 ;i<companyDatabase.size();i++)
		{
			System.out.println("list # "+i+" company: "+companyDatabase.get(i));
		}*/
		
		return companyDatabase;
		
	}
	
	
	
	
	
	
	public static void main(String[] args)
	{
		/*
		readStudentDatabase();
		studenNumberInDatabase("123456789");
		System.out.println(studentMap.containsKey("123456789"));
		studentNumberAndPINMatches("123456789","1234");
		emailValid("mili_24_729@hotmail.com");
		hasOutstandingFine("456864324");
		
		readCompanyDatabase();
		*/
	}
	
}
