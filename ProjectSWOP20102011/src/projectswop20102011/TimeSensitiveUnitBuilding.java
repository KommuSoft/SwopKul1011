package projectswop20102011;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

/**
 * A class that represents a unit or building that are timesensitive.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public abstract class TimeSensitiveUnitBuilding extends UnitBuilding{

	/**
	 * A variable registering the speed of this timesensitive unit or building
	 */
	private long speed;

	/**
	 * A variable registering the current location of this timesensitive unit or building
	 */
	private GPSCoordinate currentLocation;

	/**
	 * A variable registering the destination of this timesensitive unit or building
	 */
	private GPSCoordinate destination;

	/**
	 * A variable registering whether this unit is assigned.
	 */
	private boolean assigned;

	/**
	 * Initialize a new timesensitive unit or building with given parameters
	 * 
	 * @param name
	 *		The name of the new timesensitive unit or building.
	 * @param homeLocation
	 *		The home location of the new timesensitive unit or building.
	 * @param speed
	 *		The speed of the new timesensitive unit or building
	 * @param assigned
	 *		The assigned indicator of the new timesensitive unit or building
	 * @param currentLocation
	 *		The current location of the new timesensitive unit or building.
	 * @param destination
	 *		The destination of the new timesensitive unit or building.
	 * @param assigned
	 *		The assigned indicator of the new timesensitive unit or building
	 * @effect The new timesensitive unit or building is a unit or building with given name, home location.
	 *         |super(name,homeLocation);
	 * @effect The new timesensitive unit or building has the given speed.
	 *		|setSpeed(speed)
	 * @effect The new timesensitive unit or building has the given current location.
	 *		|setCurrentLocation(currentLocation)
	 * @effect The new timesensitive unit or building has the given destination.
	 *		|setDestination(destination)
	 * @effect The new timesensitive unit or building has the given assigned indicator.
	 *		|setAssigned(assigned)
	 * @throws InvalidUnitBuildingNameException
	 *		If the given name is an invalid name for a timesensitive unit or building.
	 * @throws InvalidLocationException
	 *		If the given home location or current location is an invalid home location or current location for a timesensitive unit or building.
	 * @throws InvalidSpeedException
	 *		If the given speed is an invalid speed for a timesensitive unit or building.
	 */
	 TimeSensitiveUnitBuilding(String name, GPSCoordinate homeLocation, long speed, GPSCoordinate currentLocation, GPSCoordinate destination, boolean assigned) throws InvalidUnitBuildingNameException, InvalidLocationException, InvalidSpeedException{
		super(name,homeLocation);
		setSpeed(speed);
		setCurrentLocation(currentLocation);
		setDestination(destination);
		setAssigned(assigned);
	}

	/**
	 * Returns the speed of this timesensitive unit or building.
	 * @return The speed of this timesensitive unit or building.
	 */
	public long getSpeed(){
		return speed;
	}

	/**
	 * Returns the current location of this timesensitive unit or building.
	 * @return The current location of this timesensitive unit or building.
	 */
	public GPSCoordinate getCurrentLocation(){
		return currentLocation;
	}

	/**
	 * Returns the destination of this timesensitive unit or building.
	 * @return The destination of this timesensitive unit or building.
	 */
	public GPSCoordinate getDestination(){
		return destination;
	}

	/**
	 * Returns whether this unit is assigned.
	 * @return True if this unit is assigned; false otherwise.
	 */
	public boolean isAssigned(){
		return assigned;
	}
	
	/**
	 * Sets the speed of this timesensitive unit or building to the given value.
	 *
	 * @param speed
	 *		The new speed of this timesensitive unit or building.
     * @throws InvalidSpeedException
	 *		If the given speed isn't a valid speed for a timesensitive unit or building.
     * @post The speed of this unit or building is set according to the given speed.
	 *		|new.getSpeed() == speed
	 */
	private void setSpeed(long speed) throws InvalidSpeedException{
		if(!isValidSpeed(speed)){
			throw new InvalidSpeedException(String.format("\"%s\" is an invalid name for a timesensitive unit or building.", speed));
		}else{
			this.speed = speed;			
		}
	}

	/**
	 * Sets the current location of this timesensitive unit or building to the given value.
	 *
	 * @param currentLocation
	 *		The new speed of this timesensitive unit or building.
     * @throws InvalidLocationException
	 *		If the given current location isn't a valid current location for a timesensitive unit or building.
     * @post The current location of this unit or building is set according to the given current location.
	 *		|new.getCurrentLocation() == currentLocation
	 */
	private void setCurrentLocation(GPSCoordinate currentLocation) throws InvalidLocationException{
        if(!isValidCurrentLocation(currentLocation)) {
            throw new InvalidLocationException(String.format("\"%s\" is an invalid current location for a unit.", currentLocation));
        }else{
			this.currentLocation = currentLocation;
		}
	}

	private void changeCurrentLocation(GPSCoordinate newCurrentLocation) throws InvalidLocationException{
		setCurrentLocation(newCurrentLocation);
	}
	/**
	 * Sets the destination of this timesensitive unit or building to the given value.
	 *
	 * @param destination
	 *		The new destination of this timesensitive unit or building.
     * @post The destination of this timesensitive unit or building is set according to the given destination.
	 *		|new.getDestination() == destination
	 */
	private void setDestination(GPSCoordinate destination){
		this.destination = destination;
	}

	/**
	 * Sets the assigned indicator to the given value.
	 *
	 * @param assigned
	 *		The new value of the assigned indicator.
     * @post The assigned indicator of this timesensitive unit or building is set according to the given assigned indicator.
	 *		|new.isAssigned() == assigned
	 */
	private void setAssigned(boolean assigned){
		this.assigned = assigned;
	}

	/**
	 * Checks if the given speed is a valid speed for a timesensitive unit or building.
	 * @param speed
	 *		The speed of a timesensitive unit or building to test.
	 * @return True if the speed is valid; false otherwise.
	 */
	public static boolean isValidSpeed(long speed){
		return speed >= 0;
	}

    /**
     * Checks if the given current location is a valid current location for a unit.
	 *
     * @param currentLocation
	 *		The current location of a unit to test.
     * @return True if the current location is valid; false otherwise.
     */
    public static boolean isValidCurrentLocation(GPSCoordinate currentLocation) {
        return (currentLocation != null);
    }
}
