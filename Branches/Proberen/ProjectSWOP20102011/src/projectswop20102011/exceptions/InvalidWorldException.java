package projectswop20102011.exceptions;

/**
 * An exception class used when a World is used in a wrong way.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidWorldException extends Exception {

	/**
	 * Creates a new instance of an InvalidWorldException class, with a specific message.
	 * @param message A message explaining the reason of the exception.
	 */
	public InvalidWorldException(String message) {
		super(message);
	}
}
