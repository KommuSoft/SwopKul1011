package projectswop20102011;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A list of units and buildings where every unit and building is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar Every UnitBuilding in this UnitBuildingList is unique.
 */
public class UnitBuildingList implements Iterable<UnitBuilding> {

    /**
     * The inner list to manage the units and buildings.
     */
    private final HashSet<UnitBuilding> unitBuildings;

    /**
     * Creating a new instance of an UnitBuildingList. At this moment this list
     * doesn't contain any object.
     * @effect The new UnitBuildingList is a list with no elements in it.
     */
    public UnitBuildingList() {
        this.unitBuildings = new HashSet<UnitBuilding>();
    }

    /**
     * Returns the list of units and buildings.
     * @return The list of units and buildings.
     */
    public HashSet<UnitBuilding> getUnitBuildings() {
        return (HashSet<UnitBuilding>) unitBuildings.clone();
    }

    /**
     * Search for and return the UnitBuilding in the UnitBuildingList with a name equal to the given name.
     * @param name
	 *		The name to compare with.
     * @return The UnitBuilding in this list with a name equal to the name, or null if no such UnitBuilding can be found.
     */
    public UnitBuilding getUnitBuildingFromName (String name) {
        for(UnitBuilding ub : this) {
            if(ub.getName().equals(name)) {
                return ub;
            }
        }
        return null;
    }

    /**
     * Returns all the UnitBuildings in this UnitBuildingList that are valid to a certain UnitBuildingCriterium.
     * @param criterium
     *		The criterium to validate potential solution on.
     * @return a list with all the UnitBuildings in this UnitBuildingList who are validated by the UnitBuildingCriterium.
     */
    public UnitBuildingList getUnitBuildingsByCriterium(UnitBuildingEvaluationCriterium criterium) {
        UnitBuildingList list = new UnitBuildingList();
        for (UnitBuilding u : this) {
            if (criterium.isValidUnitBuilding(u)) {
                list.addUnitBuilding(u);
            }
        }
        return list;
    }

    /**
     * Adds the given UnitBuilding to this list if the given UnitBuilding
     * is not already in this list of units and buildings.
     * @param ub
     *		UnitBuilding to be appended to this list of units and buildings.
     * @post This UnitBuildingList contains the given UnitBuilding.
     */
    void addUnitBuilding(UnitBuilding ub) {
        if(!this.unitBuildings.contains(ub)) {
			this.unitBuildings.add(ub);
		}
    }

    /**
     * Generates an iterator to iterate over the list of units and buildings.
     * @return An iterator to iterate of the list of units and buildings.
     */
    @Override
    public Iterator<UnitBuilding> iterator() {
        return unitBuildings.iterator();
    }

    /**
     * Sorts the UnitBuildings to a given comparator.
     * @param <T>
     *		The class of the UnitBuilding that must be sorted.
     * @param comparator
     *		The given comparator.
     * @return A list of UnitBuilding correctly sorted.
     */
	//TODO Dit moet nog ergens gebruikt worden.
    public <T extends UnitBuilding> ArrayList<T> sort(Comparator<T> comparator) {
        ArrayList<T> result = new ArrayList<T>();

        for (UnitBuilding ub : getUnitBuildings()) {
            try {
                T a = (T) ub;
                result.add(a);
            } catch (ClassCastException ex) {
            }
        }

        Collections.sort(result, comparator);
        return result;
    }
}
