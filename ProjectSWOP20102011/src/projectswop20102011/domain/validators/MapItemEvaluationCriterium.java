package projectswop20102011.domain.validators;

import projectswop20102011.domain.MapItem;

/**
 * A class representing an evaluation criterium on an MapItem.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public interface MapItemEvaluationCriterium {

	/**
	 * The validation method for a certain MapItem on the criterium.
	 * @param mapItem
	 *		The MapItem to validate.
	 * @return True if the MapItem is valid according to the criterium, otherwise false.
	 */
	public boolean isValidMapItem(MapItem mapItem);
}
