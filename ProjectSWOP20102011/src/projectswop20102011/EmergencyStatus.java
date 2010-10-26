package projectswop20102011;

import projectswop20102011.exceptions.InvalidEmergencyStatusException;

/**
 * An enumeration that represents the status of an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public enum EmergencyStatus {

    RECORDED_BUT_UNHANDLED("recorded but unhandled"),
    RESPONSE_IN_PROGRESS("response in progress"),
    FINISHED("finished");
    /**
     * textual representation of an EmergencyStatus
     */
    private final String textual;

    private EmergencyStatus (String textual) {
        this.textual = textual;
    }
    /**
     * Gets the textual representation of the EmergencyStatus.
     * @return A textual representation of the EmergencyStatus.
     */
    public String getTextual() {
        return this.textual;
    }

    /**
     * Returns a textual representation of the EmergencyStatus.
     * @return A textual representation of the EmergencyStatus.
     */
    @Override
    public String toString() {
        return this.getTextual();
    }

    /**
     * Tests if a given textual representation of an EmergencyStatus matches this EmergencyStatus.
     * @param textualRepresentation the textual representation to test.
     * @return True if the textual representation matches, otherwise false.
     */
    public boolean matches(String textualRepresentation) {
        return this.getTextual().equals(textualRepresentation.toLowerCase());
    }

    /**
     * Parses a textual representation intro its EmergencyStatus equivalent.
     * @param textualRepresentation the textual representation to parse.
     * @return An EmergencyStatus that is the equivalent of the textual representation.
     * @throws InvalidEmergencyStatusException If no EmergencyStatus matches the textual representation.
     */
    public static FireSize parse(String textualRepresentation) throws InvalidEmergencyStatusException {
        for (FireSize es : FireSize.values()) {
            if (es.matches(textualRepresentation)) {
                return es;
            }
        }
        throw new InvalidEmergencyStatusException(String.format("Unknown emergency status level \"%s\".", textualRepresentation));
    }

}
