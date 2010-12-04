package projectswop20102011.domain;

/**
 * An abstract class representing a constraint to dispatch units.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @note Constraints need to hold an implicit constraint we call the consistency constraint. We define this constraint as follow:
 *          "If a list of units is statisfies this constraint, any expansion of that list also needs to statisfy this constraint"
 */
public abstract class DispatchUnitsConstraint {

    /**
     * Tests if the given Iterable object of units could be allocated to the emergency where this DispatchUnitsConstraint is part of.
     * @param units An iterable object containing only unique and only effective units.
     * @pre The given units parameter contains only unique (no duplicates) effective units.
     * @return True if this constraint passes with the given iterable of units, otherwise false.
     */
    public abstract boolean areValidDispatchUnits (Iterable<? extends Unit> units);

}
