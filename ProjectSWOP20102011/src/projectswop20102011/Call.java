package projectswop20102011;

import java.util.*;
/**
 * A class of calls
 * 
 * @invar Each call must have a valid timestamp
 *        |hasValidTimestamp()
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class Call {

    /**
     * Variable registering the timestamp of this call
     */
    private final Date timestamp;

    /**
     * Initialize a new call with given timestamp
     *
     * @param timestamp
     *        the timestamp of the new call
     */
    public Call(Date timestamp){
        this.timestamp = timestamp;
    }

    /**
     * Return the timestamp of this call
     */
    public Date getTimestamp(){
        return timestamp;
    }

}
