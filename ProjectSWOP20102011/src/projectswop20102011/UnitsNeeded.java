package projectswop20102011;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
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
    private final Class<? extends Unit>[] units;

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
        if(!isValidEmergency(emergency)) {
            throw new InvalidEmergencyException("Emergency must be effective.");
        }
        if(!areValidTypesAndNumberOfUnits(units,numbersNeeded)) {
                throw new InvalidUnitsNeededException("The unit arrays must have the same length, no unit type is uneffective, and all the number of units are at least zero");
        }
        this.emergency = emergency;
        this.units = Arrays.copyOf(units, units.length);
        this.numbersNeeded = Arrays.copyOf(numbersNeeded, numbersNeeded.length);
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
    public Class<? extends Unit>[] getUnits() {
        return units.clone();
    }

    /**
     * Returns the emergency handled by this UnitsNeeded.
     * @return The emergency handled by this UnitsNeeded.
     */
    public Emergency getEmergency () {
        return this.emergency;
    }

    /**
     * Checks if the given Emergency is valid for this UnitsNeeded class.
     * @param emergency The emergency to test.
     * @return True if the given emergency is valid, otherwise false.
     */
    public static boolean isValidEmergency (Emergency emergency) {
        return (emergency != null);
    }
    /**
     * Checks if the given unit types and numbers are valid for this UnitsNeeded class.
     * @param units The types of units needed.
     * @param numbersNeeded The amount of units needed of a certain type.
     * @return True if both arrays have the same length, no unit type is uneffective, the given unit types list does not contain duplicates, and all the number of units are at least zero.
     * @note Note however that the array of unittypes may contains several classes who are subclasses of some other type. This only creates additional constraints on the list of units.
     */
    public static boolean areValidTypesAndNumberOfUnits (Class<? extends Unit>[] units, long[] numbersNeeded) {
        if(units.length != numbersNeeded.length) {
            return false;
        }
        for(int i = 0; i < units.length; i++) {
            if(units[i] == null) {
                return false;
            }
            for(int j = 0; j < i; j++) {
                if(units[i].equals(units[j])) {
                    return false;
                }
            }
        }
        for(long n : numbersNeeded) {
            if(n < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean canAssignUnitsToEmergency (Unit[] units) {
        if(this.getEmergency().getStatus() != EmergencyStatus.RECORDED_BUT_UNHANDLED) {
            return false;
        }
        //TODO: checks number conditions
        return true;
    }

    public synchronized void assignUnitsToEmergency (Unit[] units) throws InvalidEmergencyException {
        if(!canAssignUnitsToEmergency(units)) {
            throw new InvalidEmergencyException("Units can't be assigned to the emergency");
        }
        try {
            getEmergency().setStatus(EmergencyStatus.RESPONSE_IN_PROGRESS);
			//getEmergency().setWorkingUnits(emergency.getWorkingUnits() + 1);
			for(int i=0; i<units.length; ++i){
				try {
					units[i].assignTo(getEmergency());
				} catch (InvalidAmbulanceException ex) {
					//We assume this can't happen
					Logger.getLogger(UnitsNeeded.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
        } catch (InvalidEmergencyStatusException ex) {
            //We assume this cannot happen
            Logger.getLogger(UnitsNeeded.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}