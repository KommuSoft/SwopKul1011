package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidWithdrawalException;

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
 * @invar The speed of a unit is always valid.
 *		|isValidSpeed(getSpeed())
 */
public abstract class Unit extends MapItem implements TimeSensitive {

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
     * A variable registering whether a unit was already at the site of emergency.
     */
    private boolean wasAlreadyAtSite;

    /**
     * Initialize a new unit with given parameters.
     *
     * @param name
     *		The name of the new unit.
     * @param homeLocation
     *		The home location of the new unit.
     * @param speed
     *		The speed of the new unit.
     * @effect The new unit is a unit with given name and home location.
     *		|super(name,homeLocation);
     * @effect This speed is equal to the given parameter speed.
     *		|speed.equals(getSpeed())
     * @effect This currentLocation is equal to the given parameter homeLocation.
     *		|homeLocation.equals(getCurrentLocation())
     * @effect This emergency is equal to null.
     *		|getEmergency().equals(null)
	 * @effect The unit wasn't already at the site of emergency.
	 *		|setWasAlreadyAtSite(false)
     * @throws InvalidMapItemNameException
     *		If the given name is an invalid name for a unit.
     * @throws InvalidLocationException
     *		If the given location is an invalid location for a unit.
     * @throws InvalidSpeedException
     *		If the given speed is an invalid speed for a unit.
     */
    Unit(String name, GPSCoordinate homeLocation, long speed) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException {
        super(name, homeLocation);
        setSpeed(speed);
        setCurrentLocation(homeLocation);
        setEmergency(null);
        setWasAlreadyAtSite(false);
    }

    /**
     * Returns whether this unit is assigned.
     * @return True if this unit is assigned; false otherwise.
     */
    public boolean isAssigned() {
        return (getEmergency() != null);
    }

    /**
     * Returns whether this unit is at its destination.
     * @return True if this unit is at its destination; false otherwise.
     */
    public boolean isAtDestination() {
        if (getEmergency() != null) {
            return getCurrentLocation().equals(getDestination());
        } else {
            return false;
        }
    }

    /**
     * Returns the speed of this unit.
     * @return The speed of this unit.
     */
    public long getSpeed() {
        return speed;
    }

    /**
     * Returns the current location of this unit.
     * @return The current location of this unit.
     */
    public GPSCoordinate getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Returns the destination of this unit.
     * @return The location of the assigned emergency if the unit is assigned, otherwise the homelocation.
     */
    public GPSCoordinate getDestination() {
        if (getEmergency() == null) {
            return getHomeLocation();
        } else {
            return getEmergency().getLocation();
        }
    }

    /**
     * Returns whether a unit was already at the site of emergency.
     * @return True if this unit was already at the site of emergency, false otherwise.
     */
    public boolean wasAlreadyAtSite() {
        return wasAlreadyAtSite;
    }

