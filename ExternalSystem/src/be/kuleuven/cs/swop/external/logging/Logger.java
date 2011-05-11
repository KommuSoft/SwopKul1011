package be.kuleuven.cs.swop.external.logging;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * A logger which can be used to output messages coming from
 * 	the external system
 * 
 * 
 * @author philippe
 */
public class Logger extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * An enumeration containing different log levels, as well as 
	 * 	textual conversion methods
	 */
	private enum Level {
		FATAL, ERROR, INFO;
		
		public String toString() {
			switch(this) {
				case FATAL: return "FATAL";
				case ERROR: return "ERROR";
				case INFO: return "INFO";
				default: return "???";
			}
		}
	}
	
	JTextArea output;
	
	/**
	 * Creates a new logger
	 */
	public Logger() {
		output = new JTextArea(40,120);
		this.getContentPane().add(output);
		this.pack();
		
	    // Get the size of the screen
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    // Determine the new location of the window
	    int w = this.getSize().width;
	    int h = this.getSize().height;
	    int x = (dim.width-w)/2;
	    int y = (dim.height-h)/2;
	    
	    // Move the window
	    this.setLocation(x, y);
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	}
	
	/**
	 * Logs a message with the level INFO
	 * @param message The message to log
	 */
	public void info(String message) {
		log(message, Level.INFO);
	}
	
	/**
	 * Logs a message with the level ERROR
	 * @param message The message to log
	 */
	public void error(String message) {
		log(message, Level.ERROR);
	}
	
	/**
	 * Logs a message with the level FATAL
	 * @param message The message to log
	 */
	public void fatal(String message) {
		log(message, Level.FATAL);
	}
	
	//TODO convert to swing
	/**
	 * Logs a message with the given level
	 * @param message The message to log
	 * @param level The level of the message
	 */
	private void log(String message, Level level) {
		output.append(level.toString() + "\t" + message + "\n");
	}
	
}
