package projectswop20102011.exceptions;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class CancelEmergencyNotSupportedException extends RuntimeException{

	/**
	 * Variable registering the serivalVersionUID of this CancelEmergencyNotSupportedException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of an CancelEmergencyNotSupportedException with no detail message.
	 */
	public CancelEmergencyNotSupportedException() {
		super();
	}

	/**
	 * Creates a new CancelEmergencyNotSupportedException with a specified message.
	 * @param message
	 *		A message explaining why this Exception has been thrown.
	 */
	public CancelEmergencyNotSupportedException(String message) {
		super(message);
	}
}
