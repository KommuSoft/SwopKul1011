package projectswop20102011;

/**
 * Thrown when an application attempts to use an invalid name.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidNameException extends Exception {

    /**
     * Variable registering the serivalVersionUID of this InvalidNameException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an InvalidNameException with no detail message.
     */
    public InvalidNameException() {
        super();
    }

    /**
     * Constructs an InvalidNameException with the specified detail message.
     * @param message
     *      The detail message.
     */
    public InvalidNameException(String message) {
        super(message);
    }
}
