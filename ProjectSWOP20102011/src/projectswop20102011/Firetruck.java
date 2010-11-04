package projectswop20102011;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

/**
 * A class that represents a firetruck.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Firetruck extends Unit {

    /**
     * Initialize a new firetruck with given parameters.
     *
     * @param name
     *		The name of the new firetruck.
     * @param homeLocation
     *		The home location of the new firetruck.
     * @param speed
     *		The speed of the new firetruck.
     * @param currentLocation
     *		The current location of the new firetruck.
     * @param destination
     *		The destination of the new firetruck.
     * @param assigned
     *		The assigned indicator of the new firetruck.
     * @effect The new firetruck is a firetruck with given name, home location, speed,
     *			current location, destination and assigned indicator.
     *         |super(name,homeLocation,speed,currentLocation,destination,assigned);
     * @throws InvalidLocationException
     *		If the given location is an invalid location for a firetruck.
     * @throws InvalidUnitBuildingNameException
     *		If the given name is an invalid name for a firetruck.
     * @throws InvalidSpeedException
     *		If the given speed is an invalid speed for a firetruck.
     */
    public Firetruck(String name, GPSCoordinate homeLocation, long speed, GPSCoordinate currentLocation, GPSCoordinate destination, boolean assigned) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        super(name, homeLocation, speed, currentLocation, destination, assigned);
    }

    /**
     * Initialize a new firetruck who is at the home location and isn't assigned.
     *
     * @param name
     *		The name of the new firetruck.
     * @param homeLocation
     *		The home location of the new firetruck.
     * @param speed
     *		The speed of the new firetruck.
     * @effect The new firetruck is a unit with given name, home location and speed,
     *         |super(name,homeLocation,speed,homeLocation,null,false);
     * @throws InvalidUnitBuildingNameException
     *		If the given name is an invalid name for a firetruck.
     * @throws InvalidLocationException
     *		If the given location is an invalid location for a firetruck.
     * @throws InvalidSpeedException
     *		If the given speed is an invalid speed for a firetruck.
     */
    public Firetruck(String name, GPSCoordinate homeLocation, long speed) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        super(name, homeLocation, speed, homeLocation, null, false);
    }
}
