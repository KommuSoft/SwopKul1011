package projectswop20102011.exceptions;

/**
 * An exception class thrown when the name of a column of a ViewTable is invalid.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidViewTableNameException extends Exception {

    /**
     * Creates a new instance of an InvalidViewTableException with a specified message.
     * @param message A message explaining the cause of the exception.
     */
    public InvalidViewTableNameException(String message) {
        super(message);
    }

}
