package be.kuleuven.cs.swop.events;

import be.kuleuven.cs.swop.api.ILocation;

/**
 * This class represents a concrete location in the system, using
 * 	(x,y) coordinates.
 * 
 * @author philippe
 */
public class Location implements ILocation {

	/**
	 * The X coordinate of the location
	 */
	private int x = 0;
	/**
	 * The Y coordinate of the location
	 */
	private int y = 0;
	
	/**
	 * Creates a new location with the given X and Y 
	 * 	coordinates
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	public Location(int x, int y) {
		setX(x);
		setY(y);
	}

	@Override
	public double getDistanceTo(ILocation location) {
		int x = getX() - location.getX();
		int y = getY() - location.getY();
		return Math.sqrt(x*x + y*y);
	}
	
	@Override
	public boolean equals(Object arg0) {
		try {
			ILocation t = (ILocation)arg0;
			return getX() == t.getX() && getY() == t.getY();
		}
		catch(ClassCastException e) {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}
	
	/*
	 * Getters and Setters
	 */
	
	@Override
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the new value of x
	 * @param x The new value
	 */
	private void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the new value of y
	 * @param y The new value
	 */
	private void setY(int y) {
		this.y = y;
	}

}
