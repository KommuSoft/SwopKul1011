package projectswop20102011;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

/**
 * A class that represents a unit.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @invar The name of a unit is always valid.
 *		|isValidName(getName())
 * @invar The current location of a unit is always valid.
 *		|isValidCurrentLocation(getCurrentLocation())
 * @invar The home location of a unit is always valid.
 *		|isValidHomeLocation(getHomeLocation())
 */
public abstract class Unit extends TimeSensitiveUnitBuilding{

	/**
	 * A variable registering whether this unit is assigned.
	 */
	private boolean assigned;

	/**
	 * A variable registering the current location of this unit.
	 */
	private GPSCoordinate currentLocation;

	/**
	 * Initialize a new unit with given parameters.
	 *
	 * @param name
	 *		The name of the new unit.
	 * @param homeLocation
	 *		The home location of the new unit.
	 * @param speed
	 *		The speed of the new unit.
	 * @param assigned
	 *		The assigned indicator of the new unit.
	 * @param currentLocation
	 *		The current location of the new unit.
	 * @param destination
	 *		The destination of the new unit.
	 * @param assigned
	 *		The assigned indicator of the new unit.
	 * @effect The new unit is a unit with given name, home location, speed,
	 *			current location, destination and assigned indicator.
	 *         |super(name,homeLocation,speed,currentLocation,destination,assigned);
	 * @throws InvalidUnitBuildingNameException
	 *		If the given name is an invalid name for a unit.
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a unit.
	 * @throws InvalidSpeedException
	 *		If the given speed is an invalid speed for a unit.
	 */
	Unit(String name, GPSCoordinate homeLocation, long speed, GPSCoordinate currentLocation, GPSCoordinate destination, boolean assigned) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		super(name,homeLocation,speed,currentLocation,destination,assigned);
	}
}