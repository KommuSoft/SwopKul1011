package projectswop20102011.domain;

/**
 * An implementation for a MapItemEvaluationCriterium that checks if the object is equal to a given type.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TypeMapItemEvaluationCriterium extends MapItemEvaluationCriterium {

	/**
	 * The class of mapitems that will be accepted
	 */
	private final Class type;

	/**
	 * Creates a new instance of a TypeMapItemEvaluationCriterium with a given type to check on.
	 * @param type
	 *		The type that must be equal to the validating MapItem.
	 * @post This type is equal to the parameter type
	 *		| type == getType()
	 */
	public TypeMapItemEvaluationCriterium(Class type) {
		this.type = type;
	}

	/**
	 * Validates a given MapItem on type.
	 * @param mapItem
	 *		The MapItem to check if this is a valid MapItem.
	 * @return True if the status of the MapItem equals this type, otherwise false.
	 */
	@Override
	public boolean isValidMapItem(MapItem mapItem) {
		return getType().isAssignableFrom(mapItem.getClass());
	}

	/**
	 * Returns the type to check a MapItem on.
	 * @return The type that must be equal to the type of the MapItem to validate that MapItem.
	 */
	public Class getType() {
		return type;
	}
}
