package projectswop20102011.exceptions;

/**
 * An exception used when the expression that is being parsed isn't formatted in the propper way.
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidExpressionFormatException extends Exception {

	/**
     * Variable registering the serivalVersionUID of this InvalidExpressionFormatException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>InvalidExpressionFormatException</code> without detail message.
     */
    public InvalidExpressionFormatException() {
		super();
    }

    /**
     * Constructs an instance of <code>InvalidExpressionFormatException</code> with the specified detail message.
     * @param message the detail message.
     */
    public InvalidExpressionFormatException(String message) {
        super(message);
    }
}
