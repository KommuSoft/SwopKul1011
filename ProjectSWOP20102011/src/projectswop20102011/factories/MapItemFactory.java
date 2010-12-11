package projectswop20102011.factories;

import projectswop20102011.domain.MapItem;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents a MapItemFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class MapItemFactory{

	private final String mapItemTypeName;

	/**
	 * Creates a new MapItemFactory with a given emergency type name.
	 * @param MapItemTypeName
	 *		The name of the type of MapItem the factory will create.
     * @throws InvalidMapItemTypeNameException
	 *		If the name of the emergency type is invalid.
	 */
	protected MapItemFactory (String mapItemTypeName) throws InvalidMapItemTypeNameException {
		if(isValidMapItemTypeName(mapItemTypeName)) {
			throw new InvalidMapItemTypeNameException("emergencyTypeName must be effective and not empty");
		}
		this.mapItemTypeName = mapItemTypeName;
	}

	/**
	 * Creates a new specific MapItem with the given parameters
     * @param s
     *      A string with the specifications of the new mapItem.
     * @return The MapItem created by the factory.

	 */
	public abstract MapItem createMapItem (String s);

	/**
	 * Tests if the given emergency type name is valid for an MapItemFactory object.
	 * @param mapItemTypeName
	 *		The given unit building type name to test.
	 * @return True if the given name is effective and not empty, otherwise false.
	 */
	public static boolean isValidMapItemTypeName (String mapItemTypeName) {
		return (mapItemTypeName != null && mapItemTypeName.length() > 0);
	}

}