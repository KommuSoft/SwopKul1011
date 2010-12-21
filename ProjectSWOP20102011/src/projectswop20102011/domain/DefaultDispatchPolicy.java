package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class that represents the default policy used to handle an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DefaultDispatchPolicy extends DispatchPolicy {

    /**
     * Creates a new instance of a DefaultDispatchPolicy with a given UnitsNeeded object of the emergency to handle.
     * @param unitsNeeded 
     *      The UnitsNeeded object of the emergency this policy will handle.
     * @effect The new DefaultDispatchPolicy is a DispatchPolicy with the UnitsNeeded object.
     *          |super(unitsNeeded)
     * @throws InvalidUnitsNeededException
     *      If the given UnitsNeeded policy is ineffective.
     * @throws InvalidDispatchPolicyException
     *      If the given UnitsNeeded object has already a Policy.
     */
    DefaultDispatchPolicy(UnitsNeeded unitsNeeded) throws InvalidUnitsNeededException, InvalidDispatchPolicyException {
        super(unitsNeeded);
    }

    /**
     * Compares two different units by the distance to the location of the emergency.
     * @param unit1
     *      The first unit to compare.
     * @param unit2
     *      The second unit to compare.
     * @return a negative integer, zero, or a positive integer if the first unit is more,
     *          equal or less interesting than the unit according to this Policy. This means
     *          that the unit with the lowest distance to the emergency will be more interesting.
     */
    @Override
    public int compare(Unit unit1, Unit unit2) {
        GPSCoordinate emergencyLocation = this.getUnitsNeeded().getEmergency().getLocation();
        return ((Double) unit1.getDistanceTo(emergencyLocation)).compareTo(unit2.getDistanceTo(emergencyLocation));
    }
}
