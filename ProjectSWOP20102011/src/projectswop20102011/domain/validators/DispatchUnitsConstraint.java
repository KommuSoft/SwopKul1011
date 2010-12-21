package projectswop20102011.domain.validators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public boolean areAllUnitsReleventAndConstraintPassed(Collection<Unit> units) {
        HashSet<Unit> relevant = new HashSet<Unit>();
        return (areValidDispatchUnits(new ArrayList<Unit>(units), relevant) && relevant.containsAll(units));
    }

    /**
     * Tests if all the given units in the list are relevant for the constraint.
     * @param units The list of units to check.
     * @return True if all units are relevant for the constraint, otherwise false.
     */
    public boolean areAllUnitsRelevant(Collection<Unit> units) {
        HashSet<Unit> relevant = new HashSet<Unit>();
        areValidDispatchUnits(new ArrayList<Unit>(units), relevant);
        return relevant.containsAll(units);
    }

    public boolean areValidDispatchUnits(Collection<Unit> units) {
        return areValidDispatchUnits(new ArrayList<Unit>(units), new HashSet<Unit>());
    }

    /**
     * Tests if the given Iterable object of units could be allocated to the emergency where this DispatchUnitsConstraint is part of.
     * @param units An iterable object containing only unique and only effective units.
     * @param relevantUnits A list where Units out of units are added to if they are relevant to met the contstraint.
     * @pre The given units parameter contains only unique (no duplicates) effective units.
     * @return True if this constraint passes with the given iterable of units, otherwise false.
     * @note The implementation of this method needs to be designed that the units in front of the units list will be added first to the relevantUnits.
     */
    public abstract boolean areValidDispatchUnits(List<Unit> units, Set<Unit> relevantUnits);

    /**
     * Generates a proposal where all the elements of the fixed part are used, and where some of the variableParts are used. The proposal tries to pass the constraint, if that's impossible all Units that
     * @param fixedPart A list of units, where all the elements need to be used.
     * @param variablePart A list of units where some (or even all/none) units are selected.
     * @return If the fixed part is completly used a subset of the varialbePart containing a selection of units that are relevant fo the constraint. Although a combination of this selection and the fixed part doesn't need to pass the constraint. Otherwise an empty list.
     */
    public Collection<Unit> generateProposal(List<Unit> fixedPart, List<Unit> variablePart) {
        ArrayList<Unit> combinedParts = new ArrayList<Unit>(fixedPart);
        int n = fixedPart.size();
        combinedParts.addAll(variablePart);
        HashSet<Unit> proposal = new HashSet<Unit>();

        this.areValidDispatchUnits(combinedParts, proposal);

        for (Unit u : fixedPart) {
            if (proposal.remove(u)) {
                return new HashSet<Unit>();
            }
        }

        return proposal;
    }
}
