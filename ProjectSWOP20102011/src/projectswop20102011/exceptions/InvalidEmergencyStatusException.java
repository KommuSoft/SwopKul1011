package projectswop20102011.exceptions;

/**
 * An exception thrown for invalid usage or parsing of an Emergency Status.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidEmergencyStatusException extends Exception {

    public InvalidEmergencyStatusException(String message) {
        super(message);
    }

}