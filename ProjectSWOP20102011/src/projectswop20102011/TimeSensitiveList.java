package projectswop20102011;

import java.util.ArrayList;
import java.util.Iterator;
import projectswop20102011.exceptions.InvalidTimeSensitiveException;

/**
 * A list of objects that are time sensitive where every object is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar Every TimeSensitive in this TimeSensitiveList is unique.
 */
public class TimeSensitiveList implements Iterable<TimeSensitive>{

    /**
     * The inner list to manage the time sensitive objects.
     */
    private final ArrayList<TimeSensitive> timeSensitives;

    /**
     * Creating a new instance of an TimeSensitiveList. At this moment this list
     * doesn't contain any object.
	 * @effect The new TimeSensitiveList is a list with no elements in it.
     */
    public TimeSensitiveList() {
        this.timeSensitives = new ArrayList<TimeSensitive>();
    }

    /**
     * Returns the list of objects that are time sensitive.
     * @return The list of objects that are time sensitive.
     */
    public ArrayList<TimeSensitive> getTimeSensitives() {
        return (ArrayList<TimeSensitive>) timeSensitives.clone();
    }

    /**
     * Adds the given TimeSensitive to this list of time sensitive objects if the given TimeSensitive
	 * is not already in this list of time sensitive objects.
     * @param ub
     *		TimeSensitive to be appended to this list of time sensitive objects.
     * @throws InvalidTimeSensitiveException
     *		If the given TimeSensitive is already in this TimeSensitiveList.
     * @post This TimeSensitiveList contains the given TimeSensitive.
     */
    void addTimeSensitive(TimeSensitive ub) throws InvalidTimeSensitiveException {
        for (int i = 0; i < getTimeSensitives().size(); ++i) {
            if (getTimeSensitives().get(i) == ub) {
                throw new InvalidTimeSensitiveException("Invalid time sensitive.");
            }
        }
        timeSensitives.add(ub);
    }

    /**
     * Generates an iterator to iterate over the list of time sensitive objects.
     * @return An iterator to iterate of the list of time sensitive objects.
     */
    @Override
    public Iterator<TimeSensitive> iterator() {
        return timeSensitives.iterator();
    }
}