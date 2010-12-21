package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class representing a policy used by the dispatch center (for example: defaut policy, ASAP policy,...)
 * @invar The UnitsNeeded object of this policy is always valid.
 *              |isValidUnitsNeeded(getUnitsNeeded())
 * @note Policies are basically sorters who define which units are the most intresting. Based on their criteria they filter the most intresting allocatable units out of a list of units.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class DispatchPolicy implements Comparator<Unit> {

    /**
     * An object that points to the UnitsNeeded object of the emergency this policy will handle.
     */
    private final UnitsNeeded unitsNeeded;
    /**
     * 
     */
    private DispatchPolicy successor;

    /**
     * Creates a new DispatchPolicy with a given unitsNeeded it will handle but without any successor.
     * @param unitsNeeded A unitsNeeded object of the emergency this policy will handle.
     * @effect this(unitsNeeded,null)
     * @throws InvalidUnitsNeededException If the given UnitsNeeded is not effective.
     * @throws InvalidDispatchPolicyException If the given UnitsNeeded has already a policy object.
     */
    protected DispatchPolicy(UnitsNeeded unitsNeeded) throws InvalidUnitsNeededException, InvalidDispatchPolicyException {
        this(unitsNeeded, null);
    }

    /**
     * Creates a new instance of a DispatchPolicy class with a given UnitsNeeded object of the emergency it will handle and a successor to determine an order if this emergency doesn't find any.
     * @param unitsNeeded 
     *      The unitsNeeded object of the emergency this policy will handle.
     * @param successor
     *      The successor of this DispatchPolicy.
     * @effect The policy of the unitsNeed object is set to this policy.
     *      | unitsNeeded.setPolicy(this);
     * @post The unitsNeeded object is set to the given unitsNeeded object.
     * @throws InvalidUnitsNeededException
     *      If the given UnitsNeeded is not effective.
     * @throws InvalidDispatchPolicyException
     *      If the given UnitsNeeded has already a policy object.
     */
    protected DispatchPolicy(UnitsNeeded unitsNeeded, DispatchPolicy successor) throws InvalidUnitsNeededException, InvalidDispatchPolicyException {
        if (!isValidUnitsNeeded(unitsNeeded)) {
            throw new InvalidUnitsNeededException("UnitsNeeded must be effective.");
        }
        unitsNeeded.setPolicy(this);
        this.unitsNeeded = unitsNeeded;
    }

    /**
     * Returns the UnitsNeeded object of the emergency this policy will handle.
     * @return the UnitsNeeded object of the emergency this policy will handle.
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
    public ArrayList<Unit> filterAvailableUnits(List<? extends Unit> availableUnits) {
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
     * Compares two units to find the most interesting one (according to the policy).
     * @param unit1
     *      The first unit to compare
     * @param unit2
     *      The second unit to compare
     * @return A negative integer, zero, or a positive integer as the first unit is more, equal or less interesting than the unit according to this Policy.
     * @note If two objects are assumed to be equivalent, the comparison passes to the successor until one succesor finds a difference, or no successors are available anyomore.
     */
    @Override
    public final int compare(Unit o1, Unit o2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
