package projectswop20102011.exceptions;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidClassParameterException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>InvalidClassParameterException</code> without detail message.
     */
    public InvalidClassParameterException() {
		super();
    }

    /**
     * Constructs an instance of <code>InvalidClasParameterException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidClassParameterException(String msg) {
        super(msg);
    }
}