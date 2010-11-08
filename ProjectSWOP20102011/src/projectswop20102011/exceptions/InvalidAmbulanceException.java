package projectswop20102011.exceptions;

/**
 * An exception class thrown when a wrong ambulance is used. This class is an extension of the InvalidUnitException.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidAmbulanceException extends InvalidUnitException {

    /**
     * Creates a new instance of <code>InvalidAmbulanceException</code> without detail message.
     */
    public InvalidAmbulanceException() {
        super();
    }

    /**
     * Constructs an instance of <code>InvalidAmbulanceException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidAmbulanceException(String msg) {
        super(msg);
    }
}
