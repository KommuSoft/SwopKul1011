package projectswop20102011.domain.validators;

import java.io.InvalidClassException;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.Unit;

/**
 * A class that represents a validator that checks if a given firetruck is capable to extinguish a given fire size.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FiretruckFireSizeValidator extends TypeUnitValidator {

	/**
	 * A variable registering the fire size of this fire truck validator.
	 */
	private FireSize fireSize;

	/**
	 * Creates a new firetruck validator with the given fire size.
	 * @param fireSize
	 *		The fire size to validate with.
	 * @effect This FiretruckFireSizeValidator is initialised with the class Firetruck
	 *		|super(Firetruck.class)
	 * @effect This fire size is equal to the given fire size.
	 *		|this.fireSize.equals(fireSize)
	 */
	public FiretruckFireSizeValidator(FireSize fireSize) throws InvalidClassException {
		super(Firetruck.class);
		setFireSize(fireSize);
	}

	/**
	 * Returns the fire size of this firetruck validator.
	 * @return The fire size of this firetruck validator.
	 */
	public FireSize getFireSize() {
		return fireSize;
	}

	/**
	 * Sets the fire size to the given fire size.
	 * @param fireSize
	 *		The new fire size of thsi firetruck validator.
	 * @post This fire size is equal to the given fire size.
	 *		|new.fireSize.equals(fireSize)
	 */
	private void setFireSize(FireSize fireSize) {
		this.fireSize = fireSize;
	}

	/**
	 * Checks if the given firetruck is valid for the fire size.
	 * @param unit
	 *		The element to check.
	 * @return True is the given unit is valid; false otherwise.
	 */
	@Override
	public boolean isValid(Unit unit) {
		return (super.isValid(unit) && ((Firetruck) unit).canHandleFireSize(this.getFireSize()));
	}

	/**
	 * Returns a textual representation of the FiretruckFireSizeValidator.
	 * @return a textual representation of the FiretruckFireSizeValidator.
	 */
	@Override
	public String toString() {
		//TODO: klopt ook niet meer
		return String.format("Firetrucks who can handle a %s fire", this.getFireSize());
	}
}
