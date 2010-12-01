package projectswop20102011.exceptions;

/**
 * An exception that will be thrown when an application attempts to use an invalid name for a mapitem
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidMapItemNameException extends Exception {

	/**
	 * Variable registering the serivalVersionUID of this InvalidMapItemNameException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidMapItemNameException with no detail message.
	 */
	public InvalidMapItemNameException() {
		super();
	}

	/**
	 * Constructs an InvalidMapItemNameException with the specified detail message.
	 * @param message
	 *      The detail message.
	 */
	public InvalidMapItemNameException(String message) {
		super(message);
	}
}
