package projectswop20102011.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

		@Override
		protected void afterUnitsAssignment(UnitsNeeded unitsNeeded, Set<Unit> units) {
			try {
				//TODO dit is hier een klein beetje aangepast om het gemakkelijker te maken, het origineel staat hier nog onder
				//unitsNeeded.getEmergency().setStatus(EmergencyStatus.RESPONSE_IN_PROGRESS);
				unitsNeeded.setStatus(EmergencyStatus.RESPONSE_IN_PROGRESS);
			} catch (InvalidEmergencyStatusException ex) {
				//We assume this can't happen.
				Logger.getLogger(EmergencyStatus.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		@Override
		void finishUnit(UnitsNeeded unitsNeeded, Unit unit) throws InvalidEmergencyStatusException {
			throw new InvalidEmergencyStatusException("Can't finish units from an unhandled emergency.");
		}

		@Override
		void withdrawUnit(UnitsNeeded unitsNeeded, Unit unit) throws InvalidEmergencyStatusException {
			throw new InvalidEmergencyStatusException("Can't withdraw units from an unhandled emergency.");
		}

		@Override
		Set<Unit> getPolicyProposal(ConcreteUnitsNeeded unitsNeeded, List<? extends Unit> availableUnits) {
			return unitsNeeded.getPolicyProposal(availableUnits);
		}

		@Override
		boolean canBeResolved(UnitsNeeded unitsNeeded, Collection<Unit> availableUnits) {
			return unitsNeeded.canBeResolved(availableUnits);
		}

		@Override
		protected EmergencyStatus combineWithLower(EmergencyStatus otherStatus) {
			return null;//This method can't be called because RECORDED_BUT UNHANDLED has the lowest ordinal.
		}
	},
	/**
	 * A state of an emergency where the dispatcher has already sent units (this does not mean there are still units working or already finished).
	 */
	RESPONSE_IN_PROGRESS("response in progress") {

		@Override
		void finishUnit(UnitsNeeded unitsNeeded, Unit unit) {
			unitsNeeded.unitFinishedJob(unit);
			if (unitsNeeded.canCompleteEmergency()) {
				try {
					System.out.println("\t\t" + unit);
					System.out.println("\t\t" + unit.getEmergency());
					(unit.getEmergency()).setStatus(COMPLETED);
					//unitsNeeded.setStatus(COMPLETED);
				} catch (InvalidEmergencyStatusException ex) {
					//We assume this can't happen
					Logger.getLogger(EmergencyStatus.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}

		@Override
		void withdrawUnit(UnitsNeeded unitsNeeded, Unit unit) {
			unitsNeeded.withdrawUnit(unit);
		}

		@Override
		Set<Unit> getPolicyProposal(ConcreteUnitsNeeded unitsNeeded, List<? extends Unit> availableUnits) {
			return unitsNeeded.getPolicyProposal(availableUnits);
		}

		@Override
		boolean canBeResolved(UnitsNeeded unitsNeeded, Collection<Unit> availableUnits) {
			return unitsNeeded.canBeResolved(availableUnits);
		}

		@Override
		protected EmergencyStatus combineWithLower(EmergencyStatus otherStatus) {
			switch (otherStatus) {
				case RECORDED_BUT_UNHANDLED:
					return RESPONSE_IN_PROGRESS;
				default:
					return null;
			}
		}
	},
	/**
	 * A state of an emergency where the emergency has been completly handled. All the units needed for this emergency have finished.
	 */
	COMPLETED("completed") {

		@Override
		void finishUnit(UnitsNeeded unitsNeeded, Unit unit) throws InvalidEmergencyStatusException {
			throw new InvalidEmergencyStatusException("Unable to finish units from a completed emergency.");
		}

		@Override
		void withdrawUnit(UnitsNeeded unitsNeeded, Unit unit) throws InvalidEmergencyStatusException {
			throw new InvalidEmergencyStatusException("Unable to withdraw units from a competed emergency.");
		}

		@Override
		protected boolean canAssignUnitsFromState() {
			return false;
		}

		@Override
		Set<Unit> getPolicyProposal(ConcreteUnitsNeeded unitsNeeded, List<? extends Unit> availableUnits) {
			return new HashSet<Unit>();//a proposal containing no units
		}

		@Override
		boolean canBeResolved(UnitsNeeded unitsNeeded, Collection<Unit> availableUnits) {
			return true;
		}

		@Override
		protected EmergencyStatus combineWithLower(EmergencyStatus otherStatus) {
			switch (otherStatus) {
				case RECORDED_BUT_UNHANDLED:
					return RESPONSE_IN_PROGRESS;
				case RESPONSE_IN_PROGRESS:
					return RESPONSE_IN_PROGRESS;
				default:
					return null;
			}
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
	 * @param unitsNeeded
	 *      The unitsNeeded object of the emergency where the action takes place.
	 * @param units
	 *      The units to allocate to the emergency.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of the emergency is invalid.
	 * @throws  InvalidEmergencyException
	 *		If the emergency is invalid.
	 * @note This method has a package visibility: Only the emergency class can call this method.
	 */
	void assignUnits(UnitsNeeded unitsNeeded, Set<Unit> units) throws InvalidEmergencyStatusException, InvalidEmergencyException {
		if (!canAssignUnitsFromState()) {
			throw new InvalidEmergencyStatusException("Unable to assign units to Emergency. Emergency is in the wrong state.");
		}
		unitsNeeded.assignUnitsToEmergency(units);
		afterUnitsAssignment(unitsNeeded, units);
	}

	/**
	 * A method representing a transition where a unit signals it has finished it's job.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the emergency where the action takes place.
	 * @param unit
	 *      The unit that signals it has finished its job.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of the emergency is invalid.
	 * @note This method has a package visibility: Only the emergency class can call this method.
	 */
	abstract void finishUnit(UnitsNeeded unitsNeeded, Unit unit) throws InvalidEmergencyStatusException;

	/**
	 * A method that handles a situation where a given unit withdraws from a given emergency.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the emergency where the action takes place.
	 * @param unit
	 *      The unit that withdraws from an emergency.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of the emergency is invalid.
	 * @note This method has a package visibility: Only the emergency class can call this method.
	 */
	abstract void withdrawUnit(UnitsNeeded unitsNeeded, Unit unit) throws InvalidEmergencyStatusException;

	/**
	 * A method that checks if the given units can be assigned to the given emergency.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the emergency where the action takes place.
	 * @param units
	 *      A list of units to check for.
	 * @return True if the given list of units can be assigned, otherwise false (this also includes states where no allocation can be done).
	 */
	boolean canAssignUnits(UnitsNeeded unitsNeeded, Set<Unit> units) {
		return (this.canAssignUnitsFromState() && unitsNeeded.canAssignUnitsToEmergency(units));
	}

	/**
	 * Gets a proposal generated by the policy of this emergency.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the emergency where the action takes place.
	 * @param availableUnits
	 *      A list of available units that can be selected.
	 * @return A set of units that represents the proposal of the policy.
	 */
	abstract Set<Unit> getPolicyProposal(ConcreteUnitsNeeded unitsNeeded, List<? extends Unit> availableUnits);

	/**
	 * Checks if the given emergency can be resolved with a given collection of all the available units.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the emergency where the action takes place.
	 * @param availableUnits
	 *		A collection of all the available units.
	 * @return True if the given emergency can be resolved, otherwise false.
	 */
	abstract boolean canBeResolved(UnitsNeeded unitsNeeded, Collection<Unit> availableUnits);

	/**
	 * Checks if units can be assgined with the current state.
	 * @return True if the EmergencyState allows assignment of units, otherwise false.
	 * @note Returning true does not guarantee any combination of units can be assigned.
	 */
	protected boolean canAssignUnitsFromState() {
		return true;
	}

	/**
	 * A virtual method that needs to be overridden by an EmergencyStatus where something has to be done after Units are assigned to the Emergency.
	 * @param unitsNeeded The ConcreteUnitsNeeded object of the Emergency.
	 * @param units The Set of assigned Units.
	 */
	protected void afterUnitsAssignment(UnitsNeeded unitsNeeded, Set<Unit> units) {
	}

	/**
	 * Combining two EmergencyStatuses (this and otherStatus) intro a new EmergencyStatus. The transmission-table of the method is defined by the following transmission table:
	 * <table>
	 *  <tr><td>Combine</td><td>RBU</td><td>RIP</td><td>CPT</td></tr>
	 *  <tr><td>RBU</td><td>RBU</td><td>RIP</td><td>RIP</td></tr>
	 *  <tr><td>RIP</td><td>RIP</td><td>RIP</td><td>RIP</td></tr>
	 *  <tr><td>CPT</td><td>RIP</td><td>RIP</td><td>CPT</td></tr>
	 * </table>
	 * @param otherStatus The other status to combine with.
	 * @return The combination of the two EmergencyStatuses.
	 * @note This method is used to calculate the derived status of the disaster from it's emergency.
	 * @note This method is commutitive, this is forced: this.combine(otherStatus) == otherStatus.combine(this).
	 * @note Another property is that a status combined with the same status always results in that status: this.combine(this) == this.
	 * @note This method uses the combineWithLower method.
	 * @see Disaster#getStatus()
	 * @see #combineWithLower(projectswop20102011.domain.EmergencyStatus)
	 */
	public EmergencyStatus combine(EmergencyStatus otherStatus) {
		if (this == otherStatus) {
			return this;
		} else if (this.ordinal() < otherStatus.ordinal()) {
			return otherStatus.combine(this);
		} else {
			return this.combineWithLower(otherStatus);
		}
	}

	/**
	 * Combines this EmergencyStatus with a status that has a lower ordinal.
	 * @param otherStatus The other status to combine with.
	 * @return The combination of the two EmergencyStatuses.
	 */
	protected abstract EmergencyStatus combineWithLower(EmergencyStatus otherStatus);
}
