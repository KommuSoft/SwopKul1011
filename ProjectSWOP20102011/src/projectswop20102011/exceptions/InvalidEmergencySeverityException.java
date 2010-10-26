package projectswop20102011.exceptions;

/**
 * An exception thrown for invalid usage or parsing of an Emergency Seviri
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InvalidEmergencySeverityException extends Exception {

    public InvalidEmergencySeverityException(String message) {
        super(message);
    }
}
