package projectswop20102011.exceptions;

/**
 * An exception thrown for invalid usage or parsing of a SendableSeverity.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidSendableSeverityException extends Exception {

	/**
	 * Variable registering the serivalVersionUID of this InvalidSendableSeverityException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of an InvalidSendableSeverityException with no detail message.
	 */
	public InvalidSendableSeverityException() {
		super();
	}

	/**
	 * Creates a new instance of an InvalidSendableSeverityException with a specified message.
	 * @param message
	 *		A message explaining the cause of the exception.
	 */
	public InvalidSendableSeverityException(String message) {
		super(message);
	}
}
