package util;

import gui.FrameLogger;
import java.util.concurrent.Callable;

/**
 * Class to process a line in a CSV file
 * @author Juan Camilo Ibarra
 * @version 0.1
 * @date 2015-06-03
 */
public class LineCallableFunction implements Callable<Void> {

	protected String line;
	protected int lineCounter;
	protected FrameLogger logger;
	protected boolean goOn = true;
	protected boolean lastLine = false;
	/**
	 * If the processing will be monitored with a frame logger
	 * @param withFrame
	 */
	public LineCallableFunction(boolean withFrame) {
		
		if(withFrame)
		{
			logger = new FrameLogger();
			logger.setVisible(true);
		}
		
	}
	/**
	 * Updated the line number being processed
	 * @param line
	 */
	public void setLine(String line)
	{
		lineCounter++;
		this.line = line;
	}
	/**
	 * Tells if the function will continue
	 * @return
	 */
	public boolean hasNext()
	{
		return goOn;
	}
	/**
	 * Sets if the line being read is the last one
	 * @param b if the line is the last one
	 */
	public void setLastLine(boolean b)
	{
		lastLine = b;
	}
	@Override
	public Void call() throws Exception {
		
		return null;
	}

}
