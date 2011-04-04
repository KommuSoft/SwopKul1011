package projectswop20102011.exceptions;

/**
 * An exception class used when a list of DispatchUnitsConstraints is invalid.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidConstraintListException extends Exception {

    /**
     * Creates a new instance of an InvalidConstraintListException with a given message.
     * @param message A message explaining why this exception has been thrown.
     */
    public InvalidConstraintListException(String message) {
        super(message);
    }

}
