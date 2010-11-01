package projectswop20102011.exceptions;

/**
 * An exception thrown when a controller is invalid (null reference,...)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidControllerException extends Exception {

    /**
     * Creates a new instance of an InvalidControllerException with a given message.
     * @param message A message explaining the cause of the exception.
     */
    public InvalidControllerException(String message) {
        super(message);
    }

}
