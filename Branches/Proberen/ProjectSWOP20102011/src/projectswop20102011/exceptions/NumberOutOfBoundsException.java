package projectswop20102011.exceptions;

/**
 * An exception class that reports exceptions where numbers are out of a specific bound.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NumberOutOfBoundsException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of <code>NumberOutOfBoundsException</code> without detail message.
	 */
	public NumberOutOfBoundsException() {
	}

	/**
	 * Constructs an instance of <code>NumberOutOfBoundsException</code> with the specified detail message.
	 * @param msg The detail message.
	 */
	public NumberOutOfBoundsException(String msg) {
		super(msg);
	}
}
