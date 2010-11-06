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
     * @effect The new unit is a unit with given name, home location
     *         |super(name,homeLocation);
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
		setEmergency(null);
    }

    /**
     * Returns whether this unit is assigned.
     * @return True if this unit is assigned; false otherwise.
     */
    public boolean isAssigned() {
        return (this.getEmergency() != null);
    }

	/**
	 * Returns whether this unit is at its destination.
	 * @return True if this unit is at its destination; false otherwise.
	 */
	public boolean isAtDestination(){
		if(getEmergency() != null){
			return getCurrentLocation().equals(getEmergency().getLocation());
		} else {
			return false;
		}
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
     * Returns the destination of this timesensitive unit.
     * @return The location of the assigned emergency if the unit is assigned, otherwise the homelocation.
     */
    public GPSCoordinate getDestination() {
        if(this.emergency == null) {
			return this.getHomeLocation();
		}
        else {
			return emergency.getLocation();
		}
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
    private void setEmergency(Emergency emergency) {
        this.emergency = emergency;
    }

    /**
     * Returns the emergency of this unit.
     * @return The emergency of this unit if the unit is assigned, otherwise null.
     */
    public Emergency getEmergency() {
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
	//TODO Wanneer wordt er hier gecontroleerd of een Unit op zijn bestemming is, want als de duration te groot gekozen is gaat de Unit erover springen. Goed Jonas! Heb ik het hiermee verbeterd ? :s
    private void changeLocation(long duration) throws InvalidDurationException {
        if (duration > 0) {
            if (getDestination() != null) {
                long startX = getCurrentLocation().getX();
                long startY = getCurrentLocation().getY();
                long goalX = getDestination().getX();
                long goalY = getDestination().getY();
                long stopX;
                long stopY;

                double distance = getCurrentLocation().getDistanceTo(getDestination());
                long distanceX = goalX - startX;
                long distanceY = goalY - startY;

                stopX = Math.round(startX + (distanceX / distance) * getSpeed() * duration / 3600);
                stopY = Math.round(startY + (distanceY / distance) * getSpeed() * duration / 3600);
                GPSCoordinate stop = new GPSCoordinate(stopX, stopY);

				double calculatedDistance = getCurrentLocation().getDistanceTo(stop);
                try {
					if(calculatedDistance > distance){
						changeCurrentLocation(getDestination());
					}else{
						changeCurrentLocation(stop);
					}
                } catch (InvalidLocationException ex) {
                    Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (duration < 0) {
            throw new InvalidDurationException(String.format("\"%s\" is an invalid duration for a unit.", duration));
        }

    }

    /**
     * Advances the time with a given amount of seconds.
     * @param seconds
     *		The amount of seconds that the time must be advanced.
     */
    @Override
    public void timeAhead(long seconds) {
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
	//TODO is dit niet gevaarlijk? Nu is het mogelijk om gewoon een unit toe te wijzen aan een emergency (dus bv. een ambulance aan een robbery, waar dit totaal niet nodig is; zie pg. 3)
    public void assignTo(Emergency emergency) throws InvalidEmergencyStatusException {
        setEmergency(emergency);
        emergency.setStatus(EmergencyStatus.RESPONSE_IN_PROGRESS);
        try {
            setCurrentLocation(getHomeLocation());
        } catch (InvalidLocationException ex) {
            Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	/**
	 * Finishes the job of this Unit.
	 */
    public void finishedJob (){
        setEmergency(null);
		getEmergency().setWorkingUnits(getEmergency().getWorkingUnits()-1);
    }

}
