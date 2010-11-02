package projectswop20102011;

import java.util.ArrayList;
import java.util.Iterator;
import projectswop20102011.exceptions.InvalidUnitBuildingException;

/**
 * A list of units and buildings-that are not time sensitive-where every unit and building is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar Every NotTimeSensitiveUnitBuilding in this UnitBuildingList is unique.
 */
public class UnitBuildingList implements Iterable<UnitBuilding>{

    /**
     * The inner list to manage the not time sensitive units and buildings.
     */
    private final ArrayList<UnitBuilding> unitBuildings;

    /**
     * Creating a new instance of an UnitBuildingList. At this moment this list
     * doesn't contain any object.
	 * @effect The new UnitBuildingList is a list with no elements in it.
     */
    public UnitBuildingList() {
        this.unitBuildings = new ArrayList<UnitBuilding>();
    }

    /**
     * Returns the list of units and buildings that are not time sensitive.
     * @return The list of units and buildings that are not time sensitive.
     */
    public ArrayList<UnitBuilding> getUnitBuildings() {
        return (ArrayList<UnitBuilding>) unitBuildings.clone();
    }

    /**
     * Adds the given NotTimeSensitiveUnitBuilding to this list of not time
	 * sensitive units and buildings if the given NotTimeSensitiveUnitBuilding
	 * is not already in this list of not time sensitive units and buildings.
     * @param ub
     *		NotTimeSensitiveUnitBuilding to be appended to this list of not time
	 *		sensitive units and buildings.
     * @throws InvalidNotTimeSensitiveUnitBuildingException
     *		If the given NotTimeSensitiveUnitBuilding is already in this UnitBuildingList.
     * @post This UnitBuildingList contains the given NotTimeSensitiveUnitBuilding.
     */
    void addUnitBuilding(UnitBuilding ub) throws InvalidUnitBuildingException {
        for (int i = 0; i < getUnitBuildings().size(); ++i) {
            if (getUnitBuildings().get(i) ==ub) {
                throw new InvalidUnitBuildingException("Invalid unit or building.");
            }
        }
        unitBuildings.add(ub);
    }

    /**
     * Generates an iterator to iterate over the list of not time sensitive units and buildings.
     * @return An iterator to iterate of the list of not time sensitive units and buildings.
     */
    @Override
    public Iterator<UnitBuilding> iterator() {
        return unitBuildings.iterator();
    }
}