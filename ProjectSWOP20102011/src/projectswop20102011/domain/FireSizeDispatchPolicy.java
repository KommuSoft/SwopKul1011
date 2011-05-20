package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class representing a DispatchPolicy based on the difference of the FireSize of the emergency and the firesize of the firetrucks.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireSizeDispatchPolicy extends DispatchPolicy {

    /**
     * Creates a new instance of a FireSizeDispatchPolicy with a given ConcreteUnitsNeeded object of the emergency to handle.
     * @param unitsNeeded
     *      The ConcreteUnitsNeeded object of the emergency this policy will handle.
     * @effect The new DefaultDispatchPolicy is a DispatchPolicy with the ConcreteUnitsNeeded object.
     *		|super(unitsNeeded)
     * @throws InvalidUnitsNeededException
     *      If the given ConcreteUnitsNeeded policy is ineffective.
     */
    FireSizeDispatchPolicy(ConcreteUnitsNeeded unitsNeeded) throws InvalidUnitsNeededException {
        super(unitsNeeded);
    }

    /**
     * Create a new instance of an FireSizeDispatchPolicy with a given ConcreteUnitsNeeded object of the emergency it will handle and a successor policy.
     * @param unitsNeeded
     *		The given unitsNeeded object of the emergency.
     * @param successor
     *		The given successor policy
     * @effect super(unitsNeeded,successor)
     * @throws InvalidUnitsNeededException
     *		If the given ConcreteUnitsNeeded object is not effective.
     * @throws InvalidDispatchPolicyException
     *		If the given successor in not a valid successor.
     */
    FireSizeDispatchPolicy(ConcreteUnitsNeeded unitsNeeded, DispatchPolicy successor) throws InvalidUnitsNeededException, InvalidDispatchPolicyException {
        super(unitsNeeded, successor);
    }

    /**
     * Compares two different units by the fireSize of the emergency.
     * @param unit1
     *      The first unit to compare.
     * @param unit2
     *      The second unit to compare.
     * @return A negative integer, zero, or a positive integer if the first unit is more,
     *          equal or less interesting than the unit according to this Policy. This means
     *          that the unit with the lowest return value will be more interesting.
     */
    @Override
    protected int internalCompare(Unit unit1, Unit unit2) {
        Emergency emergency = getUnitsNeeded().getEmergency();
        if (emergency.getClass() == Fire.class) {
            boolean isF1 = isFireTruck(unit1);
            boolean isF2 = isFireTruck(unit2);
            if (isF1 && isF2) {
                Firetruck firetruck1 = (Firetruck) unit1;
                Firetruck firetruck2 = (Firetruck) unit2;
                Fire fire = (Fire) emergency;
                Long dif1 = Math.abs(firetruck1.getCapacity() - fire.getNumberOfLitersRequired());
                Long dif2 = Math.abs(firetruck2.getCapacity() - fire.getNumberOfLitersRequired());
                return dif1.compareTo(dif2);
            }
            else if(isF1 && !isF2) {
                return -1;
            }
            else if(!isF1 && isF2) {
                return 1;
            }
            else {
                return 0;
            }

        } else {
            return 0;
        }
    }
    private boolean isFireTruck (Unit u) {
        return (u.getClass() == Firetruck.class);
    }
    
}
