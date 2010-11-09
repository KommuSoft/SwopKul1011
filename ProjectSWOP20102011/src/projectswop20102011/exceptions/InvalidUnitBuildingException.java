package projectswop20102011.exceptions;

/**
 * An exception that will be thrown when an application attempts to use an invalid unit or building.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidUnitBuildingException extends Exception {

	/**
	 * Variable registering the serivalVersionUID of this InvalidUnitBuildingException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidUnitBuildingException with no detail message.
	 */
	public InvalidUnitBuildingException() {
		super();
	}

	/**
	 * Constructs an InvalidUnitBuildingException with the specified detail message.
	 * @param message
	 *      The detail message.
	 */
	public InvalidUnitBuildingException(String message) {
		super(message);
	}
}
