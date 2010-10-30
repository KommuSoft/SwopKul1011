package projectswop20102011.exceptions;

/**
 * An exception class thrown when an expression can't be parsed properly
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ParsingException extends Exception {

	/**
     * Variable registering the serivalVersionUID of this ParsingException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an ParsingException with no detail message.
     */
    public ParsingException() {
        super();
    }

    /**
     * Creates a new instance of a ParsingException with a specified message.
     * @param message A message that explains why this message has been thrown.
     */
    public ParsingException(String message) {
        super(message);
    }

}
