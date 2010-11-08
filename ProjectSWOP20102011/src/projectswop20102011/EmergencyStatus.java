package projectswop20102011;

import projectswop20102011.exceptions.InvalidEmergencyStatusException;

/**
 * An enumeration that represents the status of an emergency.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public enum EmergencyStatus {

    RECORDED_BUT_UNHANDLED("recorded but unhandled"),
    RESPONSE_IN_PROGRESS("response in progress"),
    FINISHED("finished");
    /**
     * The textual representation of an EmergencyStatus.
     */
    private final String textual;

    private EmergencyStatus (String textual) {
        this.textual = textual;
    }

    /**
     * Returns the textual representation of the EmergencyStatus.
     * @return A textual representation of the EmergencyStatus.
     */
    public String getTextual() {
        return textual;
    }

    /**
     * Returns a textual representation of the EmergencyStatus.
     * @return A textual representation of the EmergencyStatus.
     */
    @Override
    public String toString() {
        return getTextual();
    }

    /**
     * Tests if a given textual representation of an EmergencyStatus matches this EmergencyStatus.
     * @param textualRepresentation
	 *		The textual representation to test.
     * @return True if the textual representation matches, otherwise false.
     */
    public boolean matches(String textualRepresentation) {
        return this.getTextual().equals(textualRepresentation.toLowerCase());
    }

    /**
     * Parses a textual representation into its EmergencyStatus equivalent.
     * @param textualRepresentation
	 *		The textual representation to parse.
     * @return An EmergencyStatus that is the equivalent to the textual representation.
     * @throws InvalidEmergencyStatusException
	 *		If no EmergencyStatus matches the textual representation.
     */
    public static EmergencyStatus parse(String textualRepresentation) throws InvalidEmergencyStatusException {
        for (EmergencyStatus es : EmergencyStatus.values()) {
            if (es.matches(textualRepresentation)) {
                return es;
            }
        }
        throw new InvalidEmergencyStatusException(String.format("Unknown emergency status level \"%s\".", textualRepresentation));
    }

}
