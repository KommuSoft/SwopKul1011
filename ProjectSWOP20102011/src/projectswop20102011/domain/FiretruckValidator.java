package projectswop20102011.domain;

import java.io.InvalidClassException;

/**
 * A class that represents a validator that checks if a given firetruck is capable to extinguish a given fire size.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FiretruckValidator extends TypeUnitValidator {

    /**
     * A variable registering the fire size of this fire truck validator.
     */
    private FireSize fireSize;

    /**
     * Creates a new firetruck validator with the given fire size.
     * @param fireSize
     *		The fire size to validate with.
     * @effect This fire size is equal to the given fire size.
     *		|this.fireSize.equals(fireSize)
     */
    FiretruckValidator(FireSize fireSize) throws InvalidClassException {
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
        if (!super.isValid(unit)) {
            return false;
        }
        Firetruck firetruck = (Firetruck) unit;
        return firetruck.canHandleFireSize(this.getFireSize());
    }
}
