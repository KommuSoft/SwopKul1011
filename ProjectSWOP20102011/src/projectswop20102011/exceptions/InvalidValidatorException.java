package projectswop20102011.exceptions;

/**
 * An exception class used when a Validator is invalid
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidValidatorException extends Exception {

    /**
     * Creates a new instance of an InvalidValidatorException with a given message.
     * @param message A message that explains why this exception has been thrown.
     */
    public InvalidValidatorException(String message) {
        super(message);
    }

}
