package projectswop20102011.domain;

import java.util.Arrays;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class that represents the default policy used to handle an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DefaultDispatchPolicy extends DispatchPolicy {

    /**
     * Creates a new instance of a DefaultDispatchPolicy with a given UnitsNeeded object of the emergency to handle.
     * @param unitsNeeded The UnitsNeeded object of the emergency this policy will handle.
     * @throws InvalidUnitsNeededException If the given UnitsNeeded policy is ineffective.
     * @throws InvalidDispatchPolicyException If the given UnitsNeeded object has already a Policy.
     */
    public DefaultDispatchPolicy(UnitsNeeded unitsNeeded) throws InvalidUnitsNeededException, InvalidDispatchPolicyException {
        super(unitsNeeded);
    }

    /**
     * Fiters the selected units (by the policy) out of a number of available units
     * @param availableUnits A list of units that are available to handle an emergency (not necessarily this emergency in particular).
     * @return A list of units that would be allocated to the emergency if the policy would be applied.
     * @pre The list of available units is effective and contains unique (no duplicates) units that are available for an emergency.
     */
    @Override
    public Unit[] filterAvailableUnits(Unit[] availableUnits) {
        //TODO: Implement (wait for the Constraint classes)
        Arrays.sort(availableUnits, this);
        Unit[] used = getUnitsNeeded().chooseUnits(availableUnits);
        

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * Compares two different units by the distance to the location of the emergency.
     * @param unit1 The first unit to compare.
     * @param unit2 The second unit to compare.
     * @return a negative integer, zero, or a positive integer as the first unit is more,
     *          equal or less intresting than the unit according to this Policy. This means
     *          that the unit with the lowest distqnce to the emergency will be more intresting.
     */
    @Override
    public int compare(Unit unit1, Unit unit2) {
        GPSCoordinate emergencyLocation = this.getUnitsNeeded().getEmergency().getLocation();
        return ((Double) unit1.getDistanceTo(emergencyLocation)).compareTo(unit2.getDistanceTo(emergencyLocation));
    }
}
