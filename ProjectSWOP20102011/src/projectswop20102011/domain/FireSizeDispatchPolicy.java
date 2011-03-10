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
	 *		|super(unitsNeeded)
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

	/**
	 * Compares two different units by the fireSize of the emergency.
	 * @param unit1
	 *      The first unit to compare.
	 * @param unit2
	 *      The second unit to compare.
	 * @return A negative integer, zero, or a positive integer if the first unit is more,
	 *          equal or less interesting than the unit according to this Policy. This means
	 *          that the unit with the lowest return value will be more interesting.
	 * @pre unit1 is a firetruck.
	 * @pre unit2 is a firetruck.
	 * @pre The emergency is a fire.
	 */
	@Override
	protected int internalCompare(Unit unit1, Unit unit2) {
		Emergency emergency = getUnitsNeeded().getEmergency();
		//TODO: Vraagje van PJ, kan er iemand zeggen waarom deze instanceofs niet slecht zijn
		//TODO Antwoord van Jonas (eigenlijk antwooord van Venners & Blewitt):
		//[...]The process of converting the Liquid reference into a Tea reference is called "downcasting" because you are casting the reference "down" the inheritance hierarchy, from Liquid to Tea.
		//This illustrates the kind of situation in which you should use instanceof.
		//You have a base type reference, and if the object referred to by the base type reference really is a certain subclass, you want to invoke a method that only exists in that subclass.
		//Link: http://www.artima.com/objectsandjava/webuscript/PolymorphismInterfaces1.html (sectie When to use instanceof)
		//Hier toegepast: we weten niet of unit1 of unit2 een Firetruck zijn => we mogen geen instanceof gebruiken
		//
		//Als ik dan Blewitt erop na sla (http://www.javaworld.com/javaworld/jw-06-2004/jw-0614-equals.html) lees ik dat instanceof niet aan te raden is omdat het symmetrie breekt (belangrijk in equals(o) en hashcode).
		//De suggestie die daar gegeven wordt is gebruik een getClass() omdat deze wel symmetrisch is.
		//Het bijkomende voordeel van een getClass() is dat ze enkel werkt op het laagste niveau van de Object-tree.
		//vb.: Kat implements Dier.
		// Kat k = new Kat();
		// k.getClass() == Kat.class
		// k.getClass() == Dier.class
		//Heeft als resultaat true en false, terwijl instanceof tweemaal true zou zijn.
		//Hier toegepast: We hebben enkel het laagste niveau nodig dus zou ik voor getClass() gaan
			Firetruck firetruck1 = (Firetruck) unit1;
			Firetruck firetruck2 = (Firetruck) unit2;
			Fire fire = (Fire) emergency;

			return (firetruck1.getMaxSize().ordinal() - fire.getSize().ordinal()) - (firetruck2.getMaxSize().ordinal() - fire.getSize().ordinal());
		
	}
}
