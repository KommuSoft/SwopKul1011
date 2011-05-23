package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableException;
import projectswop20102011.exceptions.InvalidSendableStatusException;

/**
 * A class that records which units are working on a sendable and does
 *		the accounting for the units that are working on the sendable.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
abstract class UnitsNeeded {

    /**
     * The sendable of this UnitsNeeded.
     */
    private final Sendable sendable;
    
    /**
     * Generates a proposal for unit allocation based on the policy of the sendable.
     * @param availableUnits
     *		A list of available units.
     * @return A list of units proposed by the policy of this Sendable.
     */
    public abstract Set<Unit> getPolicyProposal(Set<Unit> availableUnits);
    
    protected UnitsNeeded (Sendable sendable) throws InvalidSendableException {
        if(!isValidSendable(sendable)) {
            throw new InvalidSendableException("Sendable must be effective.");
        }
        this.sendable = sendable;
    }

    /**
     * A method that indicates if the Sendable can be resolved with the given set of available units.
     * @param availableUnits
	 *		A set of units that are available.
     * @return True if the Sendable can be resolved by the given set of the available units.
     */
    public abstract boolean canBeResolved(Set<Unit> availableUnits);

    /**
     * A method called when a unit finishes his job to manage the sendable.
     * @param unit
     *		The unit that finishes his job.
	 * @param eventHandler
	 *		The event handler where the notifications should be sent to
	 * @effect
	 *		The unit finishes its job.
	 *
     */
    abstract void unitFinishedJob(Unit unit, EventHandler eventHandler);

    /**
     * Generates a proposal based on a list of available units to allocate to the sendable.
     * @param options
     *		A list of units where the proposal must be created from.
     * @return A subset of the given list containing units proposed for allocation.
     * @note The first items in the list will first be added to the proposal (This is usefull for Policies that sort the list of units before they generate a proposal).
     */
    abstract Set<Unit> generateProposal(SortedSet<Unit> options);

    /**
     * Returns a list of units who have finished working on this sendable.
     * @return a list of units who have finished working on this sendable.
     * @note The difference between takeFinishedUnits and getFinishedUnits is
     *		that getFinishedUnits first clones the list, takeFinishedUnits is
     *		only visible at private level and returns the real list.
     * @see #takeFinishedUnits()
     */
    public final ArrayList<Unit> getFinishedUnits() {
        return (ArrayList<Unit>) this.takeFinishedUnits().clone();
    }

    /**
     * Returns a list of units working on the sendable.
     * @return A list of the units working on the sendable.
     * @note The difference between takeWorkingUnits and getWorkingUnits is that
     *		getWorkingUnits first clones the list, takeWorkingUnits is only
     *		visible at private level and returns the real list.
     * @see #takeWorkingUnits()
     */
    public final ArrayList<Unit> getWorkingUnits() {
        return (ArrayList<Unit>) this.takeWorkingUnits().clone();
    }

    /**
     * A method that checks if the given set of units can be assigned to the sendable.
     * @param units
	 *		The set of units to assign.
     * @return True if the given set of units can be assigned, otherwise false.
     */
    public abstract boolean canAssignUnitsToEmergency(Set<Unit> units);

    /**
     * Assigns units to the sendable.
     * @param units 
	 *		The set of units to assign to the sendable.
	 * @param eventHandler
	 *		The event handler where the notifications should be sent to
     * @throws InvalidEmergencyException If the given units can be assigned because of the constraints of the sendable.
     */
    abstract void assignUnitsToSendable(Set<Unit> units, EventHandler eventHandler) throws InvalidEmergencyException;

    /**
     * Sets the status of the sendable.
     * @param sendableStatus
	 *		The new status of the sendable/
     * @throws InvalidSendableStatusException
	 *		If the given status is invalid.
     */
    abstract void setStatus(SendableStatus sendableStatus) throws InvalidSendableStatusException;

    /**
     * Checks if the units needed for the sendable are all finished.
     * @return True if all units needed for the sendable are finished; otherwise false.
     * @note If the units needed for the sendable are all finished, then the sendable of this units needed can be completed.
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
     * Withdraw a unit from its sendable.
     * @param unit
     *		The unit that wants to withdraw.
	 * @param eventHandler
	 *		The event handler where the notifications should be sent to
     * @effect The unit is removed from the workingUnits list.
     */
    abstract void withdrawUnit(Unit unit, EventHandler eventHandler);

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
    
    /**
     * Checks if the given Sendable is a valid sendable for a UnitsNeeded.
     * @param sendable
     *          The sendable to check.
     * @return True if the given Sendable is effective, otherwise false.
     */
    public static boolean isValidSendable (Sendable sendable) {
        return (sendable != null);
    }
    
    /**
     * Returns the Sendable of this UnitsNeeded object.
     * @return The Sendable of this UnitsNeeded object.
     */
    protected Sendable getSendable () {
        return this.sendable;
    }

	/**
	 * Checks if the given unit is still required for its emergency.
	 * @param u
	 *		The unit to check.
	 * @return true if the given unit is required for its emergency.
	 */
	protected boolean isRequired(Unit u){
		return false;
	}
    
}
