package projectswop20102011.exceptions;

/**
 * An exception class thrown when the size of a column of a ViewTable is invalid.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidViewTableSizeException extends Exception {

    /**
     * Creates a new instance of an InvalidViewTableSizeException with a given message.
     * @param message A message explaining the cause of the exception.
     */
    public InvalidViewTableSizeException(String message) {
        super(message);
    }

}
