package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class representing the ASAP (As Soon As Possible) dispatch policy.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ASAPDispatchPolicy extends DispatchPolicy {

    /**
     * Creates a new instance with a given UnitsNeeded object of the emergency that will be handled.
     * @param unitsNeeded
     *      The unitsNeeded object of the emergency.
     * @effect The new ASAPDispatchPolicy is a DispatchPolicy with the UnitsNeeded object.
     *		| super(unitsNeeded)
     * @throws InvalidUnitsNeededException
     *      If the given UnitsNeeded object is not effective.
     */
    ASAPDispatchPolicy(UnitsNeeded unitsNeeded) throws InvalidUnitsNeededException {
        super(unitsNeeded);
    }

    /**
     * Compares two different units by there expected time of arrival (ETA).
     * @param unit1
     *      The first unit to compare.
     * @param unit2
     *      The second unit to compare.
     * @return a negative integer, zero, or a positive integer if the first unit is more,
     *          equal or less intresting than the unit according to this Policy. This means
     *          that the unit with the lowest Expected Time of Arrival will be more intresting.
     */
    @Override
    public int internalCompare(Unit unit1, Unit unit2) {
        GPSCoordinate emergencyLocation = this.getUnitsNeeded().getEmergency().getLocation();
        return ((Long) unit1.getETA(emergencyLocation)).compareTo(unit2.getETA(emergencyLocation));
    }
}