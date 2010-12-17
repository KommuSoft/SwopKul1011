package projectswop20102011.domain.validators;

import java.util.ArrayList;
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

    /**
     * Generates a proposal where all the elements of the fixed part are used, and where some of the variableParts are used. The proposal tries to pass the constraint, if that's impossible all Units that
     * @param fixedPart A list of units, where all the elements need to be used.
     * @param variablePart A list of units where some (or even all/none) units are selected.
     * @return If the fixed part is completly used a subset of the varialbePart containing a selection of units that are relevant fo the constraint. Although a combination of this selection and the fixed part doesn't need to pass the constraint. Otherwise an empty list.
     */
    public ArrayList<Unit> generateProposal(List<Unit> fixedPart, List<Unit> variablePart) {
        ArrayList<Unit> combinedParts = new ArrayList<Unit>(fixedPart);
        int n = fixedPart.size();
        combinedParts.addAll(variablePart);
        boolean[] selection = new boolean[combinedParts.size()];
        this.areValidDispatchUnits(combinedParts, selection);
        for(int i = 0; i < fixedPart.size(); i++) {
            if(!selection[i])
                return new ArrayList<Unit>();
        }
        ArrayList<Unit> proposal = new ArrayList<Unit>();
        for(int i = fixedPart.size(); i < combinedParts.size(); i++) {
            if(selection[i]) {
                proposal.add(combinedParts.get(i));
            }
        }
        return proposal;
    }
}