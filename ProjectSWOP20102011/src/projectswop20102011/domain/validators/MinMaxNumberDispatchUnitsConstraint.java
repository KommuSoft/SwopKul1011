package projectswop20102011.domain.validators;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.utils.OrderedSet;

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
    private final ValidatorNumberator<? super Unit> validatornumberator;

    /**
     * Creates a MinMaxNumberDispatchUnitsConstraint with the given units and number.
     * @param validatornumberator
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
    public MinMaxNumberDispatchUnitsConstraint(ValidatorNumberator<? super Unit> validatornumberator, long minimum, long maximum) throws NumberOutOfBoundsException, InvalidValidatorException {
        if (!areValidMinimumMaximum(minimum,maximum)) {
            throw new NumberOutOfBoundsException("The numbers need to be larger or equal to zero, and the maximum must be larger or equal to the minumum.");
        }
        if (!isValidValidator(validatornumberator)) {
            throw new InvalidValidatorException("UnitValidator must be effective.");
        }
        this.minimum = minimum;
        this.maximum = maximum;

        this.validatornumberator = validatornumberator;
    }

    /**
     * Returns the validator that specifies what will be counted.
     * @return the validator that specifies what will be counted.
     */
    public ValidatorNumberator<? super Unit> getValidator() {
        return validatornumberator;
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

    public static boolean areValidMinimumMaximum (long minimum, long maximum) {
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
    public boolean generateProposal(List<Unit> finishedOrAssignedUnits, OrderedSet<Unit> availableUnits, Set<Unit> proposal) {
        long value = countValidUnits(finishedOrAssignedUnits);
        if (value >= this.getMinimum()) {
            return true;
        }
        for (Unit u : availableUnits) {
            if (this.getValidator().isValid(u)) {
                long unumber = this.getValidator().getNumber(u);
                if(value+unumber <= this.getMaximum()) {
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
    protected boolean canAssign(List<Unit> finishedOrAssignedUnits, OrderedSet<Unit> toAssignUnits, Set<Unit> relevantUnits) {
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

    public boolean canAssign(List<Unit> finishedOrAssignedUnits, Set<Unit> toAssignUnits) {
        //TODO: implement
        throw new UnsupportedOperationException("Not supported yet.MMNDUC1");
    }

    @Override
    public boolean canFinish(List<Unit> finishedUnits) {
        //TODO: implement
        throw new UnsupportedOperationException("Not supported yet.MMNDUC2");
    }

	@Override
	public boolean areValidDispatchUnits(List<Unit> units, Set<Integer> relevantUnitIndices) {
		throw new UnsupportedOperationException("Not supported yet.MMNDUC3");
	}
    /*
    public boolean areValidDispatchUnits (List<Unit> units, Set<Integer> relevantIndices) {
    long needed = this.getNumber();
    if(needed <= 0) {
    return true;
    }

    long n = 0;
    UnitValidator uv = this.getValidator();
    Unit u;
    for(int i = 0; i < units.size(); i++) {
    u = units.get(i);
    if(uv.isValid(u)) {
    relevantIndices.add(i);
    if(++n >= needed) {
    return true;
    }
    }
    }
    return false;
    }
     */
}
