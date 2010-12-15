package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.ILocation;
import projectswop20102011.domain.GPSCoordinate;

/**
 * A class that represents a location.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Location implements ILocation {

	/**
	 * A variable registering the GPSCoordinate of this location.
	 */
	private final GPSCoordinate gpsCoordinate;

	/**
	 * Creates a new location with the given coordinates.
	 * @param x
	 *		The x coordinate of the new location.
	 * @param y
	 *		The y coordinate of the new location.
	 */
	public Location(int x, int y) {
		gpsCoordinate = new GPSCoordinate(x, y);
	}

	/**
	 * Returns the GPSCoordinate of this location.
	 * @return The GPSCoordinate of this location.
	 */
	private GPSCoordinate getGpsCoordinate() {
		return gpsCoordinate;
	}

	/**
	 * Returns the x coordinate of this location.
	 * @return The x coordinate of this location.
	 */
	@Override
	public int getX() {
		return (int) getGpsCoordinate().getX();
	}


	/**
	 * Returns the y coordinate of this location.
	 * @return The y coordinate of this location.
	 */
	@Override
	public int getY() {
		return (int) getGpsCoordinate().getY();
	}

	/**
	 * Returns a textual representation of this location.
	 * @return A textual representation of this location.
	 */
	public String toString(){
		return getGpsCoordinate().toString();
	}
}
