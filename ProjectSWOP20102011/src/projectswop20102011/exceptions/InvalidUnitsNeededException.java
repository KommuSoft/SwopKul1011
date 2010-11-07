package projectswop20102011.exceptions;

/**
 * An exception class used when an array hasn't the proper length.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidUnitsNeededException extends Exception {

    /**
     * Creates a new instance of an InvalidUnitsNeededException with a given message.
     * @param message A message explaining the cause of the exception.
     */
    public InvalidUnitsNeededException(String message) {
        super(message);
    }

}
