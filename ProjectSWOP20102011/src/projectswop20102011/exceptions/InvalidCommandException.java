package projectswop20102011.exceptions;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidCommandException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>InvalidCommandException</code> without detail message.
     */
    public InvalidCommandException() {
    }

    /**
     * Constructs an instance of <code>InvalidCommandException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
