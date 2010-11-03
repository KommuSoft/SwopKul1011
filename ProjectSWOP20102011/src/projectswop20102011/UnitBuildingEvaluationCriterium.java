package projectswop20102011;

/**
 * A class representing an evaluation criterium on an UnitBuilding.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public abstract class UnitBuildingEvaluationCriterium{

    /**
     * The validation method for a certain UnitBuilding on the criterium.
     * @param unitBuilding
	 *		The UnitBuilding to validate.
     * @return true if the UnitBuilding is valid according to the criterium, otherwise false.
     */
    public abstract boolean isValidUnitBuilding (UnitBuilding unitBuilding);

}