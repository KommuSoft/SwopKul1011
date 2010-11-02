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
	 * Returns the speed of this time sensitive object.
	 * @return The speed of this time sensitive object.
	 */
	public long getSpeed();

	/**
	 * Returns the current location of this time sensitive object.
	 * @return The current location of this time sensitive object.
	 */
	public GPSCoordinate getCurrentLocation();

	/**
	 * Returns the destination of this time sensitive object.
	 * @return The destination of this time sensitive object.
	 */
	public GPSCoordinate getDestination();

	/**
	 * Sets the speed of this time sensitive object to the given value.
	 *
	 * @param speed
	 *		The new speed of this time sensitive object.
     * @throws InvalidSpeedException
	 *		If the given speed isn't a valid speed for a time sensitive object.
     * @post The speed of this time sensitive object is set according to the given speed.
	 *		|new.getSpeed() == speed
	 */
	public void setSpeed(long speed) throws InvalidSpeedException;

	/**
	 * Sets the current location of this time sensitive object to the given value.
	 *
	 * @param currentLocation
	 *		The new speed of this time sensitive object.
     * @throws InvalidLocationException
	 *		If the given current location isn't a valid current location for a time sensitive object.
     * @post The current location of this time sensitive object is set according to the given current location.
	 *		|new.getCurrentLocation() == currentLocation
	 */
	public void setCurrentLocation(GPSCoordinate currentLocation) throws InvalidLocationException;

	/**
	 * Sets the destination of this time sensitive object to the given value.
	 *
	 * @param destination
	 *		The new destination of this time sensitive object.
     * @post The destination of this time sensitive object is set according to the given destination.
	 *		|new.getDestination() == destination
	 */
	public void setDestination(GPSCoordinate destination);

	/**
	 * Checks if the given speed is a valid speed for a time sensitive object.
	 * @param speed
	 *		The speed of a time sensitive object to test.
	 * @return True if the speed is valid; false otherwise.
	 */
	public boolean isValidSpeed(long speed);

    /**
     * Checks if the given current location is a valid current location for a time sensitive object.
	 *
     * @param currentLocation
	 *		The current location of a time sensitive to test.
     * @return True if the current location is valid; false otherwise.
     */
    public boolean isValidCurrentLocation(GPSCoordinate currentLocation);

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
	public double calculateDistance(long x1,long y1,long x2,long y2);
}