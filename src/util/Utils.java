package util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Utility class
 * @author Juan Camilo Ibarra
 * @version 0.1
 * @date 2015-06-03
 */
public abstract class Utils {
	/**
	 * format a number to comma separated integer
	 */
	public static final DecimalFormat TO_INT = new DecimalFormat("###,###");
	/**
	 * format a number with two decimal values
	 */
	public static final DecimalFormat TO_FLOAT = new DecimalFormat("###,###.##");
	/**
	 * All white spaces
	 */
	public static final String WHITE_SPACES = "\\s++";
	
	/**
	 * Prints header of the CSV file 
	 * @param filepath filepath of the CSV file
	 * @param sep separator character
	 * @throws Exception
	 */
	public static String[] checkHeaders(String filepath, final String sep) throws Exception
	{
		ArrayList<String> hs = new ArrayList<String>();
		new CSVFileScanner(filepath, false, new LineCallableFunction(false){
			public Void call()
			{
				String[] splitted = line.split(sep, -1);
				for(int i = 0; i < splitted.length; i++)
				{
					hs.add(splitted[i]);
				}
				goOn = false;
				return null;
			}
		});
		
		return hs.toArray(new String[hs.size()]);
	}
	
	public static String[] checkFile(String file, final String sep, boolean withFrame) throws Exception
	{
		System.out.println("Checking: " + file);
		String[] headers = checkHeaders(file, sep);
		final int headersCount = headers.length;

		for(int i  = 0; i < headersCount; i++)
		{
			System.out.println(i + "\t" + headers[i]);
		}
		LineCallableFunction lcf = new LineCallableFunction(withFrame){
			public Void call()
			{
				String[] data = line.split(sep, -1);

				if(headersCount != data.length)
				{
					System.err.println("Error in line "+ lineCounter + ": " + data.length + ", " + headersCount + " expected. [" + line + "]");
					//System.exit(-1);
				}
				if(data[0].equals("0"))
				{
					System.err.println("Error");
				}
				if(logger != null)
					logger.updateLabel(TO_INT.format(lineCounter));
				return null;
			}
		};
		
		new CSVFileScanner(file, true, lcf);

		System.out.println("Number of lines: " + lcf.lineCounter);
		return headers;
	}
	
	public static int countLines(String file, boolean withFrame) throws Exception
	{
		System.out.println("Checking: " + file);
		
		LineCallableFunction lcf = new LineCallableFunction(withFrame){
			public Void call()
			{
				if(logger != null)
					logger.updateLabel(TO_INT.format(lineCounter));
				return null;
			}
		};
		
		new CSVFileScanner(file, true, lcf);

		System.out.println("Number of lines: " + lcf.lineCounter);
		return lcf.lineCounter;
	}
	
	public static void mergeFiles(ArrayList<String> files, String output)
	{
		int lastIndex = 0;
		try
		{
			Helper myHelper = new Helper(output);
			for(String file : files)
			{
				System.out.println("Processing file: " + file + "\n");
				new CSVFileScanner(file, false, new LineCallableFunction(true){
					public Void call()
					{
						try {
							myHelper.write(line + "\n");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						logger.updateLabel(Utils.TO_INT.format(lineCounter));
						return null;
					}
				});	
			}
			myHelper.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Last index: " +lastIndex ); 
	}
	
	
	public static void listFiles(File directory, ArrayList<String> list){
	    if(directory.isDirectory())
	    {
	    	for (File file : directory.listFiles()) {
		        listFiles(file, list);
		    }	
	    }
	    else
	    {
	    	list.add(directory.toString());
	    }
	}
	
	public static void goToLine(String file, int lineNumber, int threshold)
	{
		try
		{
			System.out.println("Processing file: " + file + "\n");
			new CSVFileScanner(file, false, new LineCallableFunction(true){
				public Void call()
				{
					if(lineCounter > lineNumber - threshold && lineCounter < lineNumber + threshold)
					{
						System.out.println(lineCounter + " --> " + line);
					}
					if(lineCounter > lineNumber + threshold)
					{
						goOn = false;
					}
					logger.updateLabel(Utils.TO_INT.format(lineCounter));
					return null;
				}
			});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
