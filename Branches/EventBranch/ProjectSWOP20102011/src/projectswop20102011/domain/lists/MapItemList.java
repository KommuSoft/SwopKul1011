package projectswop20102011.domain.lists;

import projectswop20102011.domain.validators.MapItemValidator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import projectswop20102011.domain.MapItem;

/**
 * A list of mapitems where every mapitem is unique.
 * @param <T>
 *		The type of MapItems the MapItemList is holding.
 * @invar Every MapItem in this MapItemList is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class MapItemList<T extends MapItem> implements Iterable<T> {

	/**
	 * The inner list to manage the mapitems
	 */
	private final HashSet<T> mapItems;

	/**
	 * Creating a new instance of an MapItemList. At this moment this list
	 * doesn't contain any object.
	 *
	 * @effect The new MapItemList is a list with no elements in it.
	 *		|mapItems = new HashSet<T>()
	 */
	public MapItemList() {
		this.mapItems = new HashSet<T>();
	}

	/**
	 * Returns the list of mapitems.
	 * @return The list of mapitems.
	 */
	public HashSet<T> getMapItems() {
		return (HashSet<T>) mapItems.clone();
	}

	/**
	 * Search for and return the MapItem in the MapItemList with a name equal to the given name.
	 * @param name
	 *		The name to compare with.
	 * @return The MapItem in this list with a name equal to the name, or null if no such MapItem can be found.
	 */
	public MapItem getMapItemFromName(String name) {
		for (MapItem mi : this) {
			if (mi.getName().equals(name)) {
				return mi;
			}
		}
		return null;
	}

	/**
	 * Returns all the Mapitems in this MapItemList that are valid to a certain MapItemValidator
	 * @param <Q>
	 *		The type of new MapItemList that will be generated. This type is relevant to the MapItemCriterium.
	 * @param validator
	 *		A validator to validate an item in this MapItemList.
	 * @return a list with all the MapItems in this MapItemList who are validated by the MapItemValidator
	 */
	public <Q extends MapItem> MapItemList<Q> getSubMapItemListByValidator(MapItemValidator<Q> validator) {
		MapItemList<Q> list = new MapItemList<Q>();
		for (MapItem u : this) {
			if (validator.isValid(u)) {
				list.addMapItem((Q) u);
			}
		}
		return list;
	}

	/**
	 * Adds the given MapItem to this list if the given MapItem
	 * is not already in this list of mapitems.
	 * @param mapItem
	 *		The MapItem to be appended to this list of mapitems.
	 * @effect This MapItemList contains the given MapItem.
	 */
	public void addMapItem(T mapItem) {
		this.takeMapItems().add(mapItem);
	}

	/**
	 * Generates an iterator to iterate over the list of mapitems.
	 * @return An iterator to iterate of the list of mapitems.
	 */
	@Override
	public Iterator<T> iterator() {
		return mapItems.iterator();
	}

	/**
	 * Sorts the MapItems to a given comparator.
	 * @param comparator
	 *		The given comparator.
	 * @return A list of MapItem correctly sorted.
	 */
	public ArrayList<T> getSortedCopy(Comparator<? super T> comparator) {
		ArrayList<T> result = this.toArrayList();
		Collections.sort(result, comparator);
		return result;
	}

	/**
	 * Returns an ArrayList<T> containing all the items in the MapItemList.
	 * @return An ArrayList containing all the items in the MapItemList.
	 */
	public ArrayList<T> toArrayList() {
		return new ArrayList<T>(this.takeMapItems());
	}

	/**
	 * Returns the real HashSet of items in this list. This method is private.
	 * @return The real HashSet of items in this list (not a clone).
	 * @see #getMapItems
	 */
	private HashSet<T> takeMapItems() {
		return this.mapItems;
	}
}
