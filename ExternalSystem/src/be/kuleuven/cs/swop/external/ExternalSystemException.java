package be.kuleuven.cs.swop.external;

/**
 * An exception which specifically occurred in the external system
 * 
 * @author philippe
 *
 */
public class ExternalSystemException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new exception with the given message
	 * @param message The message explaining the cause of the exception
	 */
	public ExternalSystemException(String message) {
		super(message);
	}
	
}
