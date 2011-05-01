package projectswop20102011.exceptions;

/**
 * An exception that will be thrown when an application attempts to do an invalid withdrawal.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidWithdrawalException extends Exception {

	/**
	 * Constructs an instance of <code>InvalidWithdrawalException</code> with the specified detail message.
	 * @param msg
	 *		the detail message.
	 */
	public InvalidWithdrawalException(String msg) {
		super(msg);
	}
}
