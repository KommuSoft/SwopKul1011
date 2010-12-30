package projectswop20102011.exceptions;

/**
 * An exception class used if the description parameter of an object is invalid.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidDescriptionException extends Exception {

    /**
     * Creates a new instance of <code>InvalidDescriptionException</code> without detail message.
     */
    public InvalidDescriptionException() {
    }


    /**
     * Constructs an instance of <code>InvalidDescriptionException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidDescriptionException(String msg) {
        super(msg);
    }
}
