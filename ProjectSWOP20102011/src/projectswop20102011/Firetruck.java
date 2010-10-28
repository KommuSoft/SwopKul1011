package projectswop20102011;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitNameException;

/**
 * A class that represents a firetruck.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Firetruck extends Unit{

	/**
	 * Initialize a new firetruck with given parameters.
	 *
	 * @param name
	 *		The name of the new firetruck.
	 * @param assigned
	 *		The assigned indicator of the new firetruck.
	 * @param currentLocation
	 *		The current location of the new firetruck.
	 * @param homeLocation
	 *		The home location of the new firetruck.
	 * @effect the new firetruck is a unit with given name, assigned indicator,
	 *			current location and home location.
	 *         |super(name,assigned,currentLocation,homeLocation)
	 * @throws InvalidUnitNameException
	 *		If the given name is an invalid name for a firetruck.
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a firetruck.
	 */
	public Firetruck(String name,boolean assigned,GPSCoordinate currentLocation, GPSCoordinate homeLocation) throws InvalidUnitNameException, InvalidLocationException{
		super(name,assigned,currentLocation,homeLocation);
	}
}
