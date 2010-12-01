package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;

/**
 * A class that represents a mapitem.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public abstract class MapItem {

	/**
	 * A variable registering the name of this mapitem.
	 */
	private String name;
	/**
	 * A variable registering the name of this mapitem.
	 */
	private final GPSCoordinate homeLocation;

	/**
	 * Initialize a new mapitem with given parameters.
	 * 
	 * @param name
	 *		The name of the new mapitem.
	 * @param homeLocation
	 *		The home location of the new mapitemg.
	 * @effect The new mapitem has the given name.
	 *		|setName(name)
	 * @post The home location of this mapitem is set according to the given home location.
	 *		|new.getHomeLocation() == homeLocation
	 * @throws InvalidMapItemNameException
	 *		If the given name is an invalid name for a mapitem.
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a mapitem.

	 */
	MapItem(String name, GPSCoordinate homeLocation) throws InvalidMapItemNameException, InvalidLocationException {
		setName(name);
		if (!isValidHomeLocation(homeLocation)) {
			throw new InvalidLocationException(String.format("\"%s\" is an invalid home location for a mapitem.", homeLocation));
		}
		this.homeLocation = homeLocation;
	}

	/**
	 * Returns the name of this mapitem.
	 * @return The name of this mapitem.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the home location of this mapitem.
	 * @return The home location of this mapitem.
	 */
	public GPSCoordinate getHomeLocation() {
		return homeLocation;
	}

	/**
	 * Sets the name of this mapitem to the given value.
	 *
	 * @param name
	 *		The new name of this mapitem.
	 * @throws InvalidMapItemNameException
	 *		If the given name isn't a valid name for a mapitem.
	 * @post The name of this mapitem is set according to the given name.
	 *		|new.getName() == name
	 */
	private void setName(String name) throws InvalidMapItemNameException {
		if (!isValidName(name)) {
			throw new InvalidMapItemNameException(String.format("\"%s\" is an invalid name for a mapitem.", name));
		} else {
			this.name = name;
		}
	}

	/**
	 * Checks if the given name is a valid name for a mapitem.
	 *
	 * @param name
	 *		The name of a mapitem to test.
	 * @return True if the name is valid; false otherwise.
	 */
	public static boolean isValidName(String name) {
		return ((name != null) && (!name.equals("")));
	}

	/**
	 * Checks if the given home location is a valid home location for a mapitem.
	 *
	 * @param homeLocation
	 *		The home location of a mapitem to test.
	 * @return True if the home location is valid; false otherwise.
	 */
	public static boolean isValidHomeLocation(GPSCoordinate homeLocation) {
		return (homeLocation != null);
	}
}
