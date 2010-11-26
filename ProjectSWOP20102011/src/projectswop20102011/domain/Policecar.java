package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

/**
 * A class that represents a policecar.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Policecar extends Unit {

	/**
	 * Initialize a new policecar with given parameters.
	 *
	 * @param name
	 *		The name of the new policecar.
	 * @param homeLocation
	 *		The home location of the new policecar.
	 * @param speed
	 *		The speed of the new policecar.
	 * @effect The new policecar is a unit with given name, home location, speed.
	 *         | super(name,homeLocation,speed);
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a policecar.
	 * @throws InvalidUnitBuildingNameException
	 *		If the given name is an invalid name for a policecar.
	 * @throws InvalidSpeedException
	 *		If the given speed is an invalid speed for a policecar.
	 */
	public Policecar(String name, GPSCoordinate homeLocation, long speed) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
		super(name, homeLocation, speed);
	}
}
