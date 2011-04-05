package projectswop20102011.domain.validators;

import java.util.List;
import java.util.Set;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class NeededLitersDispatchUnitsConstraint extends DispatchUnitsConstraint{
	
	/**
	 * A variable registering the number of liters required.
	 */
	private final long numberOfLitersRequired;
	/**
	 * A variable registering the validator that specifies what will be counted.
	 */
	private final UnitValidator validator;

	/**
	 * Creates a NeededLitersDispatchUnitsConstraint with the given units and number of liters required.
	 * 
	 * @param validator
	 *            The validator that specifies what will be counted.
	 * @param numberOfLitersRequired
	 *            The desired number of liters.
	 * @post This number is equal to the given number.
	 *       |this.number.equals(number)
	 * @post This validator is equal to the given validator.
	 *       |this.validator.equals(validator)
	 * @throws NumberOutOfBoundsException
	 *             If the given number is invalid.
	 * @throws InvalidUnitValidatorException
	 *             If the given UnitValidator is invalid.
	 */
	public NeededLitersDispatchUnitsConstraint(UnitValidator validator, long numberOfLitersRequired) throws NumberOutOfBoundsException, InvalidUnitValidatorException {
		if (!isValidNumberOfLitersRequired(numberOfLitersRequired)) {
			throw new NumberOutOfBoundsException(
					"The number of liters required needs to be larger than zero.");
		}
		if (!isValidValidator(validator)) {
			throw new InvalidUnitValidatorException(
					"UnitValidator must be effective.");
		}
		this.numberOfLitersRequired = numberOfLitersRequired;
		this.validator = validator;
	}

	/**
	 * Returns the desired number of liters.
	 * 
	 * @return The desired number of liters.
	 */
	public long getNumberOfLitersRequired() {
		return numberOfLitersRequired;
	}

	/**
	 * Returns the validator that specifies what will be counted.
	 * 
	 * @return the validator that specifies what will be counted.
	 */
	private UnitValidator getValidator() {
		return validator;
	}

	/**
	 * Tests if the given validator is a valid validator for a
	 * NeededLitersDispatchUnitsConstraint.
	 * 
	 * @param validator
	 *            The validator to validate.
	 * @return True if the given validator is effective.
	 */
	public static boolean isValidValidator(UnitValidator validator) {
		return (validator != null);
	}

	/**
	 * Tests if the given number is a valid number for a
	 * NeededLitersDispatchUnitsConstraint.
	 * 
	 * @param number
	 *            The number to validate.
	 * @return True if the given number is larger or equal to zero, false
	 *         otherwise.
	 */
	public static boolean isValidNumberOfLitersRequired(long numberOfLitersRequired) {
		return (numberOfLitersRequired > 0);
	}

	/**
	 * Tests if at least a specified number of units are validated by the
	 * specified validator.
	 * 
	 * @param units
	 *            An iterable object containing only unique and only effective
	 *            units.
	 * @param relevantIndices
	 *            A set where all the indices of units that are relevant for
	 *            this constraint are added to.
	 * @pre The given units parameter contains only unique (no duplicates)
	 *      effective units.
	 * @return True if at least the specified number of units succeed on the
	 *         specified validator, otherwise false.
	 */
	@Override
	public boolean areValidDispatchUnits(List<Unit> units, Set<Integer> relevantIndices) {
		long needed = this.getNumberOfLitersRequired();
		//TODO: Dit moet dan nog eens gecontroleerd worden
		if (needed <= 0) {
			return true;
		}

		long n = 0;
		UnitValidator uv = this.getValidator();
		Unit u;
		for (int i = 0; i < units.size(); i++) {
			u = units.get(i);
			if (uv.isValid(u)) {
				relevantIndices.add(i);
				n += ((Firetruck) u).getCapacity();
				if (n >= needed) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a textual representation of the CapacityDispatchUnitsConstraint.
	 * 
	 * @return A textual representation of the CapacityDispatchUnitsConstraint.
	 */
	@Override
	public String toString() {
		return String.format("The number of liters required of %s must be %s", this.getValidator()
				.toString(), this.getNumberOfLitersRequired());
	}
}
