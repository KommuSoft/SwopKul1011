package projectswop20102011;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidUnitBuildingException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;
import projectswop20102011.exceptions.InvalidEmergencyException;

/**
 * A class that represents the units for an Emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @invar The emergency is valid. | isValidEmergency(getEmergency())
 * @invar The unit type list and number of units list are valid | areValidTypesAndNumbersOfUnits(getUnits(),getNumbersNeeded())
 */
public class UnitsNeeded {

    /**
     * The emergency that is been handled by this UnitsNeeded class.
     */
    private final Emergency emergency;
    /**
     * A variable registering the numbersNeeded of this UnitsNeeded.
     */
    private final long[] numbersNeeded;
    /**
     * A variable registering the units of the emergency.
     */
    private final Class[] units;

    /**
     * Creates a new Object that calculates the units needed for an emergency.
     *
     * @param numbersNeeded
     *		The numbers of units needed for this emergency.
     * @param units
     *		The units needed for this emergency.
     * @throws InvalidEmergencyException If the given emergency is not effective.
     * @throws InvalidUnitsNeededException If the given units or numbersNeeded aren't valid.
     * @note This constructor has a package visibility, only instances in the domain layer (Emergencies) can create UnitsNeeded.
     */
    UnitsNeeded(Emergency emergency, Class[] units, long[] numbersNeeded) throws InvalidEmergencyException, InvalidUnitsNeededException {
        if (!isValidEmergency(emergency)) {
            throw new InvalidEmergencyException("Emergency must be effective.");
        }
        if (!areValidTypesAndNumberOfUnits(units, numbersNeeded)) {
            throw new InvalidUnitsNeededException("The unit arrays must have the same length, no unit type is uneffective, and all the number of units are at least zero");
        }
        this.emergency = emergency;
        this.units = (Class[]) units.clone();
        this.numbersNeeded = (long[]) numbersNeeded.clone();
    }

    /**
     * Returns the number of units needed for the emergency.
     * @return the number of units needed for the emergency.
     */
    public long[] getNumbersNeeded() {
        return numbersNeeded.clone();
    }

    /**
     * Returns the units needed for this emergency.
     * @return the units needed for this emergency.
     */
    public Class[] getUnits() {
        return units.clone();
    }

    /**
     * Returns the emergency handled by this UnitsNeeded.
     * @return The emergency handled by this UnitsNeeded.
     */
    public Emergency getEmergency() {
        return this.emergency;
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
     * Checks if the given unit types and numbers are valid for this UnitsNeeded class.
     * @param units
	 *		The types of units needed.
     * @param numbersNeeded The amount of units needed of a certain type.
     * @return True if both arrays are effective and have the same length, no unit type is uneffective and all are subclasses of the Unit class (or are the Unit class), and all the number of units are at least zero.
     */
    public static boolean areValidTypesAndNumberOfUnits(Class[] units, long[] numbersNeeded) {
        if (units == null || numbersNeeded  == null || units.length != numbersNeeded.length) {
            return false;
        }
        for (Class c : units) {
            if (c == null || !Unit.class.isAssignableFrom(c)) {
                return false;
            }
        }
        for (long n : numbersNeeded) {
            if (n < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given units can be assigned to the emergency.
     * @param units
	 *		The units to be assigned.
     * @return True if the emergency is not handled yet, if all the given units are effective, unique and can be assigned and for every quantity constraint, this array succeeds, otherwise false.
     */
    public boolean canAssignUnitsToEmergency(Unit[] units) {
        if (this.getEmergency().getStatus() != EmergencyStatus.RECORDED_BUT_UNHANDLED) {
            return false;
        }
        HashSet<Unit> uniqueUnits = new HashSet<Unit>();
        for (Unit u : units) {
            if (u == null || u.canBeAssigned() || !uniqueUnits.add(u)) {
                return false;
            }
        }
        for (int i = 0; i < this.getUnits().length; i++) {
            Class searchClass = this.units[i];
            long needed = this.numbersNeeded[i];
            int instances = 0;
            for (Unit u : units) {
                if (searchClass.isInstance(u)) {
                    instances++;
                }
            }
            if (instances < needed) {
                return false;
            }
        }
        return true;
    }

    /**
     * assign the given array of units to the emergency.
     * @param units
	 *		The given array of units
     * @post The status of the emergency is RESPONSE_IN_PROGRESS | this.getEmergency().getStatus().equals(EmergencyStatus.RESPONSE_IN_PROGRESS)
     * @post All the units in the given array are assigned | forall u in units, u.isAssigned()
     * @post All the units in the given array are handling the emergency of this UnitNeeded | forall u in units.getEmergency().equals(this.getEmergency())
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
            } catch (InvalidUnitBuildingException ex) {
                //We assume this can't be true (checked by the canAssigUnitsToEmergency method)
                Logger.getLogger(UnitsNeeded.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
