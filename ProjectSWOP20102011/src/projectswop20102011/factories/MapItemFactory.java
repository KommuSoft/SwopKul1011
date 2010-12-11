package projectswop20102011.factories;

import java.security.InvalidParameterException;
import projectswop20102011.domain.MapItem;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents an UnitBuildingFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class MapItemFactory{

	private final String unitBuildingTypeName;

	/**
	 * Creates a new UnitBuildingFactory with a given emergency type name.
	 * @param unitBuildingTypeName
	 *		The name of the type of UnitBuilding the factory will create.
	 * @throws InvalidUnitBuildingTypeNameException
	 *		If the name of the emergency type is invalid.
	 */
	protected MapItemFactory (String unitBuildingTypeName) throws InvalidMapItemTypeNameException {
		if(isValidUnitBuildingTypeName(unitBuildingTypeName)) {
			throw new InvalidMapItemTypeNameException("emergencyTypeName must be effective and not empty");
		}
		this.unitBuildingTypeName = unitBuildingTypeName;
	}

	/**
	 * Creates a new specific UnitBuilding with the given parameters
	 * @param parameters
	 *		The parameters of the constructor.
	 * @return The UnitBuilding created by the factory.
	 * @throws InvalidParameterException
	 *		If the given parameters are invalid parameters for the UnitBuilding.
	 */
	public abstract MapItem createMapItem (Object[] parameters) throws InvalidParameterException;

	/**
	 * Tests if the given emergency type name is valid for an UnitBuildingFactory object.
	 * @param unitBuildingTypeName
	 *		The given unit building type name to test.
	 * @return True if the given name is effective and not empty, otherwise false.
	 */
	public static boolean isValidUnitBuildingTypeName (String unitBuildingTypeName) {
		return (unitBuildingTypeName != null && unitBuildingTypeName.length() > 0);
	}

}