package projectswop20102011.exceptions;

/**
 * Thrown when an application attempts to use an invalid duration.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidDurationException extends Exception {

	/**
	 * Variable registering the serivalVersionUID of this InvalidDurationException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidDurationException with no detail message.
	 */
	public InvalidDurationException() {
		super();
	}

	/**
	 * Constructs an InvalidDurationException with the specified detail message.
	 * @param message
	 *      The detail message.
	 */
	public InvalidDurationException(String message) {
		super(message);
	}
}
