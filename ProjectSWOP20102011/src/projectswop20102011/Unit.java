package projectswop20102011;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitNameException;

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
public abstract class Unit {

	/**
	 * A variable registering the name of this unit.
	 */
	private String name;

	/**
	 * A variable registering whether this unit is assigned.
	 */
	private boolean assigned;

	/**
	 * A variable registering the current location of this unit.
	 */
	private GPSCoordinate currentLocation;

	/**
	 * A variable registering the home location of this unit.
	 */
	private GPSCoordinate homeLocation;

	/**
	 * Initialize a new unit with given parameters.
	 *
	 * @param name
	 *		The name of the new unit.
	 * @param assigned
	 *		The assigned indicator of the new unit.
	 * @param currentLocation
	 *		The current location of the new unit.
	 * @param homeLocation
	 *		The home location of the new unit.
	 * @effect The new unit has the given name.
	 *		|setName(name)
	 * @effect The new unit has the given assigned indicator.
	 *		|setAssigned(assigned)
	 * @effect The new unit has the given current location.
	 *		|setCurrentLocation(currentLocation)
	 * @effect The new unit has the given home location.
	 *		|setHomeLocation(homeLocation)
	 * @throws InvalidUnitNameException
	 *		If the given name is an invalid name for a unit.
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a unit.
	 */
	protected Unit(String name,boolean assigned,GPSCoordinate currentLocation, GPSCoordinate homeLocation) throws InvalidUnitNameException, InvalidLocationException{
		setName(name);
		setAssigned(assigned);
		setCurrentLocation(currentLocation);
		setHomeLocation(homeLocation);
	}
	/**
	 * Returns the name of this unit.
	 * @return The name of this unit.
	 */
	public String getName(){
		return name;
	}

	/**
	 * Returns whether this unit is assigned.
	 * @return True if this unit is assigned; false otherwise.
	 */
	public boolean isAssigned(){
		return assigned;
	}

	/**
	 * Returns the current location of this unit.
	 * @return The current location of this unit.
	 */
	public GPSCoordinate getCurrentLocation(){
		return currentLocation;
	}

	/**
	 * Returns the home location of this unit.
	 * @return The home location of this unit.
	 */
	public GPSCoordinate getHomeLocation(){
		return homeLocation;
	}

	/**
	 * Sets the name of this unit to the given value.
	 *
	 * @param name
	 *		The new name of this unit.
     * @throws InvalidUnitNameException
	 *		If the given name isn't a valid name for a unit.
     * @post The name of this unit is set according to the given name.
	 *		|new.getName() == name
	 */
	private void setName(String name) throws InvalidUnitNameException{
		if(!isValidName(name)){
			throw new InvalidUnitNameException(String.format("\"%s\" is an invalid name for a unit.", name));
		}else{
			this.name = name;
		}

	}

	/**
	 * Sets the assigned indicator to the given value.
	 *
	 * @param assigned
	 *		The new value of the assigned indicator.
     * @post The assigned indicator of this unit is set according to the given assigned indicator.
	 *		|new.isAssigned() == assigned
	 */
	private void setAssigned(boolean assigned){
		this.assigned = assigned;
	}

	/**
	 * Sets the current location of this unit to the given value.
	 *
	 * @param currentLocation
	 *		The new current location of this unit.
     * @throws InvalidLocationException
	 *		If the given current location isn't a valid current location for a unit.
     * @post The current location of this unit is set according to the given current location.
	 *		|new.getCurrentLocation() == currentLocation
	 */
	private void setCurrentLocation(GPSCoordinate currentLocation) throws InvalidLocationException{
        if(!isValidCurrentLocation(currentLocation)) {
            throw new InvalidLocationException(String.format("\"%s\" is an invalid current location for a unit.", currentLocation));
        }else{
			this.currentLocation = currentLocation;
		}
	}

	/**
	 * Sets the home location of this unit to the given value.
	 *
	 * @param homeLocation
	 *		The new home location of this unit.
     * @throws InvalidLocationException
	 *		If the given home location isn't a valid home location for a unit.
     * @post The current location of this unit is set according to the given current location.
	 *		|new.getHomeLocation() == homeLocation
	 */
	private void setHomeLocation(GPSCoordinate homeLocation) throws InvalidLocationException{
        if(!isValidCurrentLocation(homeLocation)) {
            throw new InvalidLocationException(String.format("\"%s\" is an invalid home location for a unit.", homeLocation));
        }else{
			this.homeLocation = homeLocation;
		}
	}

	/**
	 * Checks if the given name is a valid name for a unit.
	 *
	 * @param name
	 *		The name of a unit to test.
	 * @return True if the name is valid; false otherwise.
	 */
	public static boolean isValidName(String name){
		return ((!name.equals(""))&&(name != null));
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

    /**
     * Checks if the given home location is a valid home location for a unit.
	 *
     * @param homeLocation
	 *		The home location of a unit to test.
     * @return True if the home location is valid; false otherwise.
     */
    public static boolean isValidHomeLocation(GPSCoordinate homeLocation) {
        return (homeLocation != null);
    }
}