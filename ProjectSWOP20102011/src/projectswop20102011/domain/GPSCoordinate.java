package projectswop20102011.domain;

import java.util.Collection;

/**
 * A class that represents a GPSCoordinate.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class GPSCoordinate {

	/**
	 * A variable that registers the x coordinate of the GPS coordinate.
	 */
	private final long x;
	/**
	 * A variable that registers the y coordinate of the GPS coordinate.
	 */
	private final long y;

	/**
	 * Creates a new GPS coordinate.
	 * @param x
	 *		The x coordinate of this GPS coordinate.
	 * @param y
	 *		The y coordinate of this GPS coordinate.
	 * @post The x-coordinate of this GPSCoordinate is set according to the given x-coordinate.
	 *		|new.getX() == x
	 * @post The y-coordinate of this GPSCoordinate is set according to the given y-coordinate.
	 *		|new.getY() == y
	 */
	public GPSCoordinate(long x, long y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the x coordinate of this GPS coordinate.
	 * @return The x coordinate of this GPS coordinate.
	 */
	public long getX() {
		return x;
	}

	/**
	 * Returns the y coordinate of this GPS coordinate.
	 * @return The y coordinate of this GPS coordinate.
	 */
	public long getY() {
		return y;
	}

	/**
	 * Returns a textual representation of this GPSCoordinate.
	 * @return A textual representation of this GPSCoordinate.
	 */
	@Override
	public String toString() {
		return String.format("(%s,%s)", this.getX(), this.getY());
	}

	/**
	 * Calculates the distance to a given GPSCoordinate.
	 * @param gpsCoordinate
	 *		The GPSCoordinate to calculate te distance to.
	 * @return The distance to the given GPSCoordinate.
	 */
	public double getDistanceTo(GPSCoordinate gpsCoordinate) {
		return Math.sqrt(Math.pow(getX() - gpsCoordinate.getX(), 2) + Math.pow(getY() - gpsCoordinate.getY(), 2));
	}

	/**
	 * Checks whether the given object is equal to this GPSCoordinate.
	 * @param o
	 *		The object to check whether this GPSCoordinate is equal to it.
	 * @return True if the given object is equal to this GPSCoordinate; false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof GPSCoordinate) {
			return equals((GPSCoordinate) o);
		} else {
			return false;
		}
	}

	/**
	 * Checks whether the given GPSCoordinate is equal to this GPSCoordinate.
	 * @param gps2
	 *		The GPSCoordinate to check whether this GPSCoordinate is equal to it.
	 * @return True if the given GPSCoordinate is equal to this GPSCoordinate; false otherwise.
	 */
	public boolean equals(GPSCoordinate gps2) {
		return (getX() == gps2.getX() && getY() == gps2.getY());
	}

	/**
	 * Calculate the new GPSCoordinate when an object moves from the current coordinate to the destination by a given length.
	 * @param destination
	 *		The distination where the object needs to move to.
	 * @param length
	 *		The amount of movement the object makes.
	 * @return The new GPSCoordinate where the object is after the movement.
	 * @note If the distance to the destination is smaller than the given length, the result will be the destination.
	 * @note If the destination is a null reference, the current GPSCoordinate will be returned.
	 */
	public GPSCoordinate calculateMovingTo(GPSCoordinate destination, double length) {
		if (destination == null) {
			return this;
		}
		double distance = this.getDistanceTo(destination);
		if (distance <= length) {
			return destination;
		} else {
			long dx = destination.getX() - this.getX();
			long dy = destination.getY() - this.getY();
			long newX = Math.round(this.getX() + length * dx / distance);
			long newY = Math.round(this.getY() + length * dy / distance);
			return new GPSCoordinate(newX, newY);
		}
	}

	/**
	 * Calculates the average GPSCoordinate of a list of GPSCoordinates.
	 * @param coordinates
	 *		A collection of coordinates to calculate the average GPSCoordinate from.
	 * @return The average GPSCoordinate, this is obtained by calculating the average of the x and y coordinates of the GPSCoordinates.
	 */
	public static GPSCoordinate calculateAverage(Collection<GPSCoordinate> coordinates) {
		long xtot = 0;
		long ytot = 0;
		for (GPSCoordinate coor : coordinates) {
			xtot += coor.getX();
			ytot += coor.getY();
		}
		xtot /= coordinates.size();
		ytot /= coordinates.size();
		return new GPSCoordinate(xtot, ytot);
	}

	/**
	 * Returns the hascode of the current GPSCoordinate. An integer representing the coordinate.
	 * @return the hascode of the current GPSCoordinate.
	 */
	@Override
	public int hashCode() {
		return ((Long) getX()).hashCode() ^ ((Long) getY()).hashCode();
	}
}
