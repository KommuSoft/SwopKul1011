package projectswop20102011.domain.lists;

import java.util.HashSet;
import java.util.Iterator;
import projectswop20102011.domain.Sendable;
import projectswop20102011.domain.validators.SendableEvaluationCriterium;

/**
 * A list of sendables where every sendable is unique.
 * @invar Every Sendable in this SendableList is unique.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class SendableList<T extends Sendable> implements Iterable<T> {

	/**
	 * The inner list to manage the sendables.
	 */
	private final HashSet<T> sendables;

	/**
	 * Creates a new instance of an SendableList. At this moment this list
	 * doesn't contain any sendable.
	 * @post The inner list that manages the sendables is initialized.
	 *		| sendables = new HashSet<T>()
	 */
	public SendableList() {
		sendables = new HashSet<T>();
	}

	/**
	 * Returns the list of sendables.
	 * @return The list of sendables.
	 */
	public HashSet<T> getSendables() {
		return (HashSet<T>) sendables.clone();
	}

	/**
	 * Adds the given sendable to this list of sendables if the given sendable
	 * is not already in this list of sendables.
	 * @param sendable
	 *		Sendable to be appended to this list of sendables.
	 * @post This SendableList contains the given Sendables.
	 *		| getSendables().contains(sendable)
	 */
	public void addSendable(T sendable) {
		if (!getSendables().contains(sendable)) {
			sendables.add(sendable);
		}
	}

	/**
	 * Returns all the sendables in this SendableList that are valid to a certain SendableEvaluationCriterium.
	 * @param criterium
	 *		The criterium to validate a potential solution on.
	 * @return A list with all the sendables in this SendableList who are validated by the SendableEvaluationCriterium.
	 */
	public SendableList<T> getSendablesByCriterium(SendableEvaluationCriterium<T> criterium) {
		SendableList<T> list = new SendableList<T>();
		for (T s : this) {
			if (criterium.isValidSendable(s)) {
				list.addSendable(s);
			}
		}
		return list;
	}

	/**
	 * Generates an iterator to iterate over the list of sendables.
	 * @return An iterator to iterate of the list of sendables.
	 */
	@Override
	public Iterator<T> iterator() {
		return this.sendables.iterator();
	}

	/**
	 * Returns an array of all the sendables in this SendableList.
	 * @return An array of all the sendables in this SendableList.
	 */
	public T[] toArray() {
		return (T[]) sendables.toArray();
		//return this.sendables.toArray(new T[0]);
	}
}
