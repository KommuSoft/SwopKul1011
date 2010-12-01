package projectswop20102011.domain;

/**
 * An implementation for a MapItemEvaluationCriterium that checks if the object is equal to a given type.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TypeUnitBuildingEvaluationCriterium extends MapItemEvaluationCriterium {

	/**
	 * The class of UnitBuildings that will be accepted
	 */
	private final Class type;

	/**
	 * Creates a new instance of a TypeUnitBuildingEvaluationCriterium with a given type to check on.
	 * @param type
	 *		The type that must be equal to the validating MapItem.
	 * @post This type is equal to the parameter type
	 *		| type == getType()
	 */
	public TypeUnitBuildingEvaluationCriterium(Class type) {
		this.type = type;
	}

	/**
	 * Validates a given MapItem on type.
	 * @param unitBuilding
	 *		The MapItem to check if this is a valid MapItem.
	 * @return True if the status of the MapItem equals this type, otherwise false.
	 */
	@Override
	public boolean isValidUnitBuilding(MapItem unitBuilding) {
		return getType().isAssignableFrom(unitBuilding.getClass());
	}

	/**
	 * Returns the type to check a MapItem on.
	 * @return The type that must be equal to the type of the MapItem to validate that MapItem.
	 */
	public Class getType() {
		return type;
	}
}
