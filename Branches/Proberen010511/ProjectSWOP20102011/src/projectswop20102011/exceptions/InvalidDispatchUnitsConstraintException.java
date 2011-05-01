package projectswop20102011.exceptions;

/**
 * An exception class used when a DispatchUnitConstraint is invalid.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidDispatchUnitsConstraintException extends Exception {

    /**
     * Creates a new instance of a InvalidDispatchUnitsConstraintsException with a given message.
     * @param message A message explaining why this exception has been thrown.
     */
    public InvalidDispatchUnitsConstraintException(String message) {
        super(message);
    }
}
