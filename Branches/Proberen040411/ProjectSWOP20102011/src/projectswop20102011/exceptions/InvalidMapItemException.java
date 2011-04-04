package projectswop20102011.exceptions;

/**
 * An exception that will be thrown when an application attempts to use an invalid mapitem.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidMapItemException extends Exception {

	/**
	 * Variable registering the serivalVersionUID of this InvalidMapItemException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidMapItemException with no detail message.
	 */
	public InvalidMapItemException() {
		super();
	}

	/**
	 * Constructs an InvalidMapItemException with the specified detail message.
	 * @param message
	 *      The detail message.
	 */
	public InvalidMapItemException(String message) {
		super(message);
	}
}
