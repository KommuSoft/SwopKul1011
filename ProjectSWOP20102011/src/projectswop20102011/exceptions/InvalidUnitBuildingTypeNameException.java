package projectswop20102011.exceptions;

/**
 * An exception class thrown when an invalid type name is inserted in the UnitBuildingFactory constructor.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidUnitBuildingTypeNameException extends Exception {

	/**
	 * Variable registering the serivalVersionUID of this InvalidUnitBuildingTypeNameException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidUnitBuildingTypeNameException with no detail message.
	 */
	public InvalidUnitBuildingTypeNameException() {
		super();
	}

	/**
	 * Constructs an InvalidUnitBuildingTypeNameException with the specified detail message.
	 * @param message
	 *      The detail message.
	 */
	public InvalidUnitBuildingTypeNameException(String message) {
		super(message);
	}
}
