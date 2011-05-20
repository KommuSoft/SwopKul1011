package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;

/**
 * A class that represents a hospital.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar The location of a hospital is always valid.
 *		| isValidLocation(getLocation())
 * @invar The name of a hospital is always valid.
 *		| isValidName(getName())
 */
public class Hospital extends MapItem {

	/**
	 * Initialize a new hospital with given parameters.
	 *
	 * @param name
	 *		The name of the new hospital.
	 * @param homeLocation
	 *		The home location of the new hospital.
	 * @effect The new hospital is a hospital with given name and home home location.
	 *         |super(name,homeLocation)
	 * @throws InvalidMapItemNameException
	 *		If the given name is an invalid name for this hospital.
	 * @throws InvalidLocationException
	 *		If the given home location is an invalid home location for this hospital.
	 */
	public Hospital(String name, GPSCoordinate homeLocation) throws InvalidMapItemNameException, InvalidLocationException {
		super(name, homeLocation);
	}
}
