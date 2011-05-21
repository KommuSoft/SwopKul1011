package projectswop20102011.domain.lists;

import java.util.HashSet;
import java.util.Iterator;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.validators.DisasterEvaluationCriterium;
import projectswop20102011.exceptions.InvalidAddedDisasterException;

/**
 * A list of disasters where every disaster is unique.
 * @invar Every Disaster in this DisasterList is unique.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class DisasterList implements Iterable<Disaster> {

	/**
	 * The inner list to manage the disasters.
	 */
	private final HashSet<Disaster> disasters;

	/**
	 * Creates a new instance of an DisasterList. At this moment this list
	 * doesn't contain any disaster.
	 * @post The inner list that manages the disasters is initialized.
	 *		| disasters = new HashSet<Disaster>()
	 */
	public DisasterList() {
		disasters = new HashSet<Disaster>();
	}

	/**
	 * Returns the list of disasters.
	 * @return The list of disasters.
	 */
	public HashSet<Disaster> getDisasters() {
		return (HashSet<Disaster>) disasters.clone();
	}

	/**
	 * Adds the given disaster to this list of disasters if the given disaster
	 * is not already in this list of disasters.
	 * @param disaster
	 *		Disaster to be appended to this list of disasters.
	 * @post This DisasterList contains the given Disaster.
	 *		| getDisasters().contains(disaster)
	 */
	public void addDisaster(Disaster disaster) {
		if (!getDisasters().contains(disaster)) {
			disasters.add(disaster);
		}
	}

	/**
	 * Returns all the disasters in this DisasterList that are valid to a certain DisasterCriterium.
	 * @param criterium
	 *		The criterium to validate a potential solution on.
	 * @return A list with all the disasters in this DisasterList who are validated by the DisasterCriterium.
	 * @throws InvalidAddedDisasterException
	 *		If a disaster contains no emergencies.
	 */
	public DisasterList getDisastersByCriterium(DisasterEvaluationCriterium criterium) throws InvalidAddedDisasterException {
		DisasterList list = new DisasterList();
		for (Disaster d : this) {
			if (criterium.isValid(d)) {
				list.addDisaster(d);
			}
		}
		return list;
	}

	/**
	 * Generates an iterator to iterate over the list of disasters.
	 * @return An iterator to iterate of the list of disasters.
	 */
	@Override
	public Iterator<Disaster> iterator() {
		return this.disasters.iterator();
	}

	/**
	 * Returns an array of all the disasters in this DisasterList.
	 * @return An array of all the disasters in this DisasterList.
	 */
	public Disaster[] toArray() {
		return this.disasters.toArray(new Disaster[0]);
	}
}
