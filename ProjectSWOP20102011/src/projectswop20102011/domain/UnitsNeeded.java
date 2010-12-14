package projectswop20102011.domain;

import java.util.ArrayList;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidDispatchUnitsConstraintException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidMapItemException;
import projectswop20102011.exceptions.InvalidEmergencyException;

/**
 * A class that represents the units needed for an Emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar The emergency is valid.
 *		| isValidEmergency(getEmergency())
 * @invar The unit type list and number of units list are valid.
 *		| areValidTypesAndNumbersOfUnits(getUnits(),getNumbersNeeded())
 */
public class UnitsNeeded {

	/**
	 * A policy for allocating units to this UnitsNeeded.
	 */
	private DispatchPolicy policy;
	/**
	 * The emergency that is been handled by this UnitsNeeded class.
	 */
	private final Emergency emergency;
	/**
	 * A DispatchUnitsConstraint object specifying which units can be allocated to the emergency.
	 */
	private DispatchUnitsConstraint constraint;
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
	 *          The emergency that will be handled by this UnitsNeeded.
	 * @param constraint
	 *          A constraint used to determine when units can be assigned to the emergency.
	 * @post This emergency is set to the given emergency.
	 *		| new.getEmergency()==emergency
	 * @post This units is set to the given units.
	 *		| new.getUnits()==units.clone()
	 * @post This numbersNeeded is set to the given numbersNeeded.
	 *		| new.getNumbersNeeded()==numbersNeeded.clone()
	 * @effect Initialize the workingUnits.
	 *		| initWorkingUnits()
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
		initUnits();
	}

	/**
	 * Sets the working units of the emergency to zero.
	 * @post This workingUnits is equal to zero.
	 *		| new.getWorkingUnits() == 0
	 */
	private void initUnits() {
		this.workingUnits = new ArrayList<Unit>(0);
		this.finishedUnits = new ArrayList<Unit>(0);
	}

	/**
	 * Sets the working units of the emergency to the given amount of working units.
	 * @param workingUnits
	 *		The new amount of working units for the emergency.
	 * @post This workingUnit is equal to the given workingUnits.
	 *		| new.getWorkingUnits() == workingUnits
	 * @effect If workingUnits is zero, then the emergency is finished, so its status is set to finished.
	 *		| getEmergency().setStatus(EmergencyStatus.FINISHED)
	 */
	private void setWorkingUnits(ArrayList<Unit> workingUnits) {
		if (workingUnits.size() == 0) {
			try {
				//TODO: moet nog aangepast worden
				getEmergency().setStatus(EmergencyStatus.FINISHED);
			} catch (InvalidEmergencyStatusException ex) {
				//We ensure this can never happen.
				Logger.getLogger(Emergency.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		this.workingUnits = workingUnits;
	}

	private void addWorkingUnits(Unit u) {
		getWorkingUnits().add(u);
		setWorkingUnits(getWorkingUnits());
	}

	private void addFinishedUnits(Unit u){
		getFinishedUnits().add(u);
	}

	/**
	 * Returns the emergency handled by this UnitsNeeded.
	 * @return The emergency handled by this UnitsNeeded.
	 */
	public Emergency getEmergency() {
		return this.emergency;
	}

	/**
	 * Returns the working units of the emergency.
	 * @return The working units of the emergency.
	 */
	public ArrayList<Unit> getWorkingUnits() {
		return workingUnits;
	}

	/**
	 * Returns the finished units of this emergency.
	 * @return The finished units of this emergency.
	 */
	public ArrayList<Unit> getFinishedUnits() {
		return finishedUnits;
	}

	/**
	 * Returns the constraint of this UnitsNeeded.
	 * @return The constraint used by this UnitsNeeded.
	 */
	private DispatchUnitsConstraint getConstraint() {
		return constraint;
	}

	/**
	 * Checks if the given Emergency is valid for this UnitsNeeded class.
	 * @param emergency
	 *		The emergency to test.
	 * @return True if the given emergency is valid, otherwise false.
	 */
	public static boolean isValidEmergency(Emergency emergency) {
		return (emergency != null);
	}

	/**
	 * Checks if the given units can be assigned to the emergency.
	 * @param units
	 *		The units to be assigned.
	 * @return True if the emergency is not handled yet, if all the given units are effective,
	 *		unique and can be assigned and for every quantity constraint,
	 *		this array succeeds, otherwise false.
	 */
	public boolean canAssignUnitsToEmergency(Unit[] units) {
		if (this.getEmergency().getStatus() != EmergencyStatus.RECORDED_BUT_UNHANDLED) {
			return false;
		}

		//TODO: remove duplicates and null pointers out of <units>

		return getConstraint().areValidDispatchUnits(units);
	}

	/**
	 * Assign the given array of units to the emergency.
	 * @param units
	 *		The given array of units.
	 * @post The status of the emergency is RESPONSE_IN_PROGRESS
	 *		| this.getEmergency().getStatus().equals(EmergencyStatus.RESPONSE_IN_PROGRESS)
	 * @post All the units in the given array are assigned
	 *		| forall u in units, u.isAssigned()
	 * @post All the units in the given array are handling the emergency of this UnitNeeded
	 *		| forall u in units.getEmergency().equals(this.getEmergency())
	 * @throws InvalidEmergencyException
	 *		If the units can't be assigned to the emergency (when canAssignUnitsToEmergency fails)
	 * @see UnitsNeeded.canAssignUnitsToEmergency
	 */
	public synchronized void assignUnitsToEmergency(Unit[] units) throws InvalidEmergencyException {
		if (!canAssignUnitsToEmergency(units)) {
			throw new InvalidEmergencyException("Units can't be assigned to the emergency, harm to assignment constraints.");
		}
		try {
			this.getEmergency().setStatus(EmergencyStatus.RESPONSE_IN_PROGRESS);
		} catch (InvalidEmergencyStatusException ex) {
			//We assume this can't happen
			Logger.getLogger(UnitsNeeded.class.getName()).log(Level.SEVERE, null, ex);
		}
		for (Unit u : units) {
			try {
				u.assignTo(this.getEmergency());
			} catch (InvalidMapItemException ex) {
				//We assume this can't be true (checked by the canAssigUnitsToEmergency method)
				Logger.getLogger(UnitsNeeded.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		ArrayList<Unit> unitsToAssign = new ArrayList<Unit>(units.length);
		for (int i = 0; i < units.length; i++) {
			addWorkingUnits(units[i]);
		}
	}

	/**
	 * A method called when a unit finishes his job to manage the emergency.
	 */
	void unitFinishedJob(Unit u) {
		getWorkingUnits().remove(u);
		setWorkingUnits(getWorkingUnits());
		if(u.wasAlreadyAtSite()){
			addFinishedUnits(u);
		}
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
	 * @param policy The given policy to set.
	 * @post The given policy is equal to the policy of this UnitsNeeded
	 *          | new.getPolicy() == policy
	 * @throws InvalidDispatchPolicyException if the policy of the UnitsNeeded is already been set.
	 */
	void setPolicy(DispatchPolicy policy) throws InvalidDispatchPolicyException {
		if (this.getPolicy() != null) {
			throw new InvalidDispatchPolicyException("Policy has already been set.");
		}
		this.policy = policy;
	}

	/**
	 * Tests if the given DispatchUnitConstraint can be a valid constraint.
	 * @param constraint The constraint to check.
	 * @return True if the given constraint is effective, otherwise false.
	 */
	public static boolean isValidConstraint(DispatchUnitsConstraint constraint) {
		return (constraint != null);
	}
}
