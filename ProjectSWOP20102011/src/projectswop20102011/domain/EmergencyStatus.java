package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collection;
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

	/**
	 * A state where the emergency is recorded by the operator but not yet handled by the dispatcher.
	 */
	RECORDED_BUT_UNHANDLED("recorded but unhandled") {

		/**
		 * Assign the given units to a given emergency.
		 * @param emergency
		 *      The emergency the given units must be assigned to.
		 * @param units
		 *      A list of units to assign.
		 * @effect The given units are assigned to the given emergency.
		 *      | emergency.getUnitsNeeded().assignUnitsToEmergency(units)
		 * @throws InvalidEmergencyException
		 *      If the given units can't be assigned to the given emergency.
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
		 * Finish the job of the given unit for a given emergency.
		 * @param emergency
		 *      The emergency where the action takes place.
		 * @param unit
		 *      The unit that must be finished.
		 * @throws InvalidEmergencyStatusException
		 *      If The status of the emergency is invalid. A unit can't be finished
		 *      if the emergency is recorded but unhandled.
		 */
		@Override
		void finishUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException {
			throw new InvalidEmergencyStatusException("Can't finish units from an unhandled emergency.");
		}

		/**
		 * Withdraw a given unit from a given emergency.
		 * @param emergency
		 *      The emergency the given unit must be withdrawn from.
		 * @param unit
		 *      The unit that must be withdrawn.
		 * @throws InvalidEmergencyStatusException
		 *      If The status of the emergency is invalid. A unit can't be withdrawn
		 *      if the emergency is recorded but unhandled.
		 */
		@Override
		void withdrawUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException {
			throw new InvalidEmergencyStatusException("Can't withdraw units from an unhandled emergency.");
		}

		/**
		 * Decides whether a given list of units can be assigned to a given emergency.
		 * @param emergency
		 *      The emergency that must be checked.
		 * @param unit
		 *      The units that must be checked.
		 * @return True if the given units can be assigned, false otherwise.
		 */
		@Override
		boolean canAssignUnits(Emergency emergency, List<Unit> unit) {
			return emergency.getUnitsNeeded().canAssignUnitsToEmergency(unit);
		}

        @Override
        Collection<Unit> getPolicyProposal(Emergency emergency, List<? extends Unit> availableUnits) {
            return emergency.getUnitsNeeded().getPolicyProposal(availableUnits);
        }

        @Override
        boolean canBeResolved (Emergency emergency, Collection<Unit> availableUnits) {
            return emergency.getUnitsNeeded().CanBeResolved(availableUnits);
        }
    },
    /**
     * A state of an emergency where the dispatcher has already sent units (this does not mean there are still units working or already finished).
     */
    RESPONSE_IN_PROGRESS("response in progress") {

        @Override
        void assignUnits(Emergency emergency, List<Unit> units) throws InvalidEmergencyException {
            emergency.getUnitsNeeded().assignUnitsToEmergency(units);
        }

        @Override
        void finishUnit(Emergency emergency, Unit unit) {
            emergency.getUnitsNeeded().unitFinishedJob(unit);
            if (emergency.getUnitsNeeded().canFinish()) {
                try {
                    emergency.setStatus(COMPLETED);
                } catch (InvalidEmergencyStatusException ex) {
                    //We assume this can't happen
                    Logger.getLogger(EmergencyStatus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        void withdrawUnit(Emergency emergency, Unit unit) {
            emergency.getUnitsNeeded().removeFromWorkingUnits(unit);
        }

        @Override
        boolean canAssignUnits(Emergency emergency, List<Unit> units) {
            return emergency.getUnitsNeeded().canAssignUnitsToEmergency(units);
        }

        @Override
        Collection<Unit> getPolicyProposal(Emergency emergency, List<? extends Unit> availableUnits) {
            return emergency.getUnitsNeeded().getPolicyProposal(availableUnits);
        }

        @Override
        boolean canBeResolved (Emergency emergency, Collection<Unit> availableUnits) {
            return emergency.getUnitsNeeded().CanBeResolved(availableUnits);
        }
    },
    /**
     * A state of an emergency where the emergency has been completly handled. All the units needed for this emergency have finished.
     */
    COMPLETED("completed") {

        @Override
        void assignUnits(Emergency emergency, List<Unit> units) throws InvalidEmergencyStatusException {
            throw new InvalidEmergencyStatusException("Unable to assign units to a completed emergency.");
        }

        @Override
        void finishUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException {
            throw new InvalidEmergencyStatusException("Unable to finish units from a completed emergency.");
        }

        @Override
        void withdrawUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException {
            throw new InvalidEmergencyStatusException("Unable to withdraw units from a competed emergency.");
        }

        @Override
        boolean canAssignUnits(Emergency emergency, List<Unit> units) {
            return false;
        }

        @Override
        Collection<Unit> getPolicyProposal(Emergency emergency, List<? extends Unit> availableUnits) {
            return new ArrayList<Unit>();//a proposal containing no units
        }

        @Override
        boolean canBeResolved (Emergency emergency, Collection<Unit> availableUnits) {
            return true;
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
	 *		| new.toString().equals(textual)
	 */
	private EmergencyStatus(String textual) {
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
		return this.toString().equals(textualRepresentation.toLowerCase());
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
	 * @param emergency
	 *      The emergency where the action takes place.
	 * @param units
	 *      The units to allocate to the emergency.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of the emergency is invalid.
	 * @throws Exception
	 *      If another exception is thrown.
	 * @note This method has a package visibility: Only the emergency class can call this method.
	 */
	abstract void assignUnits(Emergency emergency, List<Unit> units) throws InvalidEmergencyStatusException, Exception;

	/**
	 * A method representing a transition where a unit signals it has finished it's job.
	 * @param emergency
	 *      The emergency where the action takes place.
	 * @param unit
	 *      The unit that signals it has finished its job.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of the emergency is invalid.
	 * @throws Exception
	 *      If another exception is thrown.
	 * @note This method has a package visibility: Only the emergency class can call this method.
	 */
	abstract void finishUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException, Exception;

	/**
	 * A method that handles a situation where a given unit withdraws from a given emergency.
	 * @param emergency
	 *      The emergency the unit is withdrawing from.
	 * @param unit
	 *      The unit that withdraws from an emergency.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of the emergency is invalid.
	 * @throws Exception
	 *      If another exception is thrown.
	 * @note This method has a package visibility: Only the emergency class can call this method.
	 */
	abstract void withdrawUnit(Emergency emergency, Unit unit) throws InvalidEmergencyStatusException, Exception;

	/**
	 * A method that checks if the given units can be assigned to the given emergency.
	 * @param emergency
	 *      The emergency to check allocation from.
	 * @param units
	 *      A list of units to check for.
	 * @return True if the given list of units can be assigned, otherwise false (this also includes states where no allocation can be done).
	 */
	abstract boolean canAssignUnits(Emergency emergency, List<Unit> units);

	/**
	 * Gets a proposal generated by the policy of this emergency.
	 * @param emergency
	 *      The emergency to generate a proposal from.
	 * @param availableUnits
	 *      A list of available units that can be selected.
	 * @return A list of units that represents the proposal of the policy.
	 */
	abstract Collection<Unit> getPolicyProposal(Emergency emergency, List<? extends Unit> availableUnits);

    /**
     * Checks if the given emergency can be resolved with a given collection of all the available units.
     * @param availableUnits A collection of all the available units.
     * @return True if the given emergency can be resolved, otherwise false.
     */
    abstract boolean canBeResolved (Emergency emergency, Collection<Unit> availableUnits);
}
