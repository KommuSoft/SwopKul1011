package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidDispatchUnitsConstraintException;
import projectswop20102011.exceptions.InvalidMapItemException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class that records which units are working on an emergency and does
 *		the accounting which units are working on the emergency.
 * @invar The emergency is valid.
 *		| isValidEmergency(getEmergency())
 * @invar The constraint is valid.
 *		| isValidConstraint(getConstraint())
 * @note This class has a package visibility and is a pure fabrication object.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
class UnitsNeeded {

	/**
	 * A policy for allocating units to this UnitsNeeded.
	 */
	private DispatchPolicy policy;
	/**
	 * The emergency that is been handled by this UnitsNeeded.
	 */
	private final Emergency emergency;
	/**
	 * A DispatchUnitsConstraint object specifying which units can be allocated to the emergency.
	 */
	private final DispatchUnitsConstraint constraint;
	/**
	 * A variable registering the units that are currently working at the emergency.
	 */
	private ArrayList<Unit> workingUnits;
	/**
	 * A variable registering the units of this emergency that have finished their job
	 */
	private ArrayList<Unit> finishedUnits;

	/**
	 * Creates a new object that calculates the units needed for an emergency.
	 * @param emergency
	 *		The emergency that will be handled by this UnitsNeeded.
	 * @param constraint
	 *		A constraint used to determine when units can be assigned to the emergency.
	 * @post This emergency is set to the given emergency.
	 *		| new.getEmergency() == emergency
	 * @post This constraint is set to the given constraint.
	 *		| new.getConstraint() == constraint
	 * @effect Initialize the Units.
	 *		| initUnits()
	 * @throws InvalidEmergencyException
	 *		If the given emergency is not effective.
	 * @throws InvalidDispatchUnitsConstraintException
	 *          If the given constraint is invalid.
	 * @note This constructor has a package visibility, only instances in the domain layer (Emergencies) can create UnitsNeeded.
	 */
	UnitsNeeded(Emergency emergency, DispatchUnitsConstraint constraint) throws InvalidEmergencyException, InvalidDispatchUnitsConstraintException {
		if (!isValidEmergency(emergency)) {
			throw new InvalidEmergencyException("Emergency must be effective.");
		}
		if (!isValidConstraint(constraint)) {
			throw new InvalidDispatchUnitsConstraintException("The constraint must be effective.");
		}
		this.emergency = emergency;
		this.constraint = constraint;
		try {
			this.setPolicy(new DefaultDispatchPolicy(this));
		} catch (InvalidUnitsNeededException ex) {
			//We assume this can't happen
			Logger.getLogger(UnitsNeeded.class.getName()).log(Level.SEVERE, null, ex);
		}
		initUnits();
	}

	/**
	 * Sets the working units of the emergency to zero.
	 * @post This workingUnits is equal to zero.
	 *		| new.getWorkingUnits().size() == 0
	 * @post This finishedUnits is equal to zero.
	 *		| new.getFinishedUnits().size() == 0
	 */
	private void initUnits() {
		this.workingUnits = new ArrayList<Unit>(0);
		this.finishedUnits = new ArrayList<Unit>(0);
	}

	/**
	 * Checks if the units needed for the emergency are all finished.
	 * @return True if all units needed for the emergency are finished; otherwise false.
	 */
	public boolean canFinish() {
		return getConstraint().areValidDispatchUnits(takeFinishedUnits());
	}

	/**
	 * Add a given unit to the working units.
	 * @param unit
	 *      The unit that must be added to the working units.
	 * @effect The given unit is added to the working units.
	 *		| takeWorkingUnits().add(unit)
	 */
	private void addWorkingUnits(Unit unit) {
		takeWorkingUnits().add(unit);
	}

	/**
	 * Add a given unit to the finished units.
	 * @param unit
	 *      The unit that must be added to the finished units.
	 * @effect The given unit is added to the finished units.
	 */
	private void addFinishedUnits(Unit unit) {
		takeFinishedUnits().add(unit);
	}

