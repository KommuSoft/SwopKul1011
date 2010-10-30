package projectswop20102011;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

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
	 * @param homeLocation
	 *		The home location of the new ambulance.
	 * @param speed
	 *		The speed of the new ambulance.
	 * @param currentLocation
	 *		The current location of the new ambulance.
	 * @param destination
	 *		The destination of the new ambulance.
	 * @param assigned
	 *		The assigned indicator of the new ambulance.
	 * @effect The new ambulance is an ambulance with given name, home location, speed,
	 *			current location, destination and assigned indicator.
	 *         |super(name,homeLocation,speed,currentLocation,destination,assigned);
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for an ambulance.
	 * @throws InvalidUnitBuildingNameException
	 *		If the given name is an invalid name for an ambulance.
	 * @throws InvalidSpeedException
	 *		If the given speed is an invalid speed for an ambulance.
	 */
	public Ambulance(String name,GPSCoordinate homeLocation, long speed, GPSCoordinate currentLocation, GPSCoordinate destination, boolean assigned) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
		super(name,homeLocation,speed,currentLocation,destination,assigned);
	}

	/**
	 * Initialize a new ambulance who is at the home location and isn't assigned.
	 *
	 * @param name
	 *		The name of the new ambulance.
	 * @param homeLocation
	 *		The home location of the new ambulance.
	 * @param speed
	 *		The speed of the new ambulance.
	 * @effect The new ambulance is an ambulance with given name, home location and speed,
	 *         |this(name,homeLocation,speed,homeLocation,null,false);
	 * @throws InvalidUnitBuildingNameException
	 *		If the given name is an invalid name for an ambulance.
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for an ambulance.
	 * @throws InvalidSpeedException
	 *		If the given speed is an invalid speed for an ambulance.
	 */
	public Ambulance(String name, GPSCoordinate homeLocation, long speed) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		this(name,homeLocation,speed,homeLocation,null,false);
	}
}