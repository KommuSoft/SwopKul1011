package projectswop20102011.domain;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidFireSizeException;
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
     * The maximal firesize the firetruck can handle.
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
     * @effect The new firetruck can handle a house-fire size.
     *		|this.maxSize.equals(FireSize.HOUSE)
     * @throws InvalidLocationException
     *		If the given location is an invalid location for a firetruck.
     * @throws InvalidMapItemNameException
     *		If the given name is an invalid name for a firetruck.
     * @throws InvalidSpeedException
     *		If the given speed is an invalid speed for a firetruck.
     */
    public Firetruck(String name, GPSCoordinate homeLocation, long speed) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException {
        //TODO deze constructor is aangemaakt zodat de oude testen nog werken.
        super(name, homeLocation, speed);
        try {
            setMaxSize(FireSize.HOUSE);
        } catch (InvalidFireSizeException ex) {
            //we assume this can never happen
            Logger.getLogger(Firetruck.class.getName()).log(Level.SEVERE, null, ex);
        }
        setWithdrawBehavior(new NotWithdraw());
    }

    /**
     * Initialize a new firetruck with given parameters.
     *
     * @param name
     *		The name of the new firetruck.
     * @param homeLocation
     *		The home location of the new firetruck.
     * @param speed
     *		The speed of the new firetruck.
     * @param maxSize
     *		The maximum size that this firetruck can handle.
     * @effect The new firetruck is a firetruck with given name, home location, speed,
     *			current location, destination and assigned indicator.
     *         |super(name,homeLocation,speed,currentLocation,destination,assigned)
     * @effect The new firetruck can handle the given fire size.
     *		|this.maxSize.equals(maxSize)
     * @throws InvalidLocationException
     *		If the given location is an invalid location for a firetruck.
     * @throws InvalidMapItemNameException
     *		If the given name is an invalid name for a firetruck.
     * @throws InvalidSpeedException
     *		If the given speed is an invalid speed for a firetruck.
     * @throws InvalidFireSizeException
     *          If the given maximum fire size is not effective.
     */
    public Firetruck(String name, GPSCoordinate homeLocation, long speed, FireSize maxSize) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidFireSizeException {
        super(name, homeLocation, speed);
        setMaxSize(maxSize);
    }

    /**
     * Returns the maximum fire size the firetruck can handle.
     * @return the maximum fire size the firetruck can handle.
     */
    public FireSize getMaxSize() {
        return maxSize;
    }

    /**
     * Sets the maximum fire size to the given value.
     * @param maxSize
     *		The new maximum fire size of this firetruck.
     * @post This maximum fire size is equal to the given maximum size.
     *		|new.maxSize.equals(maxSize)
     * @throws InvalidFireSizeException
     *          If the given maximum fire size is not effective.
     */
    private void setMaxSize(FireSize maxSize) throws InvalidFireSizeException {
        if(!isValidMaxFireSize(maxSize)) {
            throw new InvalidFireSizeException("The given maximum fire size must be effective.");
        }
        this.maxSize = maxSize;
    }

    /**
     * Tests if the given maximum fire size is a valid maximum fire size for a firetruck.
     * @param maxFireSize The given maximum fire size.
     * @return True if the given maximum fire size is effective, otherwise false.
     */
    public static boolean isValidMaxFireSize (FireSize maxFireSize) {
        return (maxFireSize != null);
    }

    /**
     * Tests if this firetruck can handle a fire with a given fire size
     * @param fireSize The fire size of the fire to test.
     * @return True if the maximum fire size of this firetruck is larger or equal to the given fire size, otherwise false.
     */
    public boolean canHandleFireSize (FireSize fireSize) {
        return (this.getMaxSize().compareTo(fireSize) >= 0);
    }

}
