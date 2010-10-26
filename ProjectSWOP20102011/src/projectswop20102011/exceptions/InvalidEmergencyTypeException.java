package projectswop20102011.exceptions;

/**
 * An exception that will be used when the emergency type (e.g.: fire, public disturbance,...) is wrong, or the type doesn't exists.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidEmergencyTypeException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidEmergencyTypeException(String message) {
        super(message);
    }
}
