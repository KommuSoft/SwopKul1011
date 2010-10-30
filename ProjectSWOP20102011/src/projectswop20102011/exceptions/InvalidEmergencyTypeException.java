package projectswop20102011.exceptions;

/**
 * An exception that will be used when the emergency type (e.g.: fire, public disturbance,...) is wrong, or the type doesn't exists.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidEmergencyTypeException extends Exception {

	/**
     * Variable registering the serivalVersionUID of this InvalidEmergencyTypeException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of an InvalidEmergencyTypeException with no detail message.
     */
	public InvalidEmergencyTypeException(){
		super();
	}

    /**
     * Creates a new instance of an InvalidEmergencyTypeException with a specified message.
     * @param message
	 *		A message explaining the cause of the exception.
     */
    public InvalidEmergencyTypeException(String message) {
        super(message);
    }
}
