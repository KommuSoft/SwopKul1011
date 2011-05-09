package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;

public abstract class UnitsNeeded {


	/**
	 * Generates a proposal for unit allocation based on the policy of the emergency.
	 * @param availableUnits
	 *		A list of available units.
	 * @return A list of units proposed by the policy of this Emergency.
	 */
	public abstract Set<Unit> getPolicyProposal(List<? extends Unit> availableUnits);

	public abstract boolean canBeResolved(Collection<? extends Unit> availableUnits);

	/**
	 * A method called when a unit finishes his job to manage the emergency.
	 * @param unit
	 *		The unit that finishes his job.
	 * @effect The unit is removed from the workingUnits list.
	 *		|takeWorkingUnit().remove(unit)
	 */
	abstract void unitFinishedJob(Unit unit);

	/**
	 * Generates a proposal based on a list of available units to allocate to the emergency.
	 * @param options
	 *		A list of units where the proposal must be created from.
	 * @return A subset of the given list containing units proposed for allocation.
	 * @note The first items in the list will first be added to the proposal (This is usefull for Policies that sort the list of units before they generate a proposal).
	 */
	abstract Set<Unit> generateProposal(List<Unit> options);

	/**
	 * Returns a list of units who have finished working on this emergency.
	 * @return a list of units who have finished working on this emergency.
	 * @note The difference between takeFinishedUnits and getFinishedUnits is
	 *		that getFinishedUnits first clones the list, takeFinishedUnits is
	 *		only visible at private level and returns the real list.
	 * @see #takeFinishedUnits()
	 */
	public abstract ArrayList<Unit> getFinishedUnits();


	/**
	 * Returns a list of units working on the emergency.
	 * @return A list of the units working on the emergency.
	 * @note The difference between takeWorkingUnits and getWorkingUnits is that
	 *		getWorkingUnits first clones the list, takeWorkingUnits is only
	 *		visible at private level and returns the real list.
	 * @see #takeWorkingUnits()
	 */
	public abstract ArrayList<Unit> getWorkingUnits();

	public abstract boolean canAssignUnitsToEmergency(Set<Unit> units);

	abstract void assignUnitsToEmergency(Set<Unit> units) throws InvalidEmergencyException;

	abstract void setStatus(EmergencyStatus emergencyStatus) throws InvalidEmergencyStatusException;

	/**
	 * Checks if the units needed for the emergency are all finished.
	 * @return True if all units needed for the emergency are finished; otherwise false.
	 * @note If the units needed for the emergency are all finished, then the emergency of this units needed can be completed.
	 */
	public boolean canCompleteEmergency() {
		return (this.getWorkingUnits().isEmpty() && getConstraint().canFinish(this.getFinishedUnits()));
	}

	abstract DispatchUnitsConstraint getConstraint();

	/**
	 * Withdraw a unit from its emergency.
	 * @param unit
	 *		The unit that wants to withdraw.
	 * @effect The unit is removed from the workingUnits list.
	 */
	abstract void withdrawUnit(Unit unit);

	abstract ArrayList<Unit> takeFinishedUnits();
}
