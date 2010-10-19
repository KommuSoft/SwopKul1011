package projectswop20102011;

import java.util.Date;
/**
 * A class that represents a call.
 * 
 * @invar Each call must have a valid timestamp.
 *        | hasValidTimestamp()
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class Call {

    /**
     * Variable registering the timestamp of this call.
     */
    private final Date timestamp;

    /**
     * Initialize a new call with given timestamp.
     *
     * @param timestamp
	 *		The timestamp of the new call.
	 * @throws InvalidTimestampException
	 *		If the given timestamp is an invalid timestamp. For the validation rules see {@link #isValidTimestamp(Date)}.
     */
    public Call(Date timestamp) throws InvalidTimestampException {
		if(isValidTimestamp(timestamp)){
			this.timestamp = (Date) timestamp.clone();
		} else {
			throw new InvalidTimestampException();
		}
    }

    /**
     * Return the timestamp of this call.
     * @return The timestamp of this call.
     */
    public Date getTimestamp(){
        return timestamp;
    }

	/**
	 * Returns true if the given timestamp is valid. A timestamp is valid if it
	 *		is before the current time.
	 * @param timestamp
	 *		The timestamp that must be checked.
	 * @return
	 *		True if the timestamp is before the current time; false otherwise.
	 */
	public static boolean isValidTimestamp(Date timestamp){
		return (timestamp.before(new Date()) || timestamp.equals(new Date()));
	}

	/**
	 * Checks if the current timestamp is a valid timestamp. For the validation rules see {@link #isValidTimestamp(Date)}.
	 * @return
	 *		True if the current timestamp is a valid timestamp; false otherwise.
	 */
	public boolean hasValidTimestamp(){
		return isValidTimestamp(getTimestamp());
	}
}
