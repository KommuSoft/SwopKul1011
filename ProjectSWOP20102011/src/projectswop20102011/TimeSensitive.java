package projectswop20102011;

import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;

/**
 * Implementing this interface means that the object becomes time sensitive.
 * I.e. it state depends on the current time.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public interface TimeSensitive {
	/**
	 * Returns the speed of this timesensitive unit or building.
	 * @return The speed of this timesensitive unit or building.
	 */
	public long getSpeed();

	/**
	 * Returns the current location of this timesensitive unit or building.
	 * @return The current location of this timesensitive unit or building.
	 */
	public GPSCoordinate getCurrentLocation();

	/**
	 * Returns the destination of this timesensitive unit or building.
	 * @return The destination of this timesensitive unit or building.
	 */
	public GPSCoordinate getDestination();

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
	private void setSpeed(long speed);

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
	private void setCurrentLocation(GPSCoordinate currentLocation);

	private void changeCurrentLocation(GPSCoordinate newCurrentLocation);

	/**
	 * Sets the destination of this timesensitive unit or building to the given value.
	 *
	 * @param destination
	 *		The new destination of this timesensitive unit or building.
     * @post The destination of this timesensitive unit or building is set according to the given destination.
	 *		|new.getDestination() == destination
	 */
	private void setDestination(GPSCoordinate destination);

	/**
	 * Checks if the given speed is a valid speed for a timesensitive unit or building.
	 * @param speed
	 *		The speed of a timesensitive unit or building to test.
	 * @return True if the speed is valid; false otherwise.
	 */
	public static boolean isValidSpeed(long speed);

    /**
     * Checks if the given current location is a valid current location for a unit.
	 *
     * @param currentLocation
	 *		The current location of a unit to test.
     * @return True if the current location is valid; false otherwise.
     */
    public static boolean isValidCurrentLocation(GPSCoordinate currentLocation);

	/**
	 *
	 * @param duration
	 * @throws InvalidLocationException
	 */
	public void changeLocation(long duration) throws InvalidLocationException;

	/**
	 *
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	private double calculateDistance(long x1,long y1,long x2,long y2);
}