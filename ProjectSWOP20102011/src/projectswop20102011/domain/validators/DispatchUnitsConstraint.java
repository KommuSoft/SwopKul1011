package projectswop20102011.domain.validators;

import java.util.List;
import projectswop20102011.domain.Unit;

/**
 * An abstract class representing a constraint to dispatch units.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class DispatchUnitsConstraint {

    /**
     * Checks if the given list of units passes this constraint and all units are relevant to this constraint.
     * @param units The list of units to check.
     * @return True if the given list passes the constraints and all the units are used, otherwise false.
     */
    public boolean areValidDispatchUnitsAndAllUsed(List<Unit> units) {
        boolean[] used = new boolean[units.size()];
        boolean r = areValidDispatchUnits(units, used);
        if (!r) {
            return false;
        }
        for (boolean u : used) {
            if (!u) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tests if all the given units in the list are relevant for the constraint.
     * @param units The list of units to check.
     * @return True if all units are relevant for the constraint, otherwise false.
     */
    public boolean areAllUnitsUsed (List<Unit> units) {
        boolean[] used = new boolean[units.size()];
        areValidDispatchUnits(units,used);
        for(boolean u : used) {
            if(!u) {
                return false;
            }
        }
        return true;
    }

    public boolean areValidDispatchUnits (List<Unit> units) {
        return areValidDispatchUnits(units,new boolean[units.size()]);
    }

    /**
     * Tests if the given Iterable object of units could be allocated to the emergency where this DispatchUnitsConstraint is part of.
     * @param units An iterable object containing only unique and only effective units.
     * @param used An array of booleans that gather information about the usage of the units. If a unit is needed, the boolean with the same index will be true.
     * @pre The given units parameter contains only unique (no duplicates) effective units.
     * @pre The used parameter is effective and has the same length as units.
     * @return True if this constraint passes with the given iterable of units, otherwise false.
     */
    public abstract boolean areValidDispatchUnits(List<Unit> units, boolean[] used);
}
