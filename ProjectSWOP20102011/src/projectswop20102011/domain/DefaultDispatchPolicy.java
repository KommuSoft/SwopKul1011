package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class that represents the default policy used to handle an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DefaultDispatchPolicy extends DispatchPolicy {

	/**
	 * Creates a new instance of a DefaultDispatchPolicy with a given ConcreteUnitsNeeded object of the emergency to handle.
	 * @param unitsNeeded
	 *      The ConcreteUnitsNeeded object of the emergency this policy will handle.
	 * @effect The new DefaultDispatchPolicy is a DispatchPolicy with the givenUnitsNeeded object.
	 *		| super(unitsNeeded)
	 * @throws InvalidUnitsNeededException
	 *      If the given ConcreteUnitsNeeded policy is ineffective.
	 */
	DefaultDispatchPolicy(ConcreteUnitsNeeded unitsNeeded) throws InvalidUnitsNeededException {
		super(unitsNeeded);
	}

	/**
	 * Creates a new instance of an DefaultDispatchPolicy with a given ConcreteUnitsNeeded object of the emergency it will handle and a successor policy.
	 * @param unitsNeeded
	 *		The given unitsNeeded object of the emergency.
	 * @param successor
	 *		The given successor policy.
	 * @effect The new DefaultDispatchPolicy is a DispatchPolicy with the given ConcreteUnitsNeeded object and the given DispatchPolicy.
	 *		|super(unitsNeeded,successor)
	 * @throws InvalidUnitsNeededException
	 *		If the given ConcreteUnitsNeeded object is not effective.
	 * @throws InvalidDispatchPolicyException
	 *		If the given successor in not a valid successor.
	 */
	DefaultDispatchPolicy(ConcreteUnitsNeeded unitsNeeded, DispatchPolicy successor) throws InvalidUnitsNeededException, InvalidDispatchPolicyException {
		super(unitsNeeded, successor);
	}

	/**
	 * Compares two different units by the distance to the location of the emergency.
	 * @param unit1
	 *      The first unit to compare.
	 * @param unit2
	 *      The second unit to compare.
	 * @return A negative integer, zero, or a positive integer if the first unit is more,
	 *		equal or less interesting than the unit according to this Policy. This means
	 *		that the unit with the lowest distance to the emergency will be more interesting.
	 */
	@Override
	public int internalCompare(Unit unit1, Unit unit2) {
		final GPSCoordinate emergencyLocation = this.getUnitsNeeded().getSendable().getLocation(); //Todo hide delegate hier toepassen? (ASAPDispatchPolicy eigenlijk ook)
		final Double distanceOfUnit1 = unit1.getDistanceTo(emergencyLocation);
		final Double distanceOfUnit2 = unit2.getDistanceTo(emergencyLocation);
		return distanceOfUnit1.compareTo(distanceOfUnit2);
	}
}
