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
public abstract class Unit extends UnitBuilding implements TimeSensitive{

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
	 * A variable registering the destination of this unit.
	 */
	private GPSCoordinate destination;

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
	Unit(String name, GPSCoordinate homeLocation, long speed, GPSCoordinate currentLocation, GPSCoordinate destination, boolean assigned) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		super(name, homeLocation);
		setSpeed(speed);
		setCurrentLocation(currentLocation);
		setDestination(destination);
		setAssigned(assigned);
	}

	/**
	 * Initialize a new unit who is at the home location and isn't assigned.
	 *
	 * @param name
	 *		The name of the new unit.
	 * @param homeLocation
	 *		The home location of the new unit.
	 * @param speed
	 *		The speed of the new unit.
	 * @effect The new unit is a unit with given name, home location, speed,
	 *         |this(name,homeLocation,speed,homeLocation,null,false);
	 * @throws InvalidUnitBuildingNameException
	 *		If the given name is an invalid name for a unit.
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a unit.
	 * @throws InvalidSpeedException
	 *		If the given speed is an invalid speed for a unit.
	 */
	Unit(String name,GPSCoordinate homeLocation,long speed) throws InvalidUnitBuildingNameException, InvalidLocationException, InvalidSpeedException{
		this(name,homeLocation,speed,homeLocation,null,false);
	}

	/**
	 * Returns whether this unit is assigned.
	 * @return True if this unit is assigned; false otherwise.
	 */
	public boolean isAssigned(){
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
	private void setAssigned(boolean assigned){
		this.assigned = assigned;
	}

	/**
	 * Returns the speed of this timesensitive unit or building.
	 * @return The speed of this timesensitive unit or building.
	 */
	@Override
	public long getSpeed(){
		return speed;
	}

	/**
	 * Returns the current location of this timesensitive unit or building.
	 * @return The current location of this timesensitive unit or building.
	 */
	@Override
	public GPSCoordinate getCurrentLocation(){
		return currentLocation;
	}

	/**
	 * Returns the destination of this timesensitive unit or building.
	 * @return The destination of this timesensitive unit or building.
	 */
	@Override
	public GPSCoordinate getDestination(){
		return destination;
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
	@Override
	public final void setSpeed(long speed) throws InvalidSpeedException{
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
	@Override
	public final void setCurrentLocation(GPSCoordinate currentLocation) throws InvalidLocationException{
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
	@Override
	public final void setDestination(GPSCoordinate destination){
		this.destination = destination;
	}

	/**
	 * Checks if the given speed is a valid speed for a timesensitive unit or building.
	 * @param speed
	 *		The speed of a timesensitive unit or building to test.
	 * @return True if the speed is valid; false otherwise.
	 */
	@Override
	public boolean isValidSpeed(long speed){
		return speed >= 0;
	}

    /**
     * Checks if the given current location is a valid current location for a unit.
	 *
     * @param currentLocation
	 *		The current location of a unit to test.
     * @return True if the current location is valid; false otherwise.
     */
	@Override
    public boolean isValidCurrentLocation(GPSCoordinate currentLocation) {
        return (currentLocation != null);
    }

	/**
	 *
	 * @param duration
	 * @throws InvalidLocationException
	 */
	@Override
	public void changeLocation(long duration) throws InvalidLocationException{
		if(destination==null){
			//TODO: Zal wss wel op andere manier moeten
			throw new NullPointerException();
		}else{
			long startX = getCurrentLocation().getX();
			long startY = getCurrentLocation().getY();
			long goalX = getDestination().getX();
			long goalY = getDestination().getY();
			long stopX;
			long stopY;

			double distance = calculateDistance(startX, startY,goalX,goalY);
			double distanceX = (double)goalX-(double)startX;
			double distanceY = (double)goalY-(double)startY;

			stopX = Math.round((double)startX + (distanceX/distance)*(double)getSpeed()*(double)duration);
			stopY = Math.round((double)startY + (distanceY/distance)*(double)getSpeed()*(double)duration);
			GPSCoordinate stop = new GPSCoordinate(stopX,stopY);
			changeCurrentLocation(stop);

			if(getCurrentLocation().getX() == getDestination().getX() &&
					getCurrentLocation().getY() == getDestination().getY()){
				setDestination(null);
			}
		}

	}

	/**
	 *
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	@Override
	public double calculateDistance(long x1,long y1,long x2,long y2){
		double distanceX = Math.pow((double)x2-(double)x1,2);
		double distanceY = Math.pow((double)y2-(double)y1, 2);
		return Math.sqrt(distanceX + distanceY);
	}
}