package projectswop20102011.domain;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;

/**
 * An enumeration that represents the status of an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public enum EmergencyStatus {

    //TODO: implement methods!!
    RECORDED_BUT_UNHANDLED("recorded but unhandled") {

        @Override
        void assignUnits(Emergency emergency, List<Unit> units) {
            try {
                emergency.setStatus(EmergencyStatus.RESPONSE_IN_PROGRESS);
            } catch (InvalidEmergencyStatusException ex) {
                //We assume this can't happen.
                Logger.getLogger(EmergencyStatus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        void finishUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException {
            throw new InvalidEmergencyStatusException("Can't finish units from an unhandled emergency.");
        }

        @Override
        void withdrawUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException {
            throw new InvalidEmergencyStatusException("Can't withdraw units from an unhandled emergency.");
        }
    },
    RESPONSE_IN_PROGRESS("response in progress") {

        @Override
        void assignUnits(Emergency emergency, List<Unit> units) {
        }

        @Override
        void finishUnit(Emergency emergency, Unit unit) {
        }

        @Override
        void withdrawUnit(Emergency emergency, Unit unit) {
        }
    },
    COMPLETED("completed") {

        @Override
        void assignUnits(Emergency emergency, List<Unit> units) throws InvalidEmergencyStatusException {
            throw new InvalidEmergencyStatusException("Unable to assign units to a completed emergency.");
        }

        @Override
        void finishUnit(Emergency emergency, Unit unit) {
        }

        @Override
        void withdrawUnit(Emergency emergency, Unit unit) {

        }
    };
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
     * @param units The units to allocate to the emergency.
     * @note This method has a package visibility: Only the emergency class can call this method.
     */
    abstract void assignUnits(Emergency emergency, List<Unit> units) throws InvalidEmergencyStatusException, Exception;

    /**
     * A method representing a transition where a unit signals it has finished it's job.
     * @param emergency The emergency where the action takes place.
     * @param unit The unit that signals it has finished it's job.
     * @note This method has a package visibility: Only the emergency class can call this method.
     */
    abstract void finishUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException, Exception;

    /**
     * A method that handles a situation where a given unit withdraws from a given emergency.
     * @param emergency The emergency the unit is withdrawing from.
     * @param unit The unit that withdraws from an emergency.
     * @note This method has a package visibility: Only the emergency class can call this method.
     */
    abstract void withdrawUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException, Exception;
}
