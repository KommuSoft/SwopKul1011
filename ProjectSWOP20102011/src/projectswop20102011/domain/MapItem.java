package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

/**
 * A class that represents a unit or building.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public abstract class MapItem {

	/**
	 * A variable registering the name of this unit or building.
	 */
	private String name;
	/**
	 * A variable registering the name of this unit or building.
	 */
	private final GPSCoordinate homeLocation;

	/**
	 * Initialize a new unit or building with given parameters.
	 * 
	 * @param name
	 *		The name of the new unit or building.
	 * @param homeLocation
	 *		The home location of the new unit or building.
	 * @effect The new unit or building has the given name.
	 *		|setName(name)
	 * @post The home location of this unit or building is set according to the given home location.
	 *		|new.getHomeLocation() == homeLocation
	 * @throws InvalidUnitBuildingNameException
	 *		If the given name is an invalid name for a unit or building.
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a unit or building.

	 */
	MapItem(String name, GPSCoordinate homeLocation) throws InvalidUnitBuildingNameException, InvalidLocationException {
		setName(name);
		if (!isValidHomeLocation(homeLocation)) {
			throw new InvalidLocationException(String.format("\"%s\" is an invalid home location for a unit or building.", homeLocation));
		}
		this.homeLocation = homeLocation;
	}

	/**
	 * Returns the name of this unit or building.
	 * @return The name of this unit or building.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the home location of this unit or building.
	 * @return The home location of this unit or building.
	 */
	public GPSCoordinate getHomeLocation() {
		return homeLocation;
	}

	/**
	 * Sets the name of this unit or building to the given value.
	 *
	 * @param name
	 *		The new name of this unit or building.
	 * @throws InvalidUnitBuildingNameException
	 *		If the given name isn't a valid name for a unit or building.
	 * @post The name of this unit or building is set according to the given name.
	 *		|new.getName() == name
	 */
	private void setName(String name) throws InvalidUnitBuildingNameException {
		if (!isValidName(name)) {
			throw new InvalidUnitBuildingNameException(String.format("\"%s\" is an invalid name for a unit or building.", name));
		} else {
			this.name = name;
		}
	}

	/**
	 * Checks if the given name is a valid name for a unit or building.
	 *
	 * @param name
	 *		The name of a unit or building to test.
	 * @return True if the name is valid; false otherwise.
	 */
	public static boolean isValidName(String name) {
		return ((name != null) && (!name.equals("")));
	}

	/**
	 * Checks if the given home location is a valid home location for a unit or building.
	 *
	 * @param homeLocation
	 *		The home location of a unit or building to test.
	 * @return True if the home location is valid; false otherwise.
	 */
	public static boolean isValidHomeLocation(GPSCoordinate homeLocation) {
		return (homeLocation != null);
	}
}
