package projectswop20102011.exceptions;

/**
 * An exception class thrown when an invalid type name is inserted in the EmergencyFactory constructor.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidEmergencyTypeNameException extends Exception {

    /**
     * Creates a new InvalidEmergencyTypeNameException instance with a given message.
     * @param message A message that specifieds why the error was thrown.
     */
    public InvalidEmergencyTypeNameException(String message) {
        super(message);
    }

}
