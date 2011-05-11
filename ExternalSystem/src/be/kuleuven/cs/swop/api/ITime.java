package be.kuleuven.cs.swop.api;

/**
 * This interface provides operations to retrieve a concrete Time value, which consists
 * of hours and minutes. Days are kept track of using the hours value, which can
 * be larger than 23.
 * 
 * 
 * @author philippe
 *
 */
public interface ITime extends Comparable<ITime> {

	/**
	 * Returns the hours part of the timestamp. The value can be between 0 and 
	 * Integer.MAX_VALUE ([0-max]).
	 * @return the number of hours
	 */
	public int getHours();
	
	/**
	 * Returns the minutes part of the timestamp. The value can be between 0 and
	 * 59 ([0-59]).
	 * @return the number of minutes
	 */
	public int getMinutes();
	
	@Override
	public boolean equals(Object arg0);
	
}
