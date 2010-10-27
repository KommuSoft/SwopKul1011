package projectswop20102011.exceptions;

/**
 * An exception thrown for invalid usage or parsing of an Emergency Status.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidEmergencyStatusException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidEmergencyStatusException(String message) {
        super(message);
    }
}
