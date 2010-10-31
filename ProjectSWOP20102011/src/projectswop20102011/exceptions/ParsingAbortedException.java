package projectswop20102011.exceptions;

/**
 * An exception class used when the user aborts a parsing procedure (with the keyword "abort").
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ParsingAbortedException extends Exception {

    public ParsingAbortedException(String message) {
        super(message);
    }

}
