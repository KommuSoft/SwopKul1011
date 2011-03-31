package projectswop20102011.exceptions;

/**
 * An exception class used when a Targetable object is Invalid.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidTargetableException extends Exception {

    /**
     * Creates a new InvalidTargetableException with a given error message.
     * @param message The message explaining why the exception has been thrown.
     */
    public InvalidTargetableException(String message) {
        super(message);
    }

}
