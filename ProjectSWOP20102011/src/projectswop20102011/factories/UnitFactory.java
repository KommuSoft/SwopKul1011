package projectswop20102011.factories;

import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents a unit factory.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class UnitFactory extends MapItemFactory {

	/**
     * Creates a new UnitFactory with a given unit type name.
     * @param unitTypeName
     *		The name of the type of unit the factory will create.
	 * @effect The new UnitFactory is a new MapItemFactory with a given type name.
	 *		|super(unitTypeName)
     * @throws InvalidMapItemTypeNameException
     *		If the name of the unit type is invalid.
     */
	public UnitFactory(String unitTypeName) throws InvalidMapItemTypeNameException {
		super(unitTypeName);
	}

	/**
	 * Creates a new Unit with the given parameters.
	 * @param parameters
	 *		The parameters of the constructor.
	 * @return The created unit.
	 * @throws Exception
	 *		If an error occurs
	 */
	@Override
	public abstract Unit createMapItem(Object[] parameters) throws Exception;

}
