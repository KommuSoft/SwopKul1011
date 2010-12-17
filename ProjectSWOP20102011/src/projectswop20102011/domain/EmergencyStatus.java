package projectswop20102011.domain;

import java.util.List;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;

/**
 * An enumeration that represents the status of an emergency.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public enum EmergencyStatus {

	RECORDED_BUT_UNHANDLED("recorded but unhandled"),
	RESPONSE_IN_PROGRESS("response in progress"),
	COMPLETED("completed");
	/**
	 * The textual representation of an EmergencyStatus.
	 */
	private final String textual;

	/**
	 * Creates a new instance of the EmergencyStatus class with a given textual representation.
	 * @param textual
	 *		The textual representation of the EmergencyStatus, used for parsing and user interaction.
	 * @post The textual representation is set to the given textual representation.
	 *		| new.getTextual().equals(textual)
	 */
	private EmergencyStatus(String textual) {
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
	 * @return An EmergencyStatus that is the equivalent of the textual representation.
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

        
        /**
         * A method representing a potential transition where units are allocated to the emergency.
         * @param emergency The emergency where the action takes place.
         * @param units
         */
        public abstract void assign_units (Emergency emergency, List<Unit> units);

}
