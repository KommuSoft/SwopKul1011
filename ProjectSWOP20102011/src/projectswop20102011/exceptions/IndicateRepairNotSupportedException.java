package projectswop20102011.exceptions;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class IndicateRepairNotSupportedException extends RuntimeException {

	/**
	 * Variable registering the serivalVersionUID of this indicateRepairNotSupportedException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of an indicateRepairNotSupportedException with no detail message.
	 */
	public IndicateRepairNotSupportedException() {
		super();
	}

	/**
	 * Creates a new indicateRepairNotSupportedException with a specified message.
	 * @param message
	 *		A message explaining why this Exception has been thrown.
	 */
	public IndicateRepairNotSupportedException(String message) {
		super(message);
	}
}