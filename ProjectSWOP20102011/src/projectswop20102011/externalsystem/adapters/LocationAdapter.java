package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;
import projectswop20102011.domain.GPSCoordinate;

/**
 * A LocationAdapter that represents a location based on (x,y) coordinates.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class LocationAdapter implements ILocation {

	/**
	 * The GPSCoordinate of this LocationAdapter.
	 */
	private final GPSCoordinate location;

	/**
	 * Creates a LocationAdapter from the given GPSCoordinate.
	 * @param location
	 *		The GPSCoordinate that is used to create a LocationAdapter.
	 */
	public LocationAdapter(GPSCoordinate location) {
		this.location = location;
	}

	/**
	 * Returns the GPSCoordinate of this LocationAdapter.
	 * @return The GPSCoordinate of this LocationAdapter.
	 */
	public GPSCoordinate getGPSCoordinate() {
		return location;
	}

	/**
	 * Retrieves the x part of the coordinates.
	 * @return The x coordinate.
	 */
	@Override
	public int getX() {
		return (int) getGPSCoordinate().getX();
	}

	/**
	 * Retrieves the y part of the coordinates.
	 * @return The y coordinate
	 */
	@Override
	public int getY() {
		return (int) getGPSCoordinate().getY();
	}

	/**
	 * Calculates the euclidean distance from this location to the given location.
	 * @param location
	 *		The location to calculate the distance to.
	 * @return
	 *		The Euclidean distance between the two locations
	 */
	@Override
	public double getDistanceTo(ILocation location) {
		LocationAdapter to = (LocationAdapter) location;
		return getGPSCoordinate().getDistanceTo(to.getGPSCoordinate());
	}

	/**
	 * A textual representation of this LocationAdapter.
	 * @return The textual representation of this LocationAdapter.
	 */
	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}
}
