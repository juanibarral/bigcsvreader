package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Simple frame to show a message
 * @author Juan Camilo Ibarra
 * @version 0.1
 * @date 2015-06-03
 */
public class FrameLogger extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel labelCounter;
	
	/**
	 * Constructor <br>
	 * Creates a frame of 200 by 200 pixels with a label
	 */
	public FrameLogger()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 200);
		setLayout(new BorderLayout());
		labelCounter = new JLabel();
		add(labelCounter, BorderLayout.CENTER);
	}
	
	/**
	 * Updates the message in the label
	 * @param message message to show
	 */
	public void updateLabel(String message)
	{
		labelCounter.setText(message);
	}
}
