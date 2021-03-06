package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class representing a policy used by the dispatch center (for example: default policy, ASAP policy,...)
 * @invar The UnitsNeeded object of this policy is always valid.
 *		|isValidUnitsNeeded(getUnitsNeeded())
 * @invar The succesor of this DispatchPolicy is always valid.
 *		|isValidSuccessor(getSuccesor())
 * @note Policies are basically sorters who define which units are the most interesting. Based on their criteria they filter the most intresting allocatable units out of a list of units.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class DispatchPolicy implements Comparator<Unit> {

	/**
	 * An object that points to the UnitsNeeded object of the emergency this policy will handle.
	 */
	private final UnitsNeeded unitsNeeded;
	/**
	 * The succesor of this DispatchPolicy.
	 */
	private DispatchPolicy successor = null;

	/**
	 * Creates a new DispatchPolicy with a given unitsNeeded it will handle but without any successor.
	 * @param unitsNeeded
	 *		A unitsNeeded object of the emergency this policy will handle.
	 * @post The unitsNeeded object is equal to the given unitsNeeded object.
	 *		|this.getUnitsNeeded() == unitsNeeded
	 * @throws InvalidUnitsNeededException
	 *		If the given UnitsNeeded is not effective.
	 */
	protected DispatchPolicy(UnitsNeeded unitsNeeded) throws InvalidUnitsNeededException {
		if (!isValidUnitsNeeded(unitsNeeded)) {
			throw new InvalidUnitsNeededException("UnitsNeeded must be effective.");
		}
		this.unitsNeeded = unitsNeeded;
	}

	/**
	 * Creates a new instance of a DispatchPolicy class with a given UnitsNeeded object of the emergency it will handle and a successor to determine an order if this emergency doesn't find any.
	 * @param unitsNeeded
	 *      The unitsNeeded object of the emergency this policy will handle.
	 * @param successor
	 *      The successor of this DispatchPolicy.
	 * @effect The unitsNeeded object is equal to the given unitsNeeded object.
	 *		|this(unitsNeeded)
	 * @effect The successor object is equal to the given successor object.
	 *		|setSuccessor(succesor)
	 * @throws InvalidUnitsNeededException
	 *      If the given UnitsNeeded is not effective.
	 * @throws InvalidDispatchPolicyException
	 *		If the given successor is invalid.
	 */
	protected DispatchPolicy(UnitsNeeded unitsNeeded, DispatchPolicy successor) throws InvalidUnitsNeededException, InvalidDispatchPolicyException {
		this(unitsNeeded);
		this.setSuccessor(successor);
	}

	/**
	 * Returns the UnitsNeeded object of the emergency this policy will handle.
	 * @return The UnitsNeeded object of the emergency this policy will handle.
	 */
	protected UnitsNeeded getUnitsNeeded() {
		return this.unitsNeeded;
	}

	/**
	 * Filters the selected units (by the policy) out of a number of available units.
	 * @pre The list of available units is effective and contains unique (no duplicates) units that are available for an emergency.
	 * @param availableUnits
	 *      A list of units that are available to handle an emergency (not necessarily this emergency in particular).
	 * @return A list of units that would be allocated to the emergency if the policy would be applied.
	 */
	public Set<Unit> generateProposal(List<? extends Unit> availableUnits) {
		ArrayList<Unit> clonedList = new ArrayList<Unit>(availableUnits);
		Collections.sort(clonedList, this);
		return this.getUnitsNeeded().generateProposal(clonedList);
	}

	/**
	 * Tests if the given UnitsNeeded object is a valid UnitsNeeded parameter.
	 * @param unitsNeeded
	 *      The UnitsNeeded object to test.
	 * @return True if the UnitsNeeded object is effective, otherwise false.
	 */
	public static boolean isValidUnitsNeeded(UnitsNeeded unitsNeeded) {
		return (unitsNeeded != null);
	}

	/**
	 * An internal comparison method defining the order of this policy.
	 * @param unit1
	 *		The first unit to compare.
	 * @param unit2
	 *		The second unit to compare.
	 * @return A negative integer, zero, or a positive integer as the first unit is more, equal or less intresting than the unit according to this Policy.
	 */
	protected abstract int internalCompare(Unit unit1, Unit unit2);

	/**
	 * Compares two units to find the most intresting one (according to the policy).
	 * @param unit1
	 *		The first unit to compare.
	 * @param unit2
	 *		The second unit to compare.
	 * @return A negative integer, zero, or a positive integer as the first unit is more, equal or less intresting than the unit according to this Policy.
	 * @note If two objects are assumed to be equivalent, the comparison passes to the successor until one succesor finds a difference, or no successors are available anyomore.
	 */
	@Override
	public final int compare(Unit unit1, Unit unit2) {
		final int firstResult = internalCompare(unit1, unit2);
		if (cantSolveAndHasSuccessor(firstResult)) {
			return getSuccessor().compare(unit1, unit2);
		}
		return firstResult;
	}

	/**
	 * Checks if the given parameter is a result that can't solve the policy and that this policy has a successor.
	 * @param firstResult
	 *		The result of the comparison.
	 * @see #compare
	 * @return True if the parameter is zero and there is a successor. False otherwise.
	 */
	private boolean cantSolveAndHasSuccessor(int firstResult){
		return firstResult == 0 && getSuccessor() != null;
	}

	/**
	 * Gets the successor of this DispatchPolicy.
	 * @return the successor of this DispatchPolicy.
	 */
	public DispatchPolicy getSuccessor() {
		return this.successor;
	}

	/**
	 * Returns the end of the chain of the responsibilities.
	 * @return The end of the chain of the responsibilities.
	 */
	public DispatchPolicy getDeepSuccessor() {
		if (this.getSuccessor() == null) {
			return this;
		} else {
			return this.getSuccessor().getDeepSuccessor();
		}
	}

	/**
	 * Sets the successor of this DispatchPolicy to the given successor.
	 * @param successor
	 *		The given successor.
	 * @post The succesor of this policy is equal to the given policy.
	 *		|new.getSuccessor() == successor
	 * @throws InvalidDispatchPolicyException
	 *		If the given succesor is effective and doesn't handle the same UnitsNeeded or creates a loop.
	 */
	public void setSuccessor(DispatchPolicy successor) throws InvalidDispatchPolicyException {
		if (!isValidSuccessor(successor)) {
			throw new InvalidDispatchPolicyException("Can't set successor: successor must be ineffective or must handle the same UnitsNeeded and no loops are allowed.");
		}
		this.successor = successor;
	}

	/**
	 * Checks if the given successor is a valid successor for this DispatchPolicy.
	 * @param successor
	 *		The successor to check for.
	 * @return True if the given successor is ineffective or handles the same UnitsNeeded and does not create a loop.
	 */
	public boolean isValidSuccessor(DispatchPolicy successor) {
		if (successor == null) {
			return true; //todo test hiervoor schrijven
		}
		if (successor.getUnitsNeeded() != this.getUnitsNeeded()) {
			return false; //todo test hiervoor schrijven
		}
		return !detectLoop(successor);
	}

	/**
         * Checks if taking the given successor as the successor of this DispatchPolicy, there would become a loop in the chain of responsibilities.
         * @param successor The successor to test for.
         * @return True if we create a loop in the system, otherwise false.
         * @note This condition always needs to fail for the current successor.
         */
        private boolean detectLoop(DispatchPolicy successor) {
		DispatchPolicy deepSuccessor = successor;
		while (deepSuccessor != null && deepSuccessor != this) {
			deepSuccessor = deepSuccessor.getSuccessor();
		}
		return (deepSuccessor != null); //todo test hiervoor schrijven (deel 2)
	}
}
