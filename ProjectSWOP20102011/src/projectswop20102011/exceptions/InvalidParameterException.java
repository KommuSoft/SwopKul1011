package projectswop20102011.exceptions;

/**
 * Thrown when an application attempts to create an object with the wrong parameters.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidParameterException extends Exception{

	/**
     * Variable registering the serivalVersionUID of this InvalidParameterException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an InvalidParameterException with no detail message.
     */
    public InvalidParameterException() {
        super();
    }

    /**
     * Constructs an InvalidParameterException with the specified detail message.
     * @param message
     *      The detail message.
     */
    public InvalidParameterException(String message) {
        super(message);
    }
}