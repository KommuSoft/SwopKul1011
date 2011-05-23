package projectswop20102011.domain;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidWithdrawalException;
import projectswop20102011.exceptions.InvalidCapacityException;

/**
 * A class that represents a firetruck.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @invar The capacity of every fire truck is valid
 *		|isValidCapcity(getCapacity) == true
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
	 *		| super(name,homeLocation,speed)
	 * @effect The capacity of this fire truck is set to the given capacity
	 *		| setCapacity(capacity)
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a firetruck.
	 * @throws InvalidMapItemNameException
	 *		If the given name is an invalid name for a firetruck.
	 * @throws InvalidSpeedException
	 *		If the given speed is an invalid speed for a firetruck.
	 * @throws InvalidCapacityException
	 *		If the given capacity is not effective.
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
	 *		If the given capacity is not valid.
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
	 * @return True if the given capacity is greater than or equal to zero, otherwise false.
	 */
	public static boolean isValidCapacity(long capacity) {
		return (capacity >= 0);
	}

	/**
	 * A method to withdraw this unit from the current Emergency.
	 * @param eventHandler
	 *		The event handler where the notifications should be sent to
	 * @throws InvalidWithdrawalException
	 *		Always: A Firetruck can't be withdrawn from an Emergency.
	 */
	@Override
	public void withdraw() throws InvalidWithdrawalException {
		throw new InvalidWithdrawalException("This unit can't be withdrawn");
	}

	/**
	 * Clones this firetruck
	 * @return A clone of this firetruck.
	 */
	@Override
	public Firetruck clone() {
		Firetruck fir = null;
		try {
			fir = new Firetruck(this.getName(), this.getHomeLocation(), this.getSpeed(), this.getCapacity());
		} catch (InvalidLocationException ex) {
			Logger.getLogger(Firetruck.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidMapItemNameException ex) {
			Logger.getLogger(Firetruck.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidSpeedException ex) {
			Logger.getLogger(Firetruck.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidCapacityException ex) {
			Logger.getLogger(Firetruck.class.getName()).log(Level.SEVERE, null, ex);
		}
		return fir;
	}
}
