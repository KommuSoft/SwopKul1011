package projectswop20102011.domain.validators;

import java.io.InvalidClassException;
import projectswop20102011.domain.Unit;

/**
 * A class representing a UnitValidator that validates if a given Unit is from a given type (example: Ambulance, Firetruck,...)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TypeUnitValidator implements UnitValidator {

    /**
     * A class where the units need to be an instance from.
     */
    private final Class<? extends Unit> unitClass;

    /**
     * Creates a new instance of a TypeUnitValidator object with a given unit type.
     * @param unitClass A class where the validating units need to be an instance from.
     * @throws InvalidClassException If the given unit class is invalid.
     */
    public TypeUnitValidator(Class<? extends Unit> unitClass) throws InvalidClassException {
        if (!isValidUnitClass(unitClass)) {
            throw new InvalidClassException("The unit class needs to be effective.");
        }
        this.unitClass = unitClass;
    }

    /**
     * Returns the type of class where a unit needs to be an instance from to statisfy this validator.
     * @return The type of class where a unit needs to be an instance from to statisfy this validator.
     */
    public Class<? extends Unit> getUnitClass() {
        return this.unitClass;
    }

    /**
     * Checks if the given unit statisfies this UnitValidator.
     * @param unit The unit to validate.
     * @return True if the given unit is a type that is the same or a subtype of the unitClass of this TypeUnitValidator, otherwise false.
     */
    @Override
    public boolean isValid(Unit unit) {
        return this.getUnitClass().isInstance(unit);
    }

    /**
     * Tests if the given UnitClass is a valid class for a TypeUnitValidator.
     * @param unitClass The unit class to validate.
     * @return True if the given unit class is effective, otherwise false.
     */
    public static boolean isValidUnitClass(Class<? extends Unit> unitClass) {
        return (unitClass != null);
    }

    /**
     * Returns a textual representation of the given UnitValidator.
     * @return A textual representation of the given UnitValidator.
     */
    @Override
    public String toString() {
        return String.format("%ss",this.getUnitClass().getSimpleName());
    }
}