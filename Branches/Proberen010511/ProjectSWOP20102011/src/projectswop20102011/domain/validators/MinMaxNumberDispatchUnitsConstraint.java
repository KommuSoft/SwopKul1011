package projectswop20102011.domain.validators;

import java.util.List;
import java.util.Set;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
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
     * @throws InvalidUnitValidatorException
	 *		If the given UnitValidator is invalid.
     */
	public MinMaxNumberDispatchUnitsConstraint(UnitValidator validator, long minimum, long maximum) throws NumberOutOfBoundsException, InvalidUnitValidatorException {
		if (!isValidNumber(minimum) || !isValidNumber(maximum) || (maximum < minimum)) {
			throw new NumberOutOfBoundsException("The numbers need to be larger or equal to zero.");
		}
		if (!isValidValidator(validator)) {
			throw new InvalidUnitValidatorException("UnitValidator must be effective.");
		}
		this.minimum = minimum;
		this.maximum = maximum;

		this.validator = validator;
	}

    /**
     * Returns the validator that specifies what will be counted.
     * @return the validator that specifies what will be counted.
     */
	public UnitValidator getValidator(){
		return validator;
	}

    /**
     * Returns the minimum desired number of units.
     * @return The minimum desired number of units.
     */
	public long getMinimum(){
		return minimum;
	}

    /**
     * Returns the maximum desired number of units.
     * @return The maximum desired number of units.
     */
	public long getMaximum(){
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

	@Override
	public boolean areValidDispatchUnits(List<Unit> units, Set<Integer> relevantUnitIndices) {
		final long minimumNeeded = getMinimum();
		final long maximumNeeded = getMaximum();

		if(minimumNeeded <= 0){
			return true;
		}

		long n = 0;
		UnitValidator uv = this.getValidator();
		Unit u;
		for(int i=0;i<units.size();i++){
			u = units.get(i);
			if(uv.isValid(u)){
				relevantUnitIndices.add(i);
				if(++n >= minimumNeeded){
					return true;
				}
			}
		}
		return false;
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
