package projectswop20102011.exceptions;

/**
 * An exception class thrown when an expression can't be parsed properly
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ParsingException extends Exception {

    /**
     * Creates a new instance of a ParsingException with a specified message.
     * @param message A message that explains why this message has been thrown.
     */
    public ParsingException(String message) {
        super(message);
    }

}
