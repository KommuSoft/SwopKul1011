package projectswop20102011.exceptions;

/**
 * An exception class used when a UnitValidator is invalid
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidUnitValidatorException extends Exception {

    /**
     * Creates a new instance of an InvalidUnitValidatorException with a given message.
     * @param message A message that explains why this exception has been thrown.
     */
    public InvalidUnitValidatorException(String message) {
        super(message);
    }

}
