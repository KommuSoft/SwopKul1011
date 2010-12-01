package projectswop20102011.domain;

/**
 * A class representing an evaluation criterium on an MapItem.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public abstract class MapItemEvaluationCriterium {

	/**
	 * The validation method for a certain MapItem on the criterium.
	 * @param unitBuilding
	 *		The MapItem to validate.
	 * @return True if the MapItem is valid according to the criterium, otherwise false.
	 */
	public abstract boolean isValidUnitBuilding(MapItem unitBuilding);
}
