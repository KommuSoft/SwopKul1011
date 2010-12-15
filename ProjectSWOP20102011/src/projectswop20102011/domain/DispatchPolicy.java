package projectswop20102011.domain;

import java.util.Comparator;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class representing a default policy used by the dispatch center (for example: defaut policy, ASAP policy,...)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class DispatchPolicy implements Comparator<Unit> {

    /**
     * The unitsneeded object of the emergency where this policy will be applied on.
     */
    private final UnitsNeeded unitsNeeded;

    /**
     * Creates a new instance of a DispatchPolicy class with a given UnitsNeeded object of the emergency it will handle.
     * @param unitsNeeded The unitsNeeded object of the emergency this policy will handle.
     * @throws InvalidUnitsNeededException If the given UnitsNeeded is not effective.
     * @throws InvalidDispatchPolicyException If the given UnitsNeeded has already a policy object.
     */
    protected DispatchPolicy(UnitsNeeded unitsNeeded) throws InvalidUnitsNeededException, InvalidDispatchPolicyException {
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
     * Fiters the selected units (by the policy) out of a number of available units
     * @param availableUnits A list of units that are available to handle an emergency (not necessarily this emergency in particular).
     * @return A list of units that would be allocated to the emergency if the policy would be applied.
     * @pre The list of available units is effective and contains unique (no duplicates) units that are available for an emergency.
     */
    public abstract Unit[] filterAvailableUnits(Unit[] availableUnits);

    /**
     * Tests if the given UnitsNeeded object is a valid UnitsNeeded parameter.
     * @param unitsNeeded The UnitsNeeded object to test.
     * @return True if the UnitsNeeded object is effective, otherwise false.
     */
    public static boolean isValidUnitsNeeded(UnitsNeeded unitsNeeded) {
        return (unitsNeeded != null);
    }
}
