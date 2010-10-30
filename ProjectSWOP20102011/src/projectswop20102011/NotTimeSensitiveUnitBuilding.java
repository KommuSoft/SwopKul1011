package projectswop20102011;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

/**
 * A class that represents a unit or building that are not timesensitive.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public abstract class NotTimeSensitiveUnitBuilding extends UnitBuilding{
	
	/**
	 * Initialize a new not-timesensitive unit or building with given parameters.
	 *
	 * @param name
	 *		The name of the new not-timesensitive unit or building.
	 * @param homeLocation
	 *		The home location of the new not timesensitive unit or building.
	 * @effect The new not-timesensitive unit or building is a unit or building with given name and home location
	 *         |super(name,homeLocation);
	 * @throws InvalidUnitBuildingNameException
	 *		If the given name is an invalid name for this not-timesensitive unit or building
	 * @throws InvalidLocationException
	 *		If the given home location is an invalid home location for this not-timesensitive unit or building
	 */
	public NotTimeSensitiveUnitBuilding(String name,GPSCoordinate homeLocation) throws InvalidUnitBuildingNameException, InvalidLocationException{
		super(name,homeLocation);
	}
}
