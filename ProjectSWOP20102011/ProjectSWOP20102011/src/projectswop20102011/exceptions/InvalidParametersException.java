package projectswop20102011.exceptions;

/**
 * An exception class used when the parameters are invalid.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidParametersException extends Exception {

    /**
     * Creates a new instance of <code>InvalidParametersException</code> without detail message.
     */
    public InvalidParametersException() {
    }


    /**
     * Constructs an instance of <code>InvalidParametersException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidParametersException(String msg) {
        super(msg);
    }
}