	/**
	 * Returns the emergency handled by this UnitsNeeded.
	 * @return The emergency handled by this UnitsNeeded.
	 */
	public Emergency getEmergency() {
		return this.emergency;
	}

	/**
	 * Returns a list of units working on the emergency.
	 * @return A list of the units working on the emergency.
	 * @note The difference between takeWorkingUnits and getWorkingUnits is that
	 *		getWorkingUnits first clones the list, takeWorkingUnits is only
	 *		visible at private level and returns the real list.
	 * @see #takeWorkingUnits()
	 */
	public ArrayList<Unit> getWorkingUnits() {
		return (ArrayList<Unit>) workingUnits.clone();
	}

	/**
	 * Returns the working units of the emergency.
	 * @return The working units of the emergency.
	 * @note The difference between takeWorkingUnits and getWorkingUnits is that
	 *		getWorkingUnits first clones the list, takeWorkingUnits is only
	 *		visible at private level and returns the real list.
	 * @see #getWorkingUnits()
	 */
	private ArrayList<Unit> takeWorkingUnits() {
		return workingUnits;
	}

	/**
	 * Returns the finished units of this emergency.
	 * @return The finished units of this emergency.
	 * @note The difference between takeFinishedUnits and getFinishedUnits is
	 *		that getFinishedUnits first clones the list, takeFinishedUnits is
	 *		only visible at private level and returns the real list.
	 * @see #getFinishedUnits()
	 */
	private ArrayList<Unit> takeFinishedUnits() {
		return finishedUnits;
	}

	/**
	 * Returns a list of units who have finished working on this emergency.
	 * @return a list of units who have finished working on this emergency.
	 * @note The difference between takeFinishedUnits and getFinishedUnits is
	 *		that getFinishedUnits first clones the list, takeFinishedUnits is
	 *		only visible at private level and returns the real list.
	 * @see #takeFinishedUnits()
	 */
	public ArrayList<Unit> getFinishedUnits() {
		return (ArrayList<Unit>) this.finishedUnits.clone();
	}

	/**
	 * Returns the constraint of this UnitsNeeded.
	 * @return The constraint used by this UnitsNeeded.
	 */
	DispatchUnitsConstraint getConstraint() {
		return constraint;
	}

	/**
	 * Checks if the given Emergency is valid for this UnitsNeeded class, i.e
	 *		the emergency is not null.
	 * @param emergency
	 *		The emergency to test.
	 * @return True if the given emergency is valid; otherwise false.
	 */
	public static boolean isValidEmergency(Emergency emergency) {
		return (emergency != null);
	}

	/**
	 * Checks if the given units can be assigned to the emergency.
	 * @param units
	 *		The units to be assigned.
	 * @return True if all the given units are effective, unique and
	 *          can be assigned and the constraint for allocation is passed;
	 *          otherwise false.
	 */
	public boolean canAssignUnitsToEmergency(List<Unit> units) {
		//TODO: remove duplicates and null pointers out of <units>
		for (Unit u : units) {
			if (!u.canBeAssigned()) {
				return false;
			}
		}
		ArrayList<Unit> totalUnits = (ArrayList<Unit>) getWorkingUnits();
		totalUnits.addAll(units);
		return getConstraint().areAllUnitsRelevant(units);
	}

