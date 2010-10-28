package projectswop20102011;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitNameException;

/**
 * A class that represents an ambulance.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Ambulance extends Unit{

	/**
	 * Initialize a new ambulance with given parameters.
	 *
	 * @param name
	 *		The name of the new ambulance.
	 * @param assigned
	 *		The assigned indicator of the new ambulance.
	 * @param currentLocation
	 *		The current location of the new ambulance.
	 * @param homeLocation
	 *		The home location of the new ambulance.
	 * @effect the new ambulance is a unit with given name, assigned indicator,
	 *			current location and home location.
	 *         |super(name,assigned,currentLocation,homeLocation)
	 * @throws InvalidUnitNameException
	 *		If the given name is an invalid name for a ambulance.
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a ambulance.
	 */
	public Ambulance(String name,boolean assigned,GPSCoordinate currentLocation, GPSCoordinate homeLocation) throws InvalidUnitNameException, InvalidLocationException{
		super(name,assigned,currentLocation,homeLocation);
	}
}