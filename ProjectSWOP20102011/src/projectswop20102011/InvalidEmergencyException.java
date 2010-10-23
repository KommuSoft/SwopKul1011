package projectswop20102011;

/**
 * Thrown when an application attempts to use an invalid emergency.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidEmergencyException extends Exception{
	/**
     * Variable registering the serivalVersionUID of this InvalidEmergencyException.
     */
    private static final long serialVersionUID = 1L;


    /**
     * Constructs an InvalidEmergencyException with no detail message.
     */
    public InvalidEmergencyException() {
        super();
    }

    /**
     * Constructs an InvalidEmergencyException with the specified detail message.
     * @param message
     *      The detail message.
     */
    public InvalidEmergencyException(String message) {
        super(message);
    }
}