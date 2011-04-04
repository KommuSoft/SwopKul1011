package projectswop20102011.exceptions;

/**
 * An exception class thrown when a wrong hospital is used. This class is an extension of the InvalidMapItemClass.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidHospitalException extends InvalidMapItemException {

	/**
	 * Creates a new instance of <code>InvalidHospitalException</code> without detail message.
	 */
	public InvalidHospitalException() {
		super();
	}

	/**
	 * Constructs an instance of <code>InvalidHospitalException</code> with the specified detail message.
	 * @param msg the detail message.
	 */
	public InvalidHospitalException(String msg) {
		super(msg);
	}
}
