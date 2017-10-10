package util;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public 	class Helper{
	private FileWriter writer;
	
	private Map<String, Object> dataObject;
	
	public Helper(String fileName) throws Exception
	{
		writer =  new FileWriter(new File(fileName), true);
		dataObject = new HashMap<String, Object>();
	}
	
	public void setDataObjectKeyValue(String key, Object value)
	{
		dataObject.put(key, value);
	}
	
	public Object getDataObject(String key)
	{
		return dataObject.get(key);
	}
	
	synchronized public void  write(String data) throws Exception
	{
		writer.write(data);
	}
	
	public void close() throws Exception
	{
		writer.close();
	}
}