package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A list of units and buildings where every unit and building is unique.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar Every MapItem in this MapItemList is unique.
 */
public class MapItemList implements Iterable<MapItem> {

    /**
     * The inner list to manage the units and buildings.
     */
    private final HashSet<MapItem> unitBuildings;

    /**
     * Creating a new instance of an MapItemList. At this moment this list
     * doesn't contain any object.
     *
     * @effect The new MapItemList is a list with no elements in it.
     */
    public MapItemList() {
        this.unitBuildings = new HashSet<MapItem>();
    }

    /**
     * Returns the list of units and buildings.
     * @return The list of units and buildings.
     */
    public HashSet<MapItem> getUnitBuildings() {
        return (HashSet<MapItem>) unitBuildings.clone();
    }

    /**
     * Search for and return the MapItem in the MapItemList with a name equal to the given name.
     * @param name
     *		The name to compare with.
     * @return The MapItem in this list with a name equal to the name,
     * or null if no such MapItem can be found.
     */
    public MapItem getUnitBuildingFromName(String name) {
        for (MapItem ub : this) {
            if (ub.getName().equals(name)) {
                return ub;
            }
        }
        return null;
    }

    /**
     * Returns all the UnitBuildings in this MapItemList that are valid to a certain UnitBuildingCriterium.
     * @param criterium
     *		The criterium to validate potential solution on.
     * @return a list with all the UnitBuildings in this MapItemList who are
     * validated by the UnitBuildingCriterium.
     */
    public MapItemList getUnitBuildingsByCriterium(MapItemEvaluationCriterium criterium) {
        MapItemList list = new MapItemList();
        for (MapItem u : this) {
            if (criterium.isValidUnitBuilding(u)) {
                list.addUnitBuilding(u);
            }
        }
        return list;
    }

    /**
     * Adds the given MapItem to this list if the given MapItem
     * is not already in this list of units and buildings.
     * @param ub
     *		The MapItem to be appended to this list of units and buildings.
     * @post This MapItemList contains the given MapItem.
     */
    public void addUnitBuilding(MapItem ub) {
        if (!this.unitBuildings.contains(ub)) {
            this.unitBuildings.add(ub);
        }
    }

    /**
     * Generates an iterator to iterate over the list of units and buildings.
     * @return An iterator to iterate of the list of units and buildings.
     */
    @Override
    public Iterator<MapItem> iterator() {
        return unitBuildings.iterator();
    }

    /**
     * Sorts the UnitBuildings to a given comparator.
     * @param <T>
     *		The class of the MapItem that must be sorted.
     * @param comparator
     *		The given comparator.
     * @return A list of MapItem correctly sorted.
     */
    public <T extends MapItem> ArrayList<T> sort(Comparator<T> comparator) {
        ArrayList<T> result = new ArrayList<T>();

        for (MapItem ub : getUnitBuildings()) {
            try {
                T a = (T) ub;
                result.add(a);
            } catch (ClassCastException ex) {
            }
        }

        Collections.sort(result, comparator);
        return result;
    }
    public MapItem[] toArray () {
        return this.unitBuildings.toArray(new MapItem[0]);
    }

}
