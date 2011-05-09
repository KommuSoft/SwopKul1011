package projectswop20102011.domain.validators;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.utils.OrderedSet;

public class DifferentUnitsDispatchUnitsConstraint extends DispatchUnitsConstraint {

	/**
	 * A variable registering the desired number of units.
	 */
	private final long number;
	/**
	 * A variable registering the validator that specifies what will be counted.
	 */
	private final UnitValidator validator;

	/**
	 * Creates a DifferentUnitsDispatchUnitsConstraint with the given units and number.
	 * @param validator
	 *		The validator that specifies what will be counted.
	 * @param number
	 *		The desired number of units.
	 * @post This number is equal to the given number.
	 *		|this.number.equals(number)
	 * @post This validator is equal to the given validator.
	 *		|this.validator.equals(validator)
	 * @throws NumberOutOfBoundsException
	 *		If the given number is invalid.
	 * @throws InvalidValidatorException
	 *		If the given UnitValidator is invalid.
	 */
	public DifferentUnitsDispatchUnitsConstraint(UnitValidator validator, long number) throws NumberOutOfBoundsException, InvalidValidatorException {
		if (!isValidNumber(number)) {
			throw new NumberOutOfBoundsException("The number needs to be larger or equal to zero.");
		}
		if (!isValidValidator(validator)) {
			throw new InvalidValidatorException("UnitValidator must be effective.");
		}
		this.number = number;
		this.validator = validator;
	}

	/**
	 * Returns the desired number of units.
	 * @return The desired number of units.
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * Returns the validator that specifies what will be counted.
	 * @return the validator that specifies what will be counted.
	 */
	private UnitValidator getValidator() {
		return validator;
	}

	/**
	 * Tests if the given validator is a valid validator for a DifferentUnitsDispatchUnitsConstraint.
	 * @param validator
	 *		The validator to validate.
	 * @return True if the given validator is effective.
	 */
	public static boolean isValidValidator(UnitValidator validator) {
		return (validator != null);
	}

	/**
	 * Tests if the given number is a valid number for a DifferentUnitsDispatchUnitsConstraint.
	 * @param number
	 *		The number to validate.
	 * @return True if the given number is larger or equal to zero, false otherwise.
	 */
	public static boolean isValidNumber(long number) {
		return (number >= 0);
	}

	/**
	 * Tests if at least a specified number of units are validated by the specified validator.
	 * @param units
	 *		An iterable object containing only unique and only effective units.
	 * @param relevantIndices
	 *		A set where all the indices  of units that are relevant for this constraint are added to.
	 * @pre The given units parameter contains only unique (no duplicates) effective units.
	 * @return True if at least the specified number of units succeed on the specified validator, otherwise false.
	 */
	@Override
	public boolean areValidDispatchUnits(List<Unit> units, Set<Integer> relevantIndices) {
		long needed = this.getNumber();
		if (needed <= 0) {
			return true;
		}

		long n = 0;
		UnitValidator uv = this.getValidator();
		Unit u;
		for (int i = 0; i < units.size(); i++) {
			u = units.get(i);
			if (uv.isValid(u) && !duplicateInList(units, i)) {
				relevantIndices.add(i);
				if (++n >= needed) {
					return true;
				}
			}
		}

		return false;

	}

	private boolean duplicateInList(List<Unit> units, int n) {
		Unit u = units.get(n);
		for (int j = 0; j < n; j++) {
			if (units.get(j).equals(u)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a textual representation of the DifferentUnitsDispatchUnitsConstraint.
	 * @return A textual representation of the DifferentUnitsDispatchUnitsConstraint.
	 */
	@Override
	public String toString() {
		return String.format("number of %s must be %s", this.getValidator().toString(), this.getNumber());
	}

	public boolean generateProposal(List<Unit> finishedOrAssignedUnits, SortedSet<Unit> availableUnits, Set<Unit> proposal) {
		//TODO: implement
		throw new UnsupportedOperationException("Not supported yet.DUDUC1");
	}

	public boolean canAssign(List<Unit> finishedOrAssignedUnits, Set<Unit> toAssignUnits) {
		//TODO: implement
		throw new UnsupportedOperationException("Not supported yet.DUDUC2");
	}

	@Override
	public boolean canFinish(List<Unit> finishedUnits) {
		//TODO: implement
		throw new UnsupportedOperationException("Not supported yet.DUDUC3");
	}

	@Override
	public boolean generateProposal(List<Unit> finishedOrAssignedUnits, OrderedSet<Unit> availableUnits, Set<Unit> proposal) {
		throw new UnsupportedOperationException("Not supported yet.DUDUC4");
	}

	@Override
	protected boolean canAssign(List<Unit> finishedOrAssignedUnits, OrderedSet<Unit> toAssignUnits, Set<Unit> relevantUnits) {
		throw new UnsupportedOperationException("Not supported yet.DUDUC5");
	}
}
