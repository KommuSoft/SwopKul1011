package projectswop20102011.exceptions;

/**
 * Thrown when an application attempts to use an invalid amount of parameters to create an object with a factory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidAmountOfParametersException extends Exception{

	/**
	 * Variable registering the serivalVersionUID of this InvalidAmountOfParametersException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidAmountOfParametersException with no detail message.
	 */
	public InvalidAmountOfParametersException() {
		super();
	}

	/**
	 * Constructs an InvalidAmountOfParametersException with the specified detail message.
	 * @param message
	 *      The detail message.
	 */
	public InvalidAmountOfParametersException(String message) {
		super(message);
	}
}
