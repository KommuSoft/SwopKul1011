package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.ITime;

/**
 * A class that represents a timestamp.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @invar Eacht timestamp has a valid amount of hours and a valid amount of minutes. 
 *		|areValidHours(hours) && areValidMinutes(minutes)
 */
public class Time implements ITime {

	/**
	 * A variable registering the hours of this timestamp.
	 */
	private final int hours;
	/**
	 * A variable registering the minuts of this timestamp.
	 */
	private final int minutes;

	/**
	 * Constructs a new timestamp with the given hours and minutes. The hours and minutes must be valid.
	 * @param hours
	 *		The hours of this timestamp.
	 * @param minutes
	 *		The minutes of this timestamp.
	 * @post The new hours are equal to the given hours.
	 *		|new.getHours.equals(hours)
	 * @post The new minutes are equal to the given minutes.
	 *		|new.getMinutes.equals(minutes)
	 */
	public Time(int hours, int minutes) {
		if (areValidHours(hours) && areValidMinutes(minutes)) {
			this.hours = hours;
			this.minutes = minutes;
		} else {
			throw new IllegalArgumentException("Invalid time specification");
		}
	}

	/**
	 * Returns the hours of this timestamp.
	 * @return The hours of this timestamp.
	 */
	@Override
	public int getHours() {
		return hours;
	}

	
	/**
	 * Returns the minutes of this timestamp.
	 * @return The minutes of this timestamp.
	 */
	@Override
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Compares two timestamps numerically
	 * @param o
	 *		The other timestamp to be compared.
	 * @return The value 0 if the two timestamps are equal. A negative value if this timestamp is before the given timestamp.
	 *		A positive value if the timestamp is after the given timestamp.
	 */
	@Override
	public int compareTo(ITime o) {
		if (o == null) {
			throw new IllegalArgumentException("Time to compare to cannot be null");
		}

		Integer time1 = Integer.valueOf(getHours() * 60 + getMinutes());
		Integer time2 = Integer.valueOf(o.getHours() * 60 + o.getMinutes());
		return time1.compareTo(time2);
	}

	/**
	 * Checks if a given parameter is a valid value for the hours of a timestamp.
	 * @param hours
	 *		The parameter to check.
	 * @return True if the given parameter is valid. I.e. it is between 0 and 23 (both boundaries inclusive).
	 */
	public static boolean areValidHours(int hours) {
		return !(hours < 0) && !(hours > 23);
	}

	/**
	 * Checks if a given parameter is a valid value for the minutes of a timestamp.
	 * @param minutes
	 *		The parameter to check.
	 * @return True if the given parameter is valid. I.e. it is between 0 and 59 (both boundaries inclusive).
	 */
	public static boolean areValidMinutes(int minutes) {
		return !(minutes < 0) && !(minutes > 59);
	}

	/**
	 * Returns a textual representation of this time.
	 * @return A textual representation of this time.
	 */
	public String toString(){
		return getHours() + "h" + getMinutes() + "m";
	}
}
