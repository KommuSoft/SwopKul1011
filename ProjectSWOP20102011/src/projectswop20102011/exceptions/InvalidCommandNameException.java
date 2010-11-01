package projectswop20102011.exceptions;

/**
 * An exception class used when the name of a command is invalid (example: not effective).
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidCommandNameException extends Exception {

    /**
     * Creates a new instance of an InvalidCommandNameException with a given message.
     * @param message A message explaining why the exception has been thrown.
     */
    public InvalidCommandNameException(String message) {
        super(message);
    }

}