	/**
	 * Assign the given list of units to the emergency.
	 * @param units
	 *		The given list of units.
	 * @post All the units in the given list are assigned
	 *		| forall (u in units)
	 *		|	u.isAssigned()
	 * @post All the units in the given list are handling the emergency of this UnitNeeded
	 *		| forall (u in units)
	 *		|	u.getEmergency().equals(this.getEmergency())
	 * @throws InvalidEmergencyException
	 *		If the units can't be assigned to the emergency (when canAssignUnitsToEmergency fails)
	 * @see #canAssignUnitsToEmergency(List)
	 */
	public synchronized void assignUnitsToEmergency(List<Unit> units) throws InvalidEmergencyException {
		if (!canAssignUnitsToEmergency(units)) {
			throw new InvalidEmergencyException("Units can't be assigned to the emergency, harm to assignment constraints.");
		}
		for (Unit u : units) {
			try {
				u.assignTo(this.getEmergency());
			} catch (InvalidMapItemException ex) {
				//We assume this can't be true (checked by the canAssigUnitsToEmergency method)
				Logger.getLogger(UnitsNeeded.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		for (int i = 0; i < units.size(); i++) {
			addWorkingUnits(units.get(i));
		}
	}

	/**
	 * A method called when a unit finishes his job to manage the emergency.
	 * @param unit
	 *		The unit that finishes his job.
	 * @effect The unit is removed from the workingUnits list.
	 *		|takeWorkingUnit().remove(unit)
	 */
	void unitFinishedJob(Unit unit){
		removeFromWorkingUnits(unit);
		addFinishedUnits(unit);
	}

	/**
	 * Returns a DispatchPolicy object that can give a proposal for unit allocations.
	 * @return A DispatchPolicy object that can give a proposal for unit allocations.
	 */
	public DispatchPolicy getPolicy() {
		return this.policy;
	}

	/**
	 * Sets the policy of this UnitsNeeded object to the given object.
	 * @param policy
	 *		The given policy to set.
	 * @post The given policy is equal to the policy of this UnitsNeeded
	 *		| new.getPolicy() == policy
	 */
	void setPolicy(DispatchPolicy policy) {
		if (policy == null) {
			try {
				this.policy = new DefaultDispatchPolicy(this);
			} catch (InvalidUnitsNeededException ex) {
				//We assume this can't happen
				Logger.getLogger(UnitsNeeded.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			this.policy = policy;
		}
	}

	/**
	 * Pushes the given policy on the current policy, and sets this as the current policy.
	 * @param policy The policy to push on the current policy.
	 * @throws InvalidDispatchPolicyException If the given policy does not handle this UnitsNeeded.
	 */
	void pushPolicy(DispatchPolicy policy) throws InvalidDispatchPolicyException {
		policy.getDeepSuccessor().setSuccessor(this.getPolicy());
		this.setPolicy(policy);
	}

	/**
	 * Tests if the given DispatchUnitConstraint can be a valid constraint, i.e.
	 *		the constraint is not null.
	 * @param constraint
	 *		The constraint to check.
	 * @return True if the given constraint is effective; otherwise false.
	 */
	public static boolean isValidConstraint(DispatchUnitsConstraint constraint) {
		return (constraint != null);
	}

	/**
	 * Handles operations needed when a unit withdraws from it's emergency.
	 * @param unit
	 *		The unit that want's to withdraw.
	 * @post The unit is removed from the workingUnits list.
	 *		|takeWorkingUnit().remove(unit)
	 */
	void removeFromWorkingUnits(Unit unit) {
		takeWorkingUnits().remove(unit);
		unit.setEmergency(null);
	}

	/**
	 * Generates a proposal based on a list of available units to allocate to the emergency.
	 * @param options
	 *		A list of units that are available for this allocation.
	 * @return A subset of the given list containing units proposed for allocation.
	 * @note The first items in the list will first be added to the proposal (This is usefull for Policies that sort the list of units before they generate a proposal).
	 */
	Collection<Unit> generateProposal(List<Unit> options) {
		List<Unit> fixedPart = this.getWorkingUnits();
		fixedPart.addAll(this.takeWorkingUnits());
		return this.getConstraint().generateProposal(fixedPart, options);
	}

	/**
	 * Generates a proposal for unit allocation based on the policy of the emergency.
	 * @return A list of units proposed by the policy of this Emergency.
	 */
	public Collection<Unit> getPolicyProposal(List<? extends Unit> availableUnits) {
		return this.getPolicy().generateProposal(availableUnits);
	}

	public boolean CanBeResolved(Collection<? extends Unit> availableUnits) {
		Collection<Unit> completeCollection = this.getFinishedUnits();
		completeCollection.addAll(this.takeFinishedUnits());
		completeCollection.addAll(availableUnits);
		return this.getConstraint().areValidDispatchUnits(completeCollection);
	}
}
