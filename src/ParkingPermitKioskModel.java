import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


//----------
// This class provide the logic of the main program
//----------
public class ParkingPermitKioskModel
{
	public static HashMap<String,HashMap<String,String>> studentMap = new HashMap<String,HashMap<String,String>>();
	public static HashMap<String,String> informationMap = new HashMap<String,String>();
	 
	public static HashMap<String,HashMap<String,String>> readDatabase()
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
			
			String str = dis.readLine();
			while(dis.available() != 0)
			{
				str = dis.readLine();
				
				String[] line = str.split(",");
				informationMap.put("PIN", line[1]);
				informationMap.put("FmailyName",line[2]);
				informationMap.put("GivenName", line[3]);
				informationMap.put("status", line[4]);
				studentMap.put(line[1], informationMap);
			}
		}catch(IOException e)
		{
			System.out.println("File Error");
		}
		return studentMap;
		
	}
	
	public static void main(String[] args)
	{
	}
	
}
