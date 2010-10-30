package projectswop20102011.exceptions;

/**
 * Thrown when an application attempts to use an invalid not time sensitive unit or building.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class InvalidNotTimeSensitiveUnitBuildingException extends Exception{
	/**
     * Variable registering the serivalVersionUID of this InvalidEmergencyStatusException.
     */
    private static final long serialVersionUID = 1L;

	/**
     * Constructs an InvalidNotTimeSensitiveUnitBuildingException with the specified detail message.
     * @param message
     *      The detail message.
     */
	public InvalidNotTimeSensitiveUnitBuildingException(String message) {
		super(message);
	}

}