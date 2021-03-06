package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidWithdrawalException;
import projectswop20102011.exceptions.InvalidCapacityException;

/**
 * A class that represents a firetruck.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Firetruck extends Unit {

	/**
	 * The capacity of the firetruck.
	 */
	private long capacity;

	/**
	 * Initialize a new firetruck with given parameters.
	 *
	 * @param name
	 *		The name of the new firetruck.
	 * @param homeLocation
	 *		The home location of the new firetruck.
	 * @param speed
	 *		The speed of the new firetruck.
	 * @param capacity
	 *		The capacity of this firetruck.
	 * @effect The new firetruck is a unit with given name, home location, speed and capacity.
	 *		|super(name,homeLocation,speed)
	 * @effect The capacity of this fire truck is set to the given capacity
	 *		|this.capacity.equals(capacity)
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a firetruck.
	 * @throws InvalidMapItemNameException
	 *		If the given name is an invalid name for a firetruck.
	 * @throws InvalidSpeedException
	 *		If the given speed is an invalid speed for a firetruck.
	 * @throws InvalidCapacityException
	 *          If the given capacity is not effective.
	 */
	public Firetruck(String name, GPSCoordinate homeLocation, long speed, long capacity) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException {
		super(name, homeLocation, speed);
		setCapacity(capacity);
	}

	/**
	 * Returns the capacity of the firetruck.
	 * @return the capacity of the firetruck.
	 */
	public long getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity to the given value.
	 * @param capacity
	 *		The new capacity of this firetruck.
	 * @post This capacity is equal to the given capacity
	 *		|new.capacity.equals(capacity)
	 * @throws InvalidCapacityException
	 *          If the given capacity is not effective.
	 */
	private void setCapacity(long capacity) throws InvalidCapacityException {
		if (!isValidCapacity(capacity)) {
			throw new InvalidCapacityException("The given capacity must be effective.");
		}
		this.capacity = capacity;
	}

	/**
	 * Tests if the given capacity is a valid capacity for a firetruck.
	 * @param capacity
	 *      The given capacity.
	 * @return True if the given capacity is effective, otherwise false.
	 */
	public static boolean isValidCapacity(long capacity) {
		return (capacity >= 0);
	}

	/**
	 * Tests if this firetruck can handle a fire with a given fire size
	 * @param fireSize
	 *      The fire size of the fire to test.
	 * @return True
	 */
	public boolean canHandleFireSize(FireSize fireSize) {
		//TODO:Dit zou nu altijd true moeten teruggeven, aangezien iedere firetruck dienst kan doen (onafhankelijk van het aantal liters.
		return true;
	}

	/**
	 * A method to withdraw this unit from the current Emergency.
	 * @throws InvalidWithdrawalException
	 *		Always: A Firetruck can't be withdrawn from an Emergency.
	 */
	@Override
	public void withdraw() throws InvalidWithdrawalException {
		throw new InvalidWithdrawalException("This unit can't be withdrawn");
	}
}
