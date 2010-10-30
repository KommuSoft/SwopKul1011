package projectswop20102011.exceptions;

/**
 * An exception thrown for invalid usage or parsing of an Emergency Severity.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidEmergencySeverityException extends Exception {

	/**
     * Variable registering the serivalVersionUID of this InvalidEmergencySeverityException.
     */
    private static final long serialVersionUID = 1L;
	
    /**
     * Creates a new instance of an InvalidEmergencySeverityException with no detail message.
     */
	public InvalidEmergencySeverityException(){
		super();
	}

    /**
     * Creates a new instance of an InvalidEmergencySeverityException with a specified message.
     * @param message
	 *		A message explaining the cause of the exception.
     */
    public InvalidEmergencySeverityException(String message) {
        super(message);
    }
}
