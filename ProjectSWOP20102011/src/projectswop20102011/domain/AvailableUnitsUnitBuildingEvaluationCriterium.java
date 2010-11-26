package projectswop20102011.domain;

/**
 * A validation criterium that only validates units who are available for an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AvailableUnitsUnitBuildingEvaluationCriterium extends UnitBuildingEvaluationCriterium {

	/**
	 * Validates a given unitBuilding if the given UnitBuilding is an available unit.
	 * @param unitBuilding The given UnitBuilding to validate.
	 * @return True if the UnitBuilding is a unit and can be assigned to an emergency.
	 */
	@Override
	public boolean isValidUnitBuilding(UnitBuilding unitBuilding) {
		return (unitBuilding instanceof Unit && ((Unit) unitBuilding).canBeAssigned());
	}
}
