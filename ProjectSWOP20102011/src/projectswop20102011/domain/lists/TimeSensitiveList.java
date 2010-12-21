package projectswop20102011.domain.lists;

import java.util.HashSet;
import java.util.Iterator;
import projectswop20102011.domain.TimeSensitive;
import projectswop20102011.exceptions.InvalidDurationException;

/**
 * A list of objects that are time sensitive where every object is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar Every TimeSensitive in this TimeSensitiveList is unique.
 */
public class TimeSensitiveList implements Iterable<TimeSensitive> {

	/**
	 * The inner list to manage the time sensitive objects.
	 */
	private final HashSet<TimeSensitive> timeSensitives;

	/**
	 * Creating a new instance of an TimeSensitiveList. At this moment this list
	 * doesn't contain any object.
	 * @post The new TimeSensitiveList is a list with no elements in it.
	 *		|new.getTimeSensitives().size() == 0
	 */
	public TimeSensitiveList() {
		this.timeSensitives = new HashSet<TimeSensitive>();
	}

	/**
	 * Returns the list of objects that are time sensitive.
	 * @return The list of objects that are time sensitive.
	 */
	public HashSet<TimeSensitive> getTimeSensitives() {
		return (HashSet<TimeSensitive>) timeSensitives.clone();
	}

	/**
	 * Adds the given TimeSensitive to this list of time sensitive objects.
	 * If the given TimeSensitive object is already in the list, nothing happens.
	 * @param ub
	 *		The TimeSensitive to be appended to this list of time sensitive objects.
	 * @post This TimeSensitiveList contains the given TimeSensitive.
	 *		|new.getTimeSensitives().contains(ub)
	 */
	public void addTimeSensitive(TimeSensitive ub) {
		if (!getTimeSensitives().contains(ub)) {
			timeSensitives.add(ub);
		}
	}

	/**
	 * Generates an iterator to iterate over the list of time sensitive objects.
	 * @return An iterator to iterate of the list of time sensitive objects.
	 */
	@Override
	public Iterator<TimeSensitive> iterator() {
		return getTimeSensitives().iterator();
	}

	/**
	 * Moves the time forward for all instances in this TimeSensitiveList for the given amount of seconds.
	 * @param seconds
	 *		The given amount of seconds.
	 * @throws InvalidDurationException
	 *		If the given duration is invalid. I.e. the duration is negative.
	 */
	public void timeAhead(int seconds) throws InvalidDurationException {
		for (TimeSensitive ts : this) {
			ts.timeAhead(seconds);
		}
	}
}
