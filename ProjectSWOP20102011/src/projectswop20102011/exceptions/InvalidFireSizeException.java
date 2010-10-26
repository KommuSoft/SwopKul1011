package projectswop20102011.exceptions;

/**
 * An exception that will be thrown for invalid usage of a FireSize or parsing to an unknown FireSize.
 * @author Willem Van Onsem
 */
public class InvalidFireSizeException extends Exception {

    /**
     * Creates a new InvalidFireSize with a specified message.
     * @param message A message explaining why this Exception has been thrown.
     */
    public InvalidFireSizeException(String message) {
        super(message);
    }

}
