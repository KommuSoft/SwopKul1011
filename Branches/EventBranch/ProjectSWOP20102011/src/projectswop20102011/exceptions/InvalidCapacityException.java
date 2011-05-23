package projectswop20102011.exceptions;

/**
 * Thrown when an application attempts to use an invalid amount of parameters to create an object with a factory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidCapacityException extends Exception{

	/**
	 * Constructs an InvalidCapacityException with no detail message.
	 */
	public InvalidCapacityException() {
		super();
	}

	/**
	 * Constructs an InvalidAmountOfParametersException with the specified detail message.
	 * @param message
	 *      The detail message.
	 */
	public InvalidCapacityException(String message) {
		super(message);
	}
}