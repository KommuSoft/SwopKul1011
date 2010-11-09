package projectswop20102011.exceptions;

/**
 * An exception that will be thrown when an application attempts to use an invalid name for a unitbuilding
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidUnitBuildingNameException extends Exception {

	/**
	 * Variable registering the serivalVersionUID of this InvalidUnitBuildingNameException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidUnitBuildingNameException with no detail message.
	 */
	public InvalidUnitBuildingNameException() {
		super();
	}

	/**
	 * Constructs an InvalidUnitBuildingNameException with the specified detail message.
	 * @param message
	 *      The detail message.
	 */
	public InvalidUnitBuildingNameException(String message) {
		super(message);
	}
}
