package projectswop20102011;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A list of time sensitive objects.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class TimeSensitiveList implements Iterable<TimeSensitive>{

    /**
     * The inner list to manage the time sensitive objects.
     */
    private final ArrayList<TimeSensitive> timeSensitives;

    /**
     * Creating a new instance of an TimeSensitiveList. At this moment this list
     * doesn't contain any object.
     */
    public TimeSensitiveList() {
        this.timeSensitives = new ArrayList<TimeSensitive>();
    }

    /**
     * Returns the list of time sensitives.
     * @return The list of time sensitives.
     */
    public ArrayList<TimeSensitive> getTimeSensitives() {
        return (ArrayList<TimeSensitive>) timeSensitives.clone();
    }

    /**
     * Adds the given time sensitive object to this list of time sensitives.
     * @param ts
     *		Time sensitive object to be appended to this list of time sensitives.
     * @post This TimeSensitiveList contains the given time sensitive object.
     */
    void addTimeSensitive(TimeSensitive ts){
		//TODO: Hier nog controleren of ts al in de lijst zit?
        timeSensitives.add(ts);
    }

    /**
     * Generates an iterator to iterate over the list of time sensitives.
     * @return An iterator to iterate of the list of time sensitives.
     */
    @Override
    public Iterator<TimeSensitive> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}