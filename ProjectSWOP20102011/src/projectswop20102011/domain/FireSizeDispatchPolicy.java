package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class representing a DispatchPolicy based on the difference of the FireSize of the emergency and the firesize of the firetrucks.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireSizeDispatchPolicy extends DispatchPolicy {

	/**
	 * Creates a new instance of a FireSizeDispatchPolicy with a given UnitsNeeded object of the emergency to handle.
	 * @param unitsNeeded
	 *      The UnitsNeeded object of the emergency this policy will handle.
	 * @effect The new DefaultDispatchPolicy is a DispatchPolicy with the UnitsNeeded object.
	 *          |super(unitsNeeded)
	 * @throws InvalidUnitsNeededException
	 *      If the given UnitsNeeded policy is ineffective.
	 */
	FireSizeDispatchPolicy(UnitsNeeded unitsNeeded) throws InvalidUnitsNeededException {
		super(unitsNeeded);
	}

	/**
	 * Create a new instance of an FireSizeDispatchPolicy with a given UnitsNeeded object of the emergency it will handle and a successor policy.
	 * @param unitsNeeded
	 *		The given unitsNeeded object of the emergency.
	 * @param successor
	 *		The given successor policy
	 * @effect super(unitsNeeded,successor)
	 * @throws InvalidUnitsNeededException
	 *		If the given UnitsNeeded object is not effective.
	 * @throws InvalidDispatchPolicyException
	 *		If the given successor in not a valid successor.
	 */
	FireSizeDispatchPolicy(UnitsNeeded unitsNeeded, DispatchPolicy successor) throws InvalidUnitsNeededException, InvalidDispatchPolicyException {
		super(unitsNeeded, successor);
	}

	@Override
	protected int internalCompare(Unit unit1, Unit unit2) {
		Emergency emergency = getUnitsNeeded().getEmergency();
		if (emergency instanceof Fire && unit1 instanceof Firetruck && unit2 instanceof Firetruck) {
			Firetruck firetruck1 = (Firetruck) unit1;
			Firetruck firetruck2 = (Firetruck) unit2;
			Fire fire = (Fire) emergency;
			return (firetruck1.getMaxSize().ordinal() - fire.getSize().ordinal()) - (firetruck2.getMaxSize().ordinal() - fire.getSize().ordinal());
		} else {
			return 0;
		}
	}
}
