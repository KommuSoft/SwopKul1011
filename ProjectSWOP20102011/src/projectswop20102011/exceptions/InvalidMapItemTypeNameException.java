package projectswop20102011.exceptions;

/**
 * An exception class thrown when an invalid type name is inserted in the MapItemFactory constructor.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
//TODO: Deze exception-klasse wordt bij factories gebruikt, maar we hebben geen factories... :)
public class InvalidMapItemTypeNameException extends Exception {

	/**
	 * Variable registering the serivalVersionUID of this InvalidMapItemTypeNameException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidMapItemTypeNameException with no detail message.
	 */
	public InvalidMapItemTypeNameException() {
		super();
	}

	/**
	 * Constructs an InvalidMapItemTypeNameException with the specified detail message.
	 * @param message
	 *      The detail message.
	 */
	public InvalidMapItemTypeNameException(String message) {
		super(message);
	}
}
