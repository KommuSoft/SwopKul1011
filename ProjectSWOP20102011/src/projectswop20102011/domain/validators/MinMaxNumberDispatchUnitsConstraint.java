package projectswop20102011.domain.validators;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a constraint that checks the minimum and maximum number of units.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class MinMaxNumberDispatchUnitsConstraint extends DispatchUnitsConstraint {

    /**
     * A variable registering the minimum desired number of units
     */
    private long minimum;
    /**
     * A variable registering the maximum desired number of units
     */
    private long maximum;
    /**
     * Tests if the given validator is a valid validator for a MinMaxNumberDispatchUnitsConstraint.
     * @param validator
     *		The validator to validate.
     * @return True if the given validator is effective.
     */
    private final UnitValidator validator;

    /**
     * Creates a MinMaxNumberDispatchUnitsConstraint with the given units and number.
     * @param validator
     *		The validator that specifies what will be counted.
     * @param minimum
     *		The desired minimum number of units.
     * @param maximum
     *		The desired minimum number of units.
     * @post This minimum is equal to the given minimum.
     *		|this.minimum.equals(minimum)
     * @post This maximum is equal to the given maximum.
     *		|this.maximum.equals(maximum)
     * @post This validator is equal to the given validator.
     *		|this.validator.equals(validator)
     * @throws NumberOutOfBoundsException
     *		If the given number is invalid.
     * @throws InvalidValidatorException
     *		If the given UnitValidator is invalid.
     */
    public MinMaxNumberDispatchUnitsConstraint(UnitValidator validator, long minimum, long maximum) throws NumberOutOfBoundsException, InvalidValidatorException {
        if (!areValidMinimumMaximum(minimum, maximum)) {
            throw new NumberOutOfBoundsException("The numbers need to be larger or equal to zero, and the maximum must be larger or equal to the minumum.");
        }
        if (!isValidValidator(validator)) {
            throw new InvalidValidatorException("UnitValidator must be effective.");
        }
        this.minimum = minimum;
        this.maximum = maximum;

        this.validator = validator;
    }

    /**
     * Returns the validator that specifies what will be counted.
     * @return the validator that specifies what will be counted.
     */
    public UnitValidator getValidator() {
        return validator;
    }

    /**
     * Returns the minimum desired number of units.
     * @return The minimum desired number of units.
     */
    public long getMinimum() {
        return minimum;
    }

    /**
     * Returns the maximum desired number of units.
     * @return The maximum desired number of units.
     */
    public long getMaximum() {
        return maximum;
    }

    /**
     * Tests if the given validator is a valid validator for a MinMaxNumberDispatchUnitsConstraint.
     * @param validator
     *		The validator to validate.
     * @return True if the given validator is effective.
     */
    public static boolean isValidValidator(UnitValidator validator) {
        return (validator != null);
    }

    /**
     * Tests if the given number is a valid number for a MinMaxNumberDispatchUnitsConstraint.
     * @param number
     *		The number to validate.
     * @return True if the given number is larger or equal to zero, false otherwise.
     */
    public static boolean isValidNumber(long number) {
        return (number >= 0);
    }

    /**
     * Tests if the given minimum and maximum number are valid.
     * @param minimum
     *          The given minimum bound.
     * @param maximum
     *          The given maximum bound.
     * @return True if the given minimum and maximum are valid number and the minimum is less or equal to the maximum.
     */
    public static boolean areValidMinimumMaximum(long minimum, long maximum) {
        return (isValidNumber(minimum) && isValidNumber(maximum) && (minimum <= maximum));
    }

    //TODO: beter verplaatsen naar een Util?
    private int collectValidUnits(Collection<Unit> source, Collection<Unit> collected) {
        int count = 0;
        for (Unit u : source) {
            if (this.getValidator().isValid(u)) {
                collected.add(u);
                count++;
            }
        }
        return count;
    }

    //TODO: beter verplaatsen naar een Util?
    private int countValidUnits(Collection<Unit> source) {
        return collectValidUnits(source, new HashSet<Unit>());
    }

    /**
     * Generates a proposal for units based on the set of available units, and adds them to the proposal.
     * @param finishedOrAssignedUnits The list of units that are already finished or assigned.
     * @param availableUnits The set of available units to assign.
     * @param proposal A set where the units that will be proposed will be added to.
     * @return True if this methods can generate a proposal without violating constraints (for example firetrucks can only added once, sometimes we can't generate a proposal).
     */
    @Override
    public boolean generateProposal(List<Unit> finishedOrAssignedUnits, SortedSet<Unit> availableUnits, Set<Unit> proposal) {
        long required = getMinimum() - countValidUnits(finishedOrAssignedUnits);
        if (required <= 0) {
            return true;
        }
        for (Unit u : availableUnits) {
            if (this.getValidator().isValid(u)) {
                proposal.add(u);
                required--;
                if (required <= 0) {
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the given set of units to assign to an emergency can be assigned.
     * @param finishedOrAssignedUnits The list of Units that were already
     * @param toAssignUnits The list of units to check if they can be assigned.
     * @param relevantUnits A set of units containing after this method all the units from toAssignUnits that are relevant.
     * @return True if the given set of units can be assigned, otherwise false.
     * @note For a valid assignment, all the units from toAssign needs to be in the relevantUnits at the end of this method.
     */
    @Override
    protected boolean canAssign(List<Unit> finishedOrAssignedUnits, SortedSet<Unit> toAssignUnits, Set<Unit> relevantUnits) {
        long counter = countValidUnits(finishedOrAssignedUnits);
        if (counter >= this.getMaximum()) {
            return true;
        }
        for (Unit u : toAssignUnits) {
            if (this.getValidator().isValid(u)) {
                counter++;
                relevantUnits.add(u);
                if (counter >= this.getMaximum()) {
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the emergency can be finished if the given units have all done their job in the emergency.
     * @param finishedUnits A list of finished Units.
     * @return True if the given emergency can finish, otherwise false.
     */
    @Override
    public boolean canFinish(List<Unit> finishedUnits) {
        int valids = countValidUnits(finishedUnits);
        return (valids >= this.getMinimum() && valids <= this.getMaximum());
    }

    /**
     * Returns a textual representation of the NumberDispatchUnitsConstraint.
     * @return A textual representation of the NumberDispatchUnitsConstraint.
     */
    @Override
    public String toString() {
        return String.format("number of %s must be between %s and %s", this.getValidator().toString(), this.getMinimum(), this.getMaximum());
    }
}
