package projectswop20102011.domain.validators;

import projectswop20102011.domain.MapItem;
import projectswop20102011.domain.Unit;

/**
 * A validator that only validates units who are available for an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AvailableUnitsMapItemValidator implements MapItemValidator<Unit> {

	/**
	 * Validates a given mapitem if the given MapItem is an available unit.
	 * @param mapItem
	 *		The given MapItem to validate.
	 * @return True if the MapItem is a unit and can be assigned to an emergency, otherwise false.
	 */
	@Override
	public boolean isValid(MapItem mapItem) {
		return (mapItem instanceof Unit && ((Unit) mapItem).canBeAssigned());
	}
}
