package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidEmergencySeverityException;

/**
 * An enumeration that represents the severity of an emergency.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public enum EmergencySeverity {

    BENIGN("benign"),
    NORMAL("normal"),
    SERIOUS("serious"),
    URGENT("urgent");
    /**
     * The textual representation of the emergency severity.
     */
    private final String textual;

    /**
     * Creates a new instance of the EmergencySeverity class with a given textual representation.
     * @param textual
     *		The textual representation of the EmergencySeverity, used for parsing and user interaction.
     * @post The textual representation is set to the given textual representation.
     *		| new.toString().equals(textual)
     */
    private EmergencySeverity(String textual) {
        this.textual = textual;
    }

    /**
     * Returns the textual representation of the EmergencyStatus.
     * @return A textual representation of the EmergencyStatus.
     */
    @Override
    public String toString() {
        return textual;
    }

    /**
     * Tests if a given textual representation of an EmergencyStatus matches this EmergencyStatus.
     * @param textualRepresentation
     *		The textual representation to test.
     * @return True if the textual representation matches, otherwise false.
     */
    public boolean matches(String textualRepresentation) {
        return toString().equals(textualRepresentation.toLowerCase());
    }

    /**
     * Parses a textual representation into its EmergencyStatus equivalent.
     * @param textualRepresentation
     *		The textual representation to parse.
     * @return An EmergencyStatus that is the equivalent of the textual representation.
     * @throws InvalidEmergencySeverityException
     *		If no EmergencyStatus matches the textual representation.
     */
    public static EmergencySeverity parse(String textualRepresentation) throws InvalidEmergencySeverityException {
        for (EmergencySeverity es : EmergencySeverity.values()) {
            if (es.matches(textualRepresentation)) {
                return es;
            }
        }
        throw new InvalidEmergencySeverityException(String.format("Unknown severity level \"%s\".", textualRepresentation));
    }

    /**
     * Calculates the maximum severity of this severity and the otherSeverity.
     * @param otherSeverity The other severity to calculate the severity level from.
     * @return The maximum severity level of this and otherSeverity.
     */
    public EmergencySeverity getMaximum(EmergencySeverity otherSeverity) {
        if (this.ordinal() >= otherSeverity.ordinal()) {
            return this;
        } else {
            return otherSeverity;
        }
    }
}
