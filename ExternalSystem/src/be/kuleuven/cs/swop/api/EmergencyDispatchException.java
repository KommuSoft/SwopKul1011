package be.kuleuven.cs.swop.api;

/**
 * An exception which specifically occurred in the emergency
 * dispatch system
 * 
 * @author philippe
 *
 */
public class EmergencyDispatchException extends Exception {

	/**
	 * The required serial version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new exception with the given message, indicating that
	 * 	an exception occurred in the Emergency Dispatching System.
	 * @param message The message explaining the cause of the exception
	 */
	public EmergencyDispatchException(String message) {
		super(message);
	}
	
}