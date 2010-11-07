package projectswop20102011;

import java.util.ArrayList;

/**
 * An implementation for a UnitBuildingEvaluationCriterium that checks if the object is equal to a given type.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TypeUnitBuildingEvaluationCriterium extends UnitBuildingEvaluationCriterium {

    /**
     * The class of UnitBuildings that will be accepted
     */
    private final Class type;

    /**
     * Creates a new instance of a TypeUnitBuildingEvaluationCriterium with a given type to check on.
     * @param type
     *		The type that must be equal to the validating UnitBuilding.
     * @post This type is equal to the parameter type | type == getType()
     */
    public TypeUnitBuildingEvaluationCriterium(Class type) {
        this.type = type;
    }

    /**
     * Validates a given UnitBuilding on type.
     * @param unitBuilding
	 *		The UnitBuilding to check if this is a valid UnitBuilding.
     * @return True if the status of the UnitBuilding equals this type, otherwise false.
     */
    @Override
    public boolean isValidUnitBuilding(UnitBuilding unitBuilding) {
		return type.isAssignableFrom(unitBuilding.getClass());
    }

    /**
     * Returns the type to check a UnitBuilding on.
     * @return The type that must be equal to the type of the UnitBuilding to validate that UnitBuilding.
     */
    public Class getType() {
        return type;
    }
}
