package projectswop20102011.domain.validators;

import java.util.ArrayList;
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
     * A validator to validate Units and to return a number for each unit.
     */
    private final ValidatorNumberator<? super Unit> validatornumberator;

    /**
     * An optional additional constraint to test a unit to be assigned againt the units that are already assigned (or proposed to).
     */
    private final QuadraticValidator<Unit,Unit> quadraticValidator;


    /**
     * Creates a MinMaxNumberDispatchUnitsConstraint with the given units and number.
     * @param validatorNumberator
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
     *		If the given minimum and maxumum bounds are invalid.
     * @throws InvalidValidatorException
     *		If the given UnitValidator is invalid.
     * @note The quadraticValidator will be set to a new DefaultQuadraticValidator where any item is valid.
     */
    public MinMaxNumberDispatchUnitsConstraint(ValidatorNumberator<? super Unit> validatorNumberator, long minimum, long maximum) throws NumberOutOfBoundsException, InvalidValidatorException {
        this(validatorNumberator,minimum,maximum,new DefaultQuadraticValidator<Unit,Unit>());
    }
    /**
     * Creates a MinMaxNumberDispatchUnitsConstraint with the given units and number.
     * @param validatorNumberator
     *		The validator that specifies what will be counted.
     * @param minimum
     *		The desired minimum number of units.
     * @param maximum
     *		The desired minimum number of units.
     * @param quadraticValidator
     *          An optional additional constraint to test a unit to be assigned againt the units that are already assigned (or proposed to).
     * @post This minimum is equal to the given minimum.
     *		|this.minimum.equals(minimum)
     * @post This maximum is equal to the given maximum.
     *		|this.maximum.equals(maximum)
     * @post This validator is equal to the given validator.
     *		|this.validator.equals(validator)
     * @throws NumberOutOfBoundsException
     *		If the given minimum and maxumum bounds are invalid.
     * @throws InvalidValidatorException
     *		If the given UnitValidator is invalid.
     * @see MinMaxNumberDispatchUnitsConstraint#MinMaxNumberDispatchUnitsConstraint(projectswop20102011.domain.validators.ValidatorNumberator, long, long)
     */
    public MinMaxNumberDispatchUnitsConstraint(ValidatorNumberator<? super Unit> validatorNumberator, long minimum, long maximum, QuadraticValidator<Unit,Unit> quadraticValidator) throws NumberOutOfBoundsException, InvalidValidatorException {
        if (!areValidMinimumMaximum(minimum, maximum)) {
            throw new NumberOutOfBoundsException("The numbers need to be larger or equal to zero, and the maximum must be larger or equal to the minumum.");
        }
        if (!isValidValidator(validatorNumberator)) {
            throw new InvalidValidatorException("UnitValidator must be effective.");
        }
        this.minimum = minimum;
        this.maximum = maximum;
        this.validatornumberator = validatorNumberator;
        this.quadraticValidator = quadraticValidator;
    }

    /**
     * Returns the validator that specifies what will be counted.
     * @return the validator that specifies what will be counted.
     */
    public ValidatorNumberator<? super Unit> getValidator() {
        return validatornumberator;
    }

    /**
     * Returns the quadratic validator of this Constraint.
     * @return The quadratic validator of this Constraint.
     */
    public QuadraticValidator<Unit,Unit> getQuadraticValidator () {
        return this.quadraticValidator;
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
     * @param validatornumberator
     *		The validator to validate.
     * @return True if the given validator is effective.
     */
    public static boolean isValidValidator(ValidatorNumberator<? super Unit> validatornumberator) {
        return (validatornumberator != null);
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

    public static boolean areValidMinimumMaximum(long minimum, long maximum) {
        return (isValidNumber(minimum) && isValidNumber(maximum) && (minimum <= maximum));
    }

    //TODO: beter verplaatsen naar een Util?
    private int collectValidUnits(Collection<Unit> source, Collection<Unit> collected) {
        int count = 0;
        for (Unit u : source) {
            if (this.getValidator().isValid(u)) {
                collected.add(u);
                count += this.getValidator().getNumber(u);
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
        ArrayList<Unit> collect = new ArrayList<Unit>();
        long value = collectValidUnits(finishedOrAssignedUnits,collect);
        if (value >= this.getMinimum()) {
            return true;
        }
        for (Unit u : availableUnits) {
            if (this.getValidator().isValid(u) && this.getQuadraticValidator().isValid(collect, u)) {
                long unumber = this.getValidator().getNumber(u);
                if (value + unumber <= this.getMaximum()) {
                    collect.add(u);
                    proposal.add(u);
                    value += unumber;
                    if (value >= this.getMinimum()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected boolean canAssign(List<Unit> finishedOrAssignedUnits, Set<Unit> toAssignUnits, Set<Unit> relevantUnits) {
        ArrayList<Unit> collect = new ArrayList<Unit>();
        long counter = collectValidUnits(finishedOrAssignedUnits,collect);
        if (counter >= this.getMaximum()) {
            return true;
        }
        for (Unit u : toAssignUnits) {
            if (this.getValidator().isValid(u) && this.getQuadraticValidator().isValid(collect, u)) {
                counter++;
                collect.add(u);
                relevantUnits.add(u);
                if (counter >= this.getMaximum()) {
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public boolean canFinish(List<Unit> finishedUnits) {
        return (countValidUnits(finishedUnits) >= this.getMinimum());
    }
}
