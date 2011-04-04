package projectswop20102011.exceptions;

/**
 * An exception thrown for invalid usage or parsing of an Emergency Status.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidEmergencyStatusException extends Exception {

	/**
	 * Variable registering the serivalVersionUID of this InvalidEmergencyStatusException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of an InvalidEmergencyStatusException with no detail message.
	 */
	public InvalidEmergencyStatusException() {
		super();
	}

	/**
	 * Creates a new instance of an InvalidEmergencyStatusException with a specified message.
	 * @param message
	 *		A message explaining the cause of the exception.
	 */
	public InvalidEmergencyStatusException(String message) {
		super(message);
	}
}
