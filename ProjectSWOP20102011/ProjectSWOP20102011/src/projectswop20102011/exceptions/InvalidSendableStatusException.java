package projectswop20102011.exceptions;

/**
 * An exception thrown for invalid usage or parsing of a SendableStatus.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidSendableStatusException extends Exception {

	/**
	 * Variable registering the serivalVersionUID of this InvalidSendableStatusException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of an InvalidSendableStatusException with no detail message.
	 */
	public InvalidSendableStatusException() {
		super();
	}

	/**
	 * Creates a new instance of an InvalidSendableStatusException with a specified message.
	 * @param message
	 *		A message explaining the cause of the exception.
	 */
	public InvalidSendableStatusException(String message) {
		super(message);
	}
}
