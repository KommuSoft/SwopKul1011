package projectswop20102011.domain.validators;

import projectswop20102011.domain.MapItem;
import projectswop20102011.domain.Unit;

/**
 * A validation criterium that only validates units who are available for an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AvailableUnitsMapItemEvaluationCriterium implements MapItemEvaluationCriterium {

	/**
	 * Validates a given mapitem if the given MapItem is an available unit.
	 * @param mapItem The given MapItem to validate.
	 * @return True if the MapItem is a unit and can be assigned to an emergency.
	 */
	@Override
	public boolean isValidMapItem(MapItem mapItem) {
		return (mapItem instanceof Unit && ((Unit) mapItem).canBeAssigned());
	}
}
