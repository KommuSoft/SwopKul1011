package projectswop20102011.exceptions;

/**
 * An exception that will be thrown when an application attempts to do an invalid finish job actuib.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidFinishJobException extends Exception {

	/**
	 * Constructs an instance of <code>InvalidFinishJobException</code> with the specified detail message.
	 * @param msg
	 *		the detail message.
	 */
	public InvalidFinishJobException(String msg) {
		super(msg);
	}
}
