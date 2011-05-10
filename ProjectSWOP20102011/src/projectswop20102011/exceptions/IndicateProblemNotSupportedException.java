package projectswop20102011.exceptions;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class IndicateProblemNotSupportedException extends RuntimeException {

	/**
	 * Variable registering the serivalVersionUID of this IndicateProblemNotSupportedException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of an IndicateProblemNotSupportedException with no detail message.
	 */
	public IndicateProblemNotSupportedException() {
		super();
	}

	/**
	 * Creates a new IndicateProblemNotSupportedException with a specified message.
	 * @param message
	 *		A message explaining why this Exception has been thrown.
	 */
	public IndicateProblemNotSupportedException(String message) {
		super(message);
	}
}
