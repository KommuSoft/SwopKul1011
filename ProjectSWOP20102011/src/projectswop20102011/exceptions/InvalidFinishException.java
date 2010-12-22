package projectswop20102011.exceptions;

/**
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidFinishException extends Exception {

	/**
	 * Creates a new InvalidFinishException instance with a given message.
	 * @param message A message that specifieds why the error was thrown.
	 */
	public InvalidFinishException(String message) {
		super(message);
	}
}
