package projectswop20102011.domain.validators;

import java.io.InvalidClassException;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.Unit;

/**
 * A unit constraint the succeeds when the Unit is a firetruck, but returns as number, the capacity of the firetruck.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class FiretruckWaterUnitValidator extends TypeUnitValidator {

    /**
     * Creates a new FiretruckWaterUnitValidator.
     * @throws InvalidClassException The exception will never be thrown.
     */
    public FiretruckWaterUnitValidator() throws InvalidClassException {
        super(Firetruck.class);
    }

    /**
     * Modified version of the getNumber method: instead of returning one, it will return the capacity of the firetruck.
     * @param unit The Unit to get the number from.
     * @return The capacity of the firetruck if the unit is a firetruck, otherwise zero.
     */
    @Override
    public long getNumber(Unit unit) {
        if (this.isValid(unit)) {
            return ((Firetruck) unit).getCapacity();
        } else {
            return 0;
        }
    }
}
