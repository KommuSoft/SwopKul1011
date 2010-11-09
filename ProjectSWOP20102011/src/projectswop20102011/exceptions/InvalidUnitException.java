package projectswop20102011.exceptions;

/**
 * An exception class used when a Unit is invalid.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidUnitException extends InvalidUnitBuildingException {

	/**
	 * Constructs an InvalidUnitBuildingException with no detail message.
	 */
	public InvalidUnitException() {
		super();
	}

	/**
	 * Creates a new instance of an InvalidUnitException with a given message.
	 * @param message A message explaining why this exception has been thrown.
	 */
	public InvalidUnitException(String message) {
		super(message);
	}
}
