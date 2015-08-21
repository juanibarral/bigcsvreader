package util;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Class that reads a large CSV file 
 * @author Juan Camilo Ibarra
 * @version 0.1
 * @date 2015-06-03
 */
public class CSVFileScanner {

	/**
	 * Constructor <br>
	 * Creates a CSV reader
	 * @param filepath filepath of the CSV file
	 * @param avoidFirst if the first line will not be read
	 * @param func Function to be called to process a line
	 * @throws Exception
	 */
	public CSVFileScanner(String filepath, boolean avoidFirst, LineCallableFunction func) throws Exception
	{
		FileInputStream inputStream = null;
		String line = "";
		Scanner sc = null;
		boolean lastLine = false;
		try {
			inputStream = new FileInputStream(filepath);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				if(!sc.hasNextLine())
				{
					lastLine = true;
					func.setLastLine(lastLine);
				}
				if(!avoidFirst)
				{
					func.setLine(line);
					func.call();
					if(!func.hasNext())
					{
						break;
					}
				}
				avoidFirst = false;
			}
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		try {
			new CSVFileScanner("data/Usos_Nombres.csv", true, new LineCallableFunction(true){
				public Void call()
				{
					String[] splitted = line.split(",", -1);
					System.out.println(splitted[0] + "\t" + splitted[1]);
					logger.updateLabel(Utils.TO_INT.format(lineCounter));
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