    /**
     * Sets the speed of this unit to the given value.
     * @param speed
     *		The new speed of this unit.
     * @throws InvalidSpeedException
     *		If the given speed isn't a valid speed for a timesensitive mapitem.
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
     * Sets the current location of this unit to the given value.
     * @param currentLocation
     *		The new speed of this unit.
     * @post The current location of this unit is set according to the given current location.
     *		|new.getCurrentLocation() == currentLocation
     */
    private void setCurrentLocation(GPSCoordinate currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * Sets the emergency of this Unit.
     * @param emergency
     *		The new emergency of this unit.
     * @post The emergency of this unit is set to the given emergency.
     *		|new.getEmergency() == emergency
     */
    final void setEmergency(Emergency emergency) {
        this.emergency = emergency;
    }

    /**
     * Sets whether a unit was already at the site of emergency.
     * @param wasAlreadyAtSite
     *		The condition whether a unit was already at the site of emergency.
     * @post The condition wasAlreadyAtSite is set to the given value.
     *		|new.wasAlreadyAtSite() == wasAlreadyAtSite
     */
    protected final void setWasAlreadyAtSite(boolean wasAlreadyAtSite) {
        this.wasAlreadyAtSite = wasAlreadyAtSite;
    }

    /**
     * Returns the emergency of this unit.
     * @return The emergency of this unit if the unit is assigned, otherwise null.
     */
    public Emergency getEmergency() {
        return emergency;
    }

    /**
     * Checks if the given speed is a valid speed for a timesensitive unit or building.
     * @param speed
     *		The speed of a timesensitive mapitem to test.
     * @return True if the speed is valid; false otherwise.
     */
    public boolean isValidSpeed(long speed) {
        return speed >= 0;
    }

    /**
     * Checks if the given current location is a valid current location for a unit.
     * @param currentLocation
     *		The current location of a unit to test.
     * @return True if the current location is valid; false otherwise.
     */
    public static boolean isValidCurrentLocation(GPSCoordinate currentLocation) {
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
    private void changeLocation(long duration) throws InvalidDurationException {
        if (duration > 0) {
            if (getDestination() != null) {
                long startX = getCurrentLocation().getX();
                long startY = getCurrentLocation().getY();
                long goalX = getDestination().getX();
                long goalY = getDestination().getY();

                double distance = getCurrentLocation().getDistanceTo(getDestination());
                long distanceX = goalX - startX;
                long distanceY = goalY - startY;

                long stopX = Math.round(startX + (distanceX / distance) * getSpeed() * duration / 3600);
                long stopY = Math.round(startY + (distanceY / distance) * getSpeed() * duration / 3600);
                GPSCoordinate stop = new GPSCoordinate(stopX, stopY);

                double calculatedDistance = getCurrentLocation().getDistanceTo(stop);
                if (calculatedDistance > distance) {
                    setCurrentLocation(getDestination());
                } else {
                    setCurrentLocation(stop);
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
     * @throws InvalidDurationException
     *		If the given amount of seconds is invalid.
     * @effect The location of this unit is changed.
     *		|changeLocation(seconds)
     * @effect If this unit arrived at the location of the emergency during this action, the flag wasAlreadyAtSite is set to true.
     *		| wasAlreadyAtSite().equals(true)
     */
    @Override
    public void timeAhead(long seconds) throws InvalidDurationException {
        changeLocation(seconds);
        if (isAssigned() && wasAlreadyAtSite() == false && getCurrentLocation() == getEmergency().getLocation()) {
            setWasAlreadyAtSite(true);
        }
    }

    /**
     * Assign the unit to a given emergency.
     * @param emergency
     *		The emergency where this unit has to respond to.
     * @effect The units emergency is equal to the given emergency
     *		| this.getEmergency().equals(emergency)
     * @effect The unit is assigned.
     * @throws InvalidMapItemException
     *          If the unit is already assigned to an emergency.
     */
    void assignTo(Emergency emergency) throws InvalidMapItemException {
        if (!canBeAssigned()) {
            throw new InvalidMapItemException("Unit can't be assigned");
        } else {
            setEmergency(emergency);
            if (getCurrentLocation() == emergency.getLocation()) {
                setWasAlreadyAtSite(true);
            }
        }
    }

    /**
     * Checks if this unit can be assigned.
     * @return True if this unit can be assigned, otherwise false.
     */
    public boolean canBeAssigned() {
        return !isAssigned();
    }

    /**
     * Withdraw this unit from the emergency he is currently assigned to
	 * @effect The unit is withdrawn from its emergency.
	 *		|this.getEmergency().withdrawUnit(this)
	 * @effect The emergency of this unit is set to null.
	 *		|this.setEmergency(null)
     * @throws InvalidWithdrawalException
     *			If the unit is already at site of the emergency.
     * @throws InvalidEmergencyException
     *			If the unit isn't assigned to an emergency.
     * @throws InvalidEmergencyStatusException
     *                  If the emergency of this unit is in a state where the unit cannot withdraw.
     */
    public void withdraw() throws InvalidWithdrawalException, InvalidEmergencyException, InvalidEmergencyStatusException {
        if (!this.isAssigned()) {
            throw new InvalidEmergencyException("The unit is not assigned to an emergency, so it can't be withdrawn.");
        }
        if (this.wasAlreadyAtSite()) {
            throw new InvalidWithdrawalException("The unit was already at site of the emergency, so it can't be withdrawn.");
        }
        this.getEmergency().withdrawUnit(this);
        this.setEmergency(null);
    }

    /**
     * Calculates the expected time of arrival to a location.
     * @param location
     *		The given location.
     * @return The number of seconds this unit would use to reach the given location.
     */
    public long getETA(GPSCoordinate location) {
        return Math.round(3600 * this.getDistanceTo(location) / this.getSpeed());
    }

    /**
     * Calculates the distance of this unit to the given location.
     * @param location
     *		The location to calculate the distance to.
     * @return The distance between this unit and the given location.
     */
    public double getDistanceTo(GPSCoordinate location) {
        return this.getCurrentLocation().getDistanceTo(location);
    }

    /**
     * Finishes the job of this Unit.
     * @effect The emergency of this unit is null
     *		| this.getEmergency().equals(null)
     * @effect The flag wasAlreadyAtSite is set to false.
	 *		|this.getWasAlreadyAtSite().equals(false)
     * @throws InvalidEmergencyException
     *		If the unit is not assigned to an emergency.
     * @throws InvalidLocationException
     *		If the unit is not at the location of the emergency.
     */
    public void finishedJob() throws InvalidEmergencyException, InvalidLocationException, InvalidEmergencyStatusException, Exception {
        if (isAssigned()) {
            if (isAtDestination()) {
                getEmergency().finishUnit(this);
                setEmergency(null);
                setWasAlreadyAtSite(false);
            } else {
                throw new InvalidLocationException("The unit is not at it's destination.");
            }
        } else {
            throw new InvalidEmergencyException("The unit is not assigned to an emergency so it can't finishes its job.");
        }
    }
}
