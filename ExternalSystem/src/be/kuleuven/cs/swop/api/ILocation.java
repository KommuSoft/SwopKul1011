package be.kuleuven.cs.swop.api;

/**
 * This interface represents a location based on (x,y) coordinates.
 * 
 * @author philippe
 */
public interface ILocation {

	/**
	 * Retrieves the x part of the coordinates. Any integer is valid.
	 * @return The x coordinate
	 */
	public int getX();
	
	/**
	 * Retrieves the y part of the coordinates. Any integer is valid.
	 * @return The y coordinate
	 */
	public int getY();
	
	/**
	 * Calculates the euclidean distance from this location
	 * 	to the given location.
	 * @param location The location to calculate the distance to
	 * @return The euclidean distance between the two locations
	 */
	public double getDistanceTo(ILocation location);
	
	@Override
	public boolean equals(Object arg0);
}
