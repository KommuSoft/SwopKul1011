package projectswop20102011.domain;

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
    private final DispatchPolicy policy;
    /**
     * The emergency that is been handled by this UnitsNeeded class.
     */
    private final Emergency emergency;
    /**
     * A DispatchUnitsConstraint object specifying which units can be allocated to the emergency.
     */
    private DispatchUnitsConstraint constraint;
    /**
     * A variable registering the amount of units that are currently working at the emergency.
     */
    private long workingUnits;

    /**
     * Creates a new object that calculates the units needed for an emergency.
     * @param emergency
     *          The emergency that will be handled by this UnitsNeeded.
     * @param constraint
     *          A constraint used to determine when units can be assigned to the emergency.
     * @param policy
     *          A DispatchPolicy object that can give a unit allocation proposal.
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
     * @throws InvalidDispatchPolicyException
     *          If the given policy is ineffective.
     * @note This constructor has a package visibility, only instances in the domain layer (Emergencies) can create UnitsNeeded.
     */
    UnitsNeeded(Emergency emergency, DispatchUnitsConstraint constraint, DispatchPolicy policy) throws InvalidEmergencyException, InvalidDispatchUnitsConstraintException, InvalidDispatchPolicyException {
        if (!isValidEmergency(emergency)) {
            throw new InvalidEmergencyException("Emergency must be effective.");
        }
        if (!isValidConstraint(constraint)) {
            throw new InvalidDispatchUnitsConstraintException("The constraint must be effective.");
        }
        if(!isValidPolicy(policy)) {
            throw new InvalidDispatchPolicyException("The policy must be effective.");
        }
        this.emergency = emergency;
        this.constraint = constraint;
        this.policy = policy;
        initWorkingUnits();
    }

    /**
     * Sets the working units of the emergency to zero.
     * @post This workingUnits is equal to zero.
     *		| new.getWorkingUnits() == 0
     */
    private void initWorkingUnits() {
        this.workingUnits = 0;
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
    private void setWorkingUnits(long workingUnits) {
        if (workingUnits == 0) {
            try {
                getEmergency().setStatus(EmergencyStatus.FINISHED);
            } catch (InvalidEmergencyStatusException ex) {
                //We ensure this can never happen.
                Logger.getLogger(Emergency.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.workingUnits = workingUnits;
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
    public long getWorkingUnits() {
        return workingUnits;
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

        return this.constraint.areValidDispatchUnits(units);
    }

    /**
     * Assign the given array of units to the emergency.
     *
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
        setWorkingUnits(units.length);
    }

    /**
     * A method called when a unit finishes his job to manage the emergency.
     */
    void unitFinishedJob() {
        this.setWorkingUnits(this.getWorkingUnits() - 1);
    }

    /**
     * Returns a DispatchPolicy object that can give a proposal for unit allocations.
     * @return A DispatchPolicy object that can give a proposal for unit allocations.
     */
    public DispatchPolicy getPolicy () {
        return this.policy;
    }

    /**
     * Tests if the given DispatchUnitConstraint can be a valid constraint.
     * @param constraint The constraint to check.
     * @return True if the given constraint is effective, otherwise false.
     */
    public static boolean isValidConstraint(DispatchUnitsConstraint constraint) {
        return (constraint != null);
    }

    /**
     * Tests if the given policy is a valid policy for a UnitsNeeded object.
     * @param policy The policy to check.
     * @return True if the given policy is effective, otherwise false.
     */
    public static boolean isValidPolicy(DispatchPolicy policy) {
        return (policy != null);
    }
}
