package projectswop20102011;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitNameException;

/**
 * A class that represents a policecar.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Policecar extends Unit{

	/**
	 * Initialize a new policecar with given parameters.
	 *
	 * @param name
	 *		The name of the new policecar.
	 * @param assigned
	 *		The assigned indicator of the new policecar.
	 * @param currentLocation
	 *		The current location of the new policecar.
	 * @param homeLocation
	 *		The home location of the new policecar.
	 * @effect the new policecar is a unit with given name, assigned indicator,
	 *			current location and home location.
	 *         |super(name,assigned,currentLocation,homeLocation)
	 * @throws InvalidUnitNameException
	 *		If the given name is an invalid name for a policecar.
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a policecar.
	 */
	public Policecar(String name,boolean assigned,GPSCoordinate currentLocation, GPSCoordinate homeLocation) throws InvalidUnitNameException, InvalidLocationException{
		super(name,assigned,currentLocation,homeLocation);
	}

}
