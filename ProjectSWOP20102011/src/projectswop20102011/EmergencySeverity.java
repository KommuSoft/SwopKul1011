package projectswop20102011;

import projectswop20102011.exceptions.InvalidEmergencySeverityException;

/**
 * An enumeration that represents the severity of an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public enum EmergencySeverity {

    BENIGN("benign"),
    NORMAL("normal"),
    SERIOUS("serious"),
    URGENT("urgent");
    private final String textual;

    /**
     * Creates a new instance of the EmergencySeverity class with a given textual representation.
     * @param textual The textual representation of the EmergencySeverity, used for parsing and user interaction.
     */
    EmergencySeverity(String textual) {
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
     * @throws InvalidEmergencySeverityException If no EmergencyStatus matches the textual representation.
     */
    public static EmergencySeverity parse(String textualRepresentation) throws InvalidEmergencySeverityException {
        for (EmergencySeverity es : EmergencySeverity.values()) {
            if (es.matches(textualRepresentation)) {
                return es;
            }
        }
        throw new InvalidEmergencySeverityException(String.format("Unknown severity level \"%s\".", textualRepresentation));
    }
}
