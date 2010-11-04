package projectswop20102011.exceptions;

/**
 * An exception class used, to notify the user, his input cannot be parsed.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ParsingException extends Exception {


    /**
     * Constructs an instance of <code>ParsingException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ParsingException(String msg) {
        super(msg);
    }
}
