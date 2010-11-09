package projectswop20102011.exceptions;

/**
 * Thrown when an application attempts to use an invalid coordinate.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidCoordinateException extends Exception {

	/**
	 * Variable registering the serivalVersionUID of this InvalidCoordinateException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidCoordinateException with no detail message.
	 */
	public InvalidCoordinateException() {
		super();
	}

	/**
	 * Constructs an InvalidCoordinateException with the specified detail message.
	 * @param message
	 *      The detail message.
	 */
	public InvalidCoordinateException(String message) {
		super(message);
	}
}
