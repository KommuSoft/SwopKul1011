package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidConstraintListException;

/**
 * A constraint class checking if a collection of other constraints all pass.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AndDispatchUnitsConstraint extends DispatchUnitsConstraint {

    private final DispatchUnitsConstraint[] constraints;

    /**
     * Creates a nez AndDispatchUnitsConstraint with the given constraints below that all should pass to let pass this constraint.
     * @param constraints The list of constraints that all should pass.
     * @throws InvalidConstraintListException If the given list of constraints is invalid.
     */
    public AndDispatchUnitsConstraint(DispatchUnitsConstraint... constraints) throws InvalidConstraintListException {
        if (!areValidConstraints(constraints)) {
            throw new InvalidConstraintListException("The list with constraints and all its elements must be effective.");
        }
        this.constraints = constraints.clone();
    }

    /**
     * Tests if the given list of constraints is a valid constraint argument in the constructor.
     * @param constraints A list of constraints to check.
     * @return True if the list and all it's elements are effective, otherwise false.Ò
     */
    public static boolean areValidConstraints(DispatchUnitsConstraint... constraints) {
        if (constraints == null) {
            return false;
        }
        for (DispatchUnitsConstraint duc : constraints) {
            if (duc == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tests if the given Iterable object of units could be allocated to the emergency where this DispatchUnitsConstraint is part of.
     * @param units An iterable object containing only unique and only effective units.
     * @pre The given units parameter contains only unique (no duplicates) effective units.
     * @return If all the constraints below this constraint pass, otherwise false.
     */
    @Override
    public boolean areValidDispatchUnits(Iterable<? extends Unit> units) {
        for (DispatchUnitsConstraint duc : this.constraints) {
            if (!duc.areValidDispatchUnits(units)) {
                return false;
            }
        }
        return true;
    }
}
