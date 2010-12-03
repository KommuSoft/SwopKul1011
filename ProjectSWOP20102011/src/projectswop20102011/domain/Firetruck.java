package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;

/**
 * A class that represents a firetruck.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Firetruck extends Unit {

    /**
     * The maximimal firesize the firetruck can handle.
     */
    private FireSize maxSize;

    /**
     * Initialize a new firetruck with given parameters.
     *
     * @param name
     *		The name of the new firetruck.
     * @param homeLocation
     *		The home location of the new firetruck.
     * @param speed
     *		The speed of the new firetruck.
     * @effect The new firetruck is a firetruck with given name, home location, speed,
     *			current location, destination and assigned indicator.
     *         |super(name,homeLocation,speed,currentLocation,destination,assigned);
     * @throws InvalidLocationException
     *		If the given location is an invalid location for a firetruck.
     * @throws InvalidMapItemNameException
     *		If the given name is an invalid name for a firetruck.
     * @throws InvalidSpeedException
     *		If the given speed is an invalid speed for a firetruck.
     */
    public Firetruck(String name, GPSCoordinate homeLocation, long speed) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException {
        super(name, homeLocation, speed);
    }

    /**
     * Returns the maximum firesize the firetruck can handle.
     * @return the maximum firesize the firetruck can handle.
     */
    FireSize getMaxSize() {
        return this.maxSize;
    }

    /**
     * Checks if the given fire size can be handled by the firetruck.
     * @param fireSize
     * @return
     */
    public boolean canHandleFireSize (FireSize fireSize) {
        return this.maxSize.compareTo(fireSize) >= 0;
    }

}