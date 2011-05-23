package projectswop20102011.exceptions;

/**
 * An exception class used when the name of an object is invalid.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidNameException extends Exception {

    /**
     * Creates a new instance of <code>InvalidNameException</code> without detail message.
     */
    public InvalidNameException() {
        super();
    }


    /**
     * Constructs an instance of <code>InvalidNameException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidNameException(String msg) {
        super(msg);
    }
}
