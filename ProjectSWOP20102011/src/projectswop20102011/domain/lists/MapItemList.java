package projectswop20102011.domain.lists;

import projectswop20102011.domain.validators.MapItemEvaluationCriterium;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import projectswop20102011.domain.MapItem;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidWithdrawalException;

/**
 * A list of mapitems where every mapitem is unique.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar Every MapItem in this MapItemList is unique.
 */
public class MapItemList implements Iterable<MapItem> {

    /**
     * The inner list to manage the mapitems
     */
    private final HashSet<MapItem> mapItems;

    /**
     * Creating a new instance of an MapItemList. At this moment this list
     * doesn't contain any object.
     *
     * @effect The new MapItemList is a list with no elements in it.
     */
    public MapItemList() {
        this.mapItems = new HashSet<MapItem>();
    }

    /**
     * Returns the list of mapitems.
     * @return The list of mapitems.
     */
    public HashSet<MapItem> getMapItems() {
        return (HashSet<MapItem>) mapItems.clone();
    }

    /**
     * Search for and return the MapItem in the MapItemList with a name equal to the given name.
     * @param name
     *		The name to compare with.
     * @return The MapItem in this list with a name equal to the name,
     * or null if no such MapItem can be found.
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
     * Returns all the Mapitems in this MapItemList that are valid to a certain MapItemCriterium.
     * @param criterium
     *		The criterium to validate potential solution on.
     * @return a list with all the MapItems in this MapItemList who are
     * validated by the MapItemCriterium.
     */
    public MapItemList getMapItemsByCriterium(MapItemEvaluationCriterium criterium) {
        MapItemList list = new MapItemList();
        for (MapItem u : this) {
            if (criterium.isValidMapItem(u)) {
                list.addMapItem(u);
            }
        }
        return list;
    }

    /**
     * Adds the given MapItem to this list if the given MapItem
     * is not already in this list of mapitems.
     * @param mi
     *		The MapItem to be appended to this list of mapitems.
     * @post This MapItemList contains the given MapItem.
     */
    public void addMapItem(MapItem mi) {
        if (!this.mapItems.contains(mi)) {
            this.mapItems.add(mi);
        }
    }

    /**
     * Generates an iterator to iterate over the list of mapitems.
     * @return An iterator to iterate of the list of mapitems.
     */
    @Override
    public Iterator<MapItem> iterator() {
        return mapItems.iterator();
    }

    /**
     * Sorts the MapItems to a given comparator.
     * @param <T>
     *		The class of the MapItem that must be sorted.
     * @param comparator
     *		The given comparator.
     * @return A list of MapItem correctly sorted.
     */
    public <T extends MapItem> ArrayList<T> sort(Comparator<T> comparator) {
        ArrayList<T> result = new ArrayList<T>();

        for (MapItem mi : getMapItems()) {
            try {
                T a = (T) mi;
                result.add(a);
            } catch (ClassCastException ex) {
            }
        }

        Collections.sort(result, comparator);
        return result;
    }
    public MapItem[] toArray () {
        return this.mapItems.toArray(new MapItem[0]);
    }
}
