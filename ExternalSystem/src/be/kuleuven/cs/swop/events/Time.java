package be.kuleuven.cs.swop.events;

import be.kuleuven.cs.swop.api.ITime;

/**
 * A concrete implementation of a Time consisting of hours and minutes.
 * 
 * @author philippe
 *
 */
public class Time implements ITime {

	/**
	 * The number of hours in the timestamp
	 */
	private int hours = 0;
	/**
	 * The number of minutes in the timestamp.
	 */
	private int minutes = 0;
	
	/**
	 * Creates a new Time with the given minutes and hours
	 * @param hours The number of hours in the timestamp
	 * @param minutes The number of minutes in the timestamp
	 */
	public Time(int hours, int minutes) {
		setHours(hours);
		setMinutes(minutes);
	}
	
	@Override
	public int compareTo(ITime arg0) {
		if(arg0 == null)
			throw new IllegalArgumentException("Time to compare to cannot be null");
		
		Double current = getHours() + ((double)getMinutes()) / 60;
		Double other = arg0.getHours() + ((double)arg0.getMinutes()) / 60;
		
		return current.compareTo(other);
	}
	
	@Override
	public boolean equals(Object arg0) {
		try {
			ITime t = (ITime)arg0;
			return getHours() == t.getHours() && getMinutes() == t.getMinutes();
		}
		catch(ClassCastException e) {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return getHours() + "h " + getMinutes() + "m";
	}
	
	/*
	 * Getters and Setters
	 */
	
	@Override
	public int getHours() {
		return this.hours;
	}

	/**
	 * Sets the number of hours to the given value
	 * @param hours the hours to set
	 */
	private void setHours(int hours) {
		this.hours = hours;
	}

	/** 
	 * Sets the number of minutes to the given value
	 * @param minutes the minutes to set
	 */
	private void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	@Override
	public int getMinutes() {
		return this.minutes;
	}

}
