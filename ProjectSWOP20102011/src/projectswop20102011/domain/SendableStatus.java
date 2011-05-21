package projectswop20102011.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableStatusException;

/**
 * An enumeration that represents the status of a sendable.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public enum SendableStatus {

	/**
	 * The state the sendable has after it is created, but where the dispatcher hasn't already sent units to it.
	 */
	RECORDED_BUT_UNHANDLED("recorded but unhandled") {

		@Override
		protected void afterUnitsAssignment(UnitsNeeded unitsNeeded, Set<Unit> units) {
			try {
				if(!units.isEmpty()){
					unitsNeeded.setStatus(SendableStatus.RESPONSE_IN_PROGRESS);
				}
			} catch (InvalidSendableStatusException ex) {
				//We assume this can't happen.
				Logger.getLogger(SendableStatus.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		@Override
		void finishUnit(UnitsNeeded unitsNeeded, Unit unit, EventHandler eventHandler) throws InvalidSendableStatusException {
			throw new InvalidSendableStatusException("Can't finish units from an unhandled emergency.");
		}

		@Override
		void withdrawUnit(UnitsNeeded unitsNeeded, Unit unit, EventHandler eventHandler) throws InvalidSendableStatusException {
			throw new InvalidSendableStatusException("Can't withdraw units from an unhandled emergency.");
		}

		@Override
		Set<Unit> getPolicyProposal(ConcreteUnitsNeeded unitsNeeded, Set<Unit> availableUnits) {
			return unitsNeeded.getPolicyProposal(availableUnits);
		}

		@Override
		boolean canBeResolved(UnitsNeeded unitsNeeded, Set<Unit> availableUnits) {
			return unitsNeeded.canBeResolved(availableUnits);
		}

		@Override
		protected SendableStatus combineWithLower(SendableStatus otherStatus) {
			return null;//This method can't be called because RECORDED_BUT UNHANDLED has the lowest ordinal.
		}
	},
	/**
	 * A state of a sendable where the dispatcher has already sent units (this does not mean there are still units working or already finished).
	 */
	RESPONSE_IN_PROGRESS("response in progress") {

		@Override
		void finishUnit(UnitsNeeded unitsNeeded, Unit unit, EventHandler eventHandler) {
			Emergency e = unit.getEmergency();
			unitsNeeded.unitFinishedJob(unit, eventHandler);
			if (unitsNeeded.canCompleteEmergency()) {
				try {
					e.setStatus(COMPLETED);
					//unitsNeeded.setStatus(COMPLETED);
				} catch (InvalidSendableStatusException ex) {
					//We assume this can't happen
					Logger.getLogger(SendableStatus.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

		}

		@Override
		void withdrawUnit(UnitsNeeded unitsNeeded, Unit unit, EventHandler eventHandler) {
			unitsNeeded.withdrawUnit(unit, eventHandler);
		}

		@Override
		Set<Unit> getPolicyProposal(ConcreteUnitsNeeded unitsNeeded, Set<Unit> availableUnits) {
			return unitsNeeded.getPolicyProposal(availableUnits);
		}

		@Override
		boolean canBeResolved(UnitsNeeded unitsNeeded, Set<Unit> availableUnits) {
			return unitsNeeded.canBeResolved(availableUnits);
		}

		@Override
		protected SendableStatus combineWithLower(SendableStatus otherStatus) {
			switch (otherStatus) {
				case RECORDED_BUT_UNHANDLED:
					return RESPONSE_IN_PROGRESS;
				default:
					return null;
			}
		}
	},
	/**
	 * A state of a sendable where the sendable has been completly handled. All the units needed for this sendable have finished.
	 */
	COMPLETED("completed") {

		@Override
		void finishUnit(UnitsNeeded unitsNeeded, Unit unit, EventHandler eventHandler) throws InvalidSendableStatusException {
			throw new InvalidSendableStatusException("Unable to finish units from a completed emergency.");
		}

		@Override
		void withdrawUnit(UnitsNeeded unitsNeeded, Unit unit, EventHandler eventHandler) throws InvalidSendableStatusException {
			throw new InvalidSendableStatusException("Unable to withdraw units from a competed emergency.");
		}

		@Override
		protected boolean canAssignUnitsFromState() {
			return false;
		}

		@Override
		Set<Unit> getPolicyProposal(ConcreteUnitsNeeded unitsNeeded, Set<Unit> availableUnits) {
			return new HashSet<Unit>();//a proposal containing no units
		}

		@Override
		boolean canBeResolved(UnitsNeeded unitsNeeded, Set<Unit> availableUnits) {
			return true;
		}

		@Override
		protected SendableStatus combineWithLower(SendableStatus otherStatus) {
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
	 * The textual representation of a SendableStatus.
	 */
	private final String textual;

	/**
	 * Creates a new instance of the SendableStatus class with a given textual representation.
	 * @param textual
	 *		The textual representation of the SendableStatus, used for parsing and user interaction.
	 * @post The textual representation is set to the given textual representation.
	 *		| new.toString().equals(textual)
	 */
	private SendableStatus(String textual) {
		this.textual = textual;
	}

	/**
	 * Returns the textual representation of the SendableStatus.
	 * @return A textual representation of the SendableStatus.
	 */
	@Override
	public String toString() {
		return textual;
	}

	/**
	 * Tests if a given textual representation of a SendableStatus matches the textual representation of this SendableStatus.
	 * @param textualRepresentation
	 *		The textual representation to test.
	 * @return True if the textual representation matches, otherwise false.
	 */
	public boolean matches(String textualRepresentation) {
		return this.toString().equals(textualRepresentation.toLowerCase());
	}

	/**
	 * Parses a textual representation into its SendableStatus equivalent.
	 * @param textualRepresentation
	 *		The textual representation to parse.
	 * @return A SendableStatus that is the equivalent of the textual representation.
	 * @throws InvalidSendableStatusException
	 *		If no SendableStatus matches the textual representation.
	 */
	public static SendableStatus parse(String textualRepresentation) throws InvalidSendableStatusException {
		for (SendableStatus es : SendableStatus.values()) {
			if (es.matches(textualRepresentation)) {
				return es;
			}
		}
		throw new InvalidSendableStatusException(String.format("Unknown sendable status level \"%s\".", textualRepresentation));
	}

	/**
	 * A method representing a potential transition where units are allocated to the sendable.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the sendable where the action takes place.
	 * @param units
	 *      The units to allocate to the sendable.
	 * @param eventHandler
	 *		The event handler where the notifications should be sent to
	 * @throws InvalidSendableStatusException
	 *      If the status of the sendable is invalid.
	 * @throws  InvalidEmergencyException
	 *		If the sendable is invalid.
	 * @note This method has a package visibility: Only the sendable class can call this method.
	 */
	void assignUnits(UnitsNeeded unitsNeeded, Set<Unit> units, EventHandler eventHandler) throws InvalidSendableStatusException, InvalidEmergencyException {
		if (!canAssignUnitsFromState()) {
			throw new InvalidSendableStatusException("Unable to assign units to Emergency. Emergency is in the wrong state.");
		}
		unitsNeeded.assignUnitsToEmergency(units, eventHandler);
		afterUnitsAssignment(unitsNeeded, units);
	}

	/**
	 * A method representing a transition where a unit signals it has finished its job.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the sendable where the action takes place.
	 * @param unit
	 *      The unit that signals it has finished its job.
	 * @param eventHandler
	 *		The event handler where the notifications should be sent to
	 * @throws InvalidSendableStatusException
	 *      If the status of the sendable is invalid.
	 * @note This method has a package visibility: Only the sendable class can call this method.
	 */
	abstract void finishUnit(UnitsNeeded unitsNeeded, Unit unit, EventHandler eventHandler) throws InvalidSendableStatusException;

	/**
	 * A method that handles a situation where a given unit withdraws from a given sendable.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the sendable where the action takes place.
	 * @param unit
	 *      The unit that withdraws from a sendable.
	 * @param eventHandler
	 *		The event handler where the notifications should be sent to
	 * @throws InvalidSendableStatusException
	 *      If the status of the sendable is invalid.
	 * @note This method has a package visibility: Only the sendable class can call this method.
	 */
	abstract void withdrawUnit(UnitsNeeded unitsNeeded, Unit unit, EventHandler eventHandler) throws InvalidSendableStatusException;

	/**
	 * A method that checks if the given units can be assigned to the given sendable.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the sendable where the action takes place.
	 * @param units
	 *      A list of units to check for.
	 * @return True if the given list of units can be assigned, otherwise false (this also includes states where no allocation can be done).
	 */
	boolean canAssignUnits(UnitsNeeded unitsNeeded, Set<Unit> units) {
		return (this.canAssignUnitsFromState() && unitsNeeded.canAssignUnitsToEmergency(units));
	}

	/**
	 * Gets a proposal generated by the policy of this sendable.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the sendable where the action takes place.
	 * @param availableUnits
	 *      A list of available units that can be selected.
	 * @return A set of units that represents the proposal of the policy.
	 */
	abstract Set<Unit> getPolicyProposal(ConcreteUnitsNeeded unitsNeeded, Set<Unit> availableUnits);

	/**
	 * Checks if the given sendable can be resolved with a given collection of all the available units.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the sendable where the action takes place.
	 * @param availableUnits
	 *		A collection of all the available units.
	 * @return True if the given sendable can be resolved, otherwise false.
	 */
	abstract boolean canBeResolved(UnitsNeeded unitsNeeded, Set<Unit> availableUnits);

	/**
	 * Checks if units can be assgined with the current state.
	 * @return True if the SendableStatus allows assignment of units, otherwise false.
	 * @note Returning true does not guarantee any combination of units can be assigned.
	 */
	protected boolean canAssignUnitsFromState() {
		return true;
	}

	/**
	 * A virtual method that needs to be overridden by an SendableStatus where something has to be done after Units are assigned to the Sendable.
	 * @param unitsNeeded
	 *		The ConcreteUnitsNeeded object of the Sendable.
	 * @param units
	 *		The Set of assigned Units.
	 */
	protected void afterUnitsAssignment(UnitsNeeded unitsNeeded, Set<Unit> units) {
	}

	/**
	 * Combining two SendableStatuses (this and otherStatus) intro a new SendableStatus. The transmission-table of the method is defined by the following transmission table:
	 * <table>
	 *  <tr><td>Combine</td><td>RBU</td><td>RIP</td><td>CPT</td></tr>
	 *  <tr><td>RBU</td><td>RBU</td><td>RIP</td><td>RIP</td></tr>
	 *  <tr><td>RIP</td><td>RIP</td><td>RIP</td><td>RIP</td></tr>
	 *  <tr><td>CPT</td><td>RIP</td><td>RIP</td><td>CPT</td></tr>
	 * </table>
	 * @param otherStatus
	 *		The other sendablestatus to combine with.
	 * @return The combination of the two SendableStatuses.
	 * @note This method is used to calculate the derived status of the disaster from it's sendable.
	 * @note This method is commutative, this is forced: this.combine(otherStatus) == otherStatus.combine(this).
	 * @note Another property is that a status combined with the same status always results in that status: this.combine(this) == this.
	 * @note This method uses the combineWithLower method.
	 * @see Disaster#getStatus()
	 * @see #combineWithLower(projectswop20102011.domain.SendableStatus)
	 */
	public SendableStatus combine(SendableStatus otherStatus) {
		if (this == otherStatus) {
			return this;
		} else if (this.ordinal() < otherStatus.ordinal()) {
			return otherStatus.combine(this);
		} else {
			return this.combineWithLower(otherStatus);
		}
	}

	/**
	 * Combines this SendableStatus with a status that has a lower ordinal.
	 * @param otherStatus The other status to combine with.
	 * @return The combination of the two SendableStatuses.
	 */
	protected abstract SendableStatus combineWithLower(SendableStatus otherStatus);
}
