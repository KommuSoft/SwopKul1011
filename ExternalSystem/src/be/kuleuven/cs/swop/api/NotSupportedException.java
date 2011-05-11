package be.kuleuven.cs.swop.api;

/**
 * An exception which occurred because some part of the 
 * 	Emergency Dispatching system is not implemented.
 * 
 * @author philippe
 *
 */
public class NotSupportedException extends Exception {

	/**
	 * The required serial version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new exception with the given message, indicating that
	 * 	an operation of the Emergency Dispatch API is not supported.
	 * @param message The message explaining the cause of the exception
	 */
	public NotSupportedException(String message) {
		super(message);
	}
	
}