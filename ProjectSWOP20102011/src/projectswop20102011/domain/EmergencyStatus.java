package projectswop20102011.domain;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;

/**
 * An enumeration that represents the status of an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public enum EmergencyStatus {

    RECORDED_BUT_UNHANDLED("recorded but unhandled") {

        /**
         * Assigns the given units to the given emergency.
         * @param emergency The emergency where the units are assigned to.
         * @param units A list of units we want to assign to that emergency.
         * @throws InvalidEmergencyException If the given list of exception does not met the constraints produces by the emergency.
         */
        @Override
        void assignUnits(Emergency emergency, List<Unit> units) throws InvalidEmergencyException {
            emergency.getUnitsNeeded().assignUnitsToEmergency(units);
            try {
                emergency.setStatus(EmergencyStatus.RESPONSE_IN_PROGRESS);
            } catch (InvalidEmergencyStatusException ex) {
                //We assume this can't happen.
                Logger.getLogger(EmergencyStatus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Finish the given unit of it's job from the given emergency.
         * @param emergency The emergency where the unit is assigned to.
         * @param unit The unit that want's to finish it's job.
         * @throws InvalidEmergencyStatusException Always, units never can finish from an emergency where no units are assigned to.
         */
        @Override
        void finishUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException {
            throw new InvalidEmergencyStatusException("Can't finish units from an unhandled emergency.");
        }

        /**
         * Withdraws the given unit from is's assignment to the given emergency.
         * @param emergency The emergency the unit want's to withdraw from.
         * @param unit The unit that want's to withdraw.
         * @throws InvalidEmergencyStatusException Always, units never can finish from an emergency where no units are assigned to.
         */
        @Override
        void withdrawUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException {
            throw new InvalidEmergencyStatusException("Can't withdraw units from an unhandled emergency.");
        }
    },
    RESPONSE_IN_PROGRESS("response in progress") {

        /**
         * Assigns the given units to the given emergency.
         * @param emergency The emergency where the units are assigned to.
         * @param units The units that are assigned to the given emergency.
         * @throws InvalidEmergencyException If the given units does not met the constraints of the given emergency.
         */
        @Override
        void assignUnits(Emergency emergency, List<Unit> units) throws InvalidEmergencyException {
            emergency.getUnitsNeeded().assignUnitsToEmergency(units);
        }

        /**
         * Let the given unit finish his job from the given emergency.
         * @param emergency The emergency where the unit was assigned to.
         * @param unit The unit that want's to finish his job.
         */
        @Override
        void finishUnit(Emergency emergency, Unit unit) {
            emergency.getUnitsNeeded().unitFinishedJob(unit);
            if(emergency.getUnitsNeeded().canFinish()) {
                try {
                    emergency.setStatus(COMPLETED);
                } catch (InvalidEmergencyStatusException ex) {
                    //We assume this can't happen
                    Logger.getLogger(EmergencyStatus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        /**
         * Withdraws the given unit from the given emergency.
         * @param emergency The emergency where the unit want's to withdraw from.
         * @param unit The unit that want's to withdraw.
         */
        @Override
        void withdrawUnit(Emergency emergency, Unit unit) {
            emergency.getUnitsNeeded().WithdrawUnit(unit);
        }
    },
    COMPLETED("completed") {

        @Override
        void assignUnits(Emergency emergency, List<Unit> units) throws InvalidEmergencyStatusException {
            throw new InvalidEmergencyStatusException("Unable to assign units to a completed emergency.");
        }

        @Override
        void finishUnit(Emergency emergency, Unit unit) {
            //TODO: Implement
        }

        @Override
        void withdrawUnit(Emergency emergency, Unit unit) {
            //TODO: Implement
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
