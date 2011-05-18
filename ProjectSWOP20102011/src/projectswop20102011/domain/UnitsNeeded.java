package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableStatusException;

public abstract class UnitsNeeded {

    /**
     * Generates a proposal for unit allocation based on the policy of the emergency.
     * @param availableUnits
     *		A list of available units.
     * @return A list of units proposed by the policy of this Emergency.
     */
    public abstract Set<Unit> getPolicyProposal(Set<Unit> availableUnits);

    /**
     * A method that indicates if the Sendable can be resolved with the given set of available units.
     * @param availableUnits A set of units that are available.
     * @return True if the Sendable can be resolved by the given set of the available units.
     */
    public abstract boolean canBeResolved(Set<Unit> availableUnits);

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
    abstract Set<Unit> generateProposal(SortedSet<Unit> options);

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

    /**
     * A method that checks if the given set of units can be assigned to the sendable.
     * @param units The set of units to assign.
     * @return True if the given set of units can be assigned, otherwise false.
     */
    public abstract boolean canAssignUnitsToEmergency(Set<Unit> units);

    /**
     * Assigns units to the sendable.
     * @param units the set of units to assign to the sendable.
     * @throws InvalidEmergencyException If the given units can be assigned because of the constraints of the sendable.
     */
    abstract void assignUnitsToEmergency(Set<Unit> units) throws InvalidEmergencyException;

    /**
     * Sets the status of the sendable.
     * @param emergencyStatus The new status of the sendable/
     * @throws InvalidEmergencyStatusException If the given status is invalid.
     */
    abstract void setStatus(SendableStatus emergencyStatus) throws InvalidSendableStatusException;

    /**
     * Checks if the units needed for the emergency are all finished.
     * @return True if all units needed for the emergency are finished; otherwise false.
     * @note If the units needed for the emergency are all finished, then the emergency of this units needed can be completed.
     */
    public boolean canCompleteEmergency() {
        return (this.getWorkingUnits().isEmpty() && getConstraint().canFinish(this.getFinishedUnits()));
    }

    /**
     * Returns the constraints of the sendable.
     * @return The constraint of the sendable.
     */
    abstract DispatchUnitsConstraint getConstraint();

    /**
     * Withdraw a unit from its emergency.
     * @param unit
     *		The unit that wants to withdraw.
     * @effect The unit is removed from the workingUnits list.
     */
    abstract void withdrawUnit(Unit unit);

    /**
     * Gets the list of units that have been finished in the Sendable.
     * @return the list of units that have been finished in the Sendable.
     */
    abstract ArrayList<Unit> takeFinishedUnits();

    /**
     * Gets the list of units that are currently working on the sendable.
     * @return the list of units that are currently working on the sendable.
     */
    abstract ArrayList<Unit> takeWorkingUnits();

    /**
     * Generates a list containing all the units that are working or have already finished the sendable.
     * @return A list containing all the units that are working of have already finished the sendable.
     */
    public ArrayList<Unit> getAlreadyAssignedUnits() {
        ArrayList<Unit> units = new ArrayList<Unit>(this.takeFinishedUnits());
        units.addAll(this.takeWorkingUnits());
        return units;
    }
}
