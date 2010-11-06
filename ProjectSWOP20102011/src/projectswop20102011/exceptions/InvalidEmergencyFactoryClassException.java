package projectswop20102011.exceptions;

/**
 * An exception class, thrown while creating an EmergencyFactory with an invalid emergencyClass
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidEmergencyFactoryClassException extends Exception {

    /**
     * Creates a new instance of an InvalidEmergencyFactoryClassException with a given message.
     * @param message A message explaining the reason of the exception.
     */
    public InvalidEmergencyFactoryClassException(String message) {
        super(message);
    }

}
