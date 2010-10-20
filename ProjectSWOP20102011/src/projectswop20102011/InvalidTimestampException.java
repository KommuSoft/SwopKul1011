package projectswop20102011;

/**
 * Thrown when an application attempts to use an invalid timestamp.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidTimestampException extends Exception {

    /**
     * Variable registering the serivalVersionUID of this InvalidNameException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an InvalidNameException with no detail message.
     */
    public InvalidTimestampException() {
        super();
    }

    /**
     * Constructs an InvalidNameException with the specified detail message.
     * @param message
     *      The detail message.
     */
    public InvalidTimestampException(String message) {
        super(message);
    }
}
