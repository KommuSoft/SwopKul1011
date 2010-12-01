package projectswop20102011.domain;

/**
 * A validation criterium that only validates units who are available for an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AvailableUnitsMapItemEvaluationCriterium extends MapItemEvaluationCriterium {

	/**
	 * Validates a given unitBuilding if the given MapItem is an available unit.
	 * @param unitBuilding The given MapItem to validate.
	 * @return True if the MapItem is a unit and can be assigned to an emergency.
	 */
	@Override
	public boolean isValidUnitBuilding(MapItem unitBuilding) {
		return (unitBuilding instanceof Unit && ((Unit) unitBuilding).canBeAssigned());
	}
}
