package projectswop20102011;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
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
public abstract class Unit extends UnitBuilding implements TimeSensitive {

    /**
     * A variable registering whether this unit is assigned.
     */
    private boolean assigned;
    /**
     * A variable registering the speed of this unit.
     */
    private long speed;
    /**
     * A variable registering the current location of this unit.
     */
    private GPSCoordinate currentLocation;
	/**
	 * A variable registering the emergency of this unit.
	 */
	private Emergency emergency;


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
     * @param assigned
     *		The assigned indicator of the new unit.
     * @effect The new unit is a unit with given name, home location, speed,
     *			current location, destination.
     *         |super(name,homeLocation,speed,currentLocation,destination);
     * @post The new unit has the given assigned indicator
     *		|new.isAssigned() == assigned
     * @throws InvalidUnitBuildingNameException
     *		If the given name is an invalid name for a unit.
     * @throws InvalidLocationException
     *		If the given location is an invalid location for a unit.
     * @throws InvalidSpeedException
     *		If the given speed is an invalid speed for a unit.
     */
    Unit(String name, GPSCoordinate homeLocation, long speed) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        super(name, homeLocation);
        setSpeed(speed);
        setCurrentLocation(homeLocation);
        setAssigned(false);
    }

    /**
     * Returns whether this unit is assigned.
     * @return True if this unit is assigned; false otherwise.
     */
    public boolean isAssigned() {
        return assigned;
    }

    /**
     * Sets the assigned indicator to the given value.
     *
     * @param assigned
     *		The new value of the assigned indicator.
     * @post The assigned indicator of this timesensitive unit or building is set according to the given assigned indicator.
     *		|new.isAssigned() == assigned
     */
    private void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    /**
     * Returns the speed of this timesensitive unit or building.
     * @return The speed of this timesensitive unit or building.
     */
    public long getSpeed() {
        return speed;
    }

    /**
     * Returns the current location of this timesensitive unit or building.
     * @return The current location of this timesensitive unit or building.
     */
    public GPSCoordinate getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Returns the destination of this timesensitive unit or building.
     * @return The destination of this timesensitive unit or building.
     */
    public GPSCoordinate getDestination() {
        return emergency.getLocation();
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
    private void setSpeed(long speed) throws InvalidSpeedException {
        if (!isValidSpeed(speed)) {
            throw new InvalidSpeedException(String.format("\"%s\" is an invalid name for a timesensitive unit or building.", speed));
        } else {
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
    private void setCurrentLocation(GPSCoordinate currentLocation) throws InvalidLocationException {
        if (!isValidCurrentLocation(currentLocation)) {
            throw new InvalidLocationException(String.format("\"%s\" is an invalid current location for a unit.", currentLocation));
        } else {
            this.currentLocation = currentLocation;
        }
    }

	/**
	 * Sets the emergency of this Unit.
	 * @param emergency
	 *		The new emergency of this unit.
	 */
	private void setEmergency(Emergency emergency){
		this.emergency = emergency;
	}

	//TODO: is dit gevaarlijk
	/**
	 * Returns the emergency of this unit.
	 * @return The emergency of this unit.
	 */
	public Emergency getEmergency(){
		return emergency;
	}

    private void changeCurrentLocation(GPSCoordinate newCurrentLocation) throws InvalidLocationException {
        setCurrentLocation(newCurrentLocation);
    }

    /**
     * Checks if the given speed is a valid speed for a timesensitive unit or building.
     * @param speed
     *		The speed of a timesensitive unit or building to test.
     * @return True if the speed is valid; false otherwise.
     */
    public boolean isValidSpeed(long speed) {
        return speed >= 0;
    }

    /**
     * Checks if the given current location is a valid current location for a unit.
     *
     * @param currentLocation
     *		The current location of a unit to test.
     * @return True if the current location is valid; false otherwise.
     */
    public boolean isValidCurrentLocation(GPSCoordinate currentLocation) {
        return (currentLocation != null);
    }

	/**
	 * Change the current location of this unit over a given time interval.
	 * @param duration
	 *		The duration of the displacement of this unit.
	 * @effect
	 *		The location of this unit has been changed according to a given time interval.
	 *		Stop is the current location of this unit after the time interval.
	 *		|changeCurrentLocation(stop)
	 * @throws InvalidDurationException
	 *		If the given duration is invalid.
	 */
	public void changeLocation(long duration)throws InvalidDurationException{
		if(duration > 0){
			if(getDestination() != null){
				long startX = getCurrentLocation().getX();
				long startY = getCurrentLocation().getY();
				long goalX = getDestination().getX();
				long goalY = getDestination().getY();
				long stopX;
				long stopY;

				double distance = getCurrentLocation().getDistanceTo(getDestination());
				long distanceX = goalX-startX;
				long distanceY = goalY-startY;

				stopX = Math.round(startX + (distanceX/distance)*getSpeed()*duration/3600);
				stopY = Math.round(startY + (distanceY/distance)*getSpeed()*duration/3600);
				GPSCoordinate stop = new GPSCoordinate(stopX,stopY);
				try {
					changeCurrentLocation(stop);
				} catch (InvalidLocationException ex) {
					Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
				}

				if(getCurrentLocation().getX() == getDestination().getX() &&
						getCurrentLocation().getY() == getDestination().getY()){
					//TODO: report dat de emergency afgewerkt is
				}
			}
		}else if(duration < 0){
			throw new InvalidDurationException(String.format("\"%s\" is an invalid duration for a unit.", duration));
		}

    }

    /**
     * Calculates the distance from one location to another.
     * @param x1
     *		The x-coordinate of the first location.
     * @param y1
     *		The y-coordinate of the first location.
     * @param x2
     *		The x-coordinate of the second location.
     * @param y2
     *		The y-coordinate of the second location.
     * @return
     *		The distance from one location to another one.
     *
     */
    public double calculateDistance(long x1, long y1, long x2, long y2) {
        double distanceX = Math.pow((double) x2 - (double) x1, 2);
        double distanceY = Math.pow((double) y2 - (double) y1, 2);
        return Math.sqrt(distanceX + distanceY);
    }

	/**
	 * Advances the time with a given amount of seconds.
	 * @param seconds
	 *		The amount of seconds that the time must be advanced.
	 */
	@Override
	public void timeAhead(long seconds){
		try {
			changeLocation(seconds);
		} catch (InvalidDurationException ex) {
			Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Assign the unit to a given emergency.
	 *
	 * @pre The unit is not assigned.
	 * @param emergency
	 *		The emergency where this units have to respond to.
	 * @throws InvalidEmergencyStatusException
	 *		If the emergency status is invalid.
	 */
	public void assignTo(Emergency emergency) throws InvalidEmergencyStatusException{
		setEmergency(emergency);
		setAssigned(true);
		emergency.setStatus(EmergencyStatus.RESPONSE_IN_PROGRESS);
		try {
			setCurrentLocation(getHomeLocation());
		} catch (InvalidLocationException ex) {
			Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}