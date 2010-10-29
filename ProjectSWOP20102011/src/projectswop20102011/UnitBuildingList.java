package projectswop20102011;

import java.util.ArrayList;
import java.util.Iterator;
import projectswop20102011.exceptions.InvalidUnitBuildingException;

/**
 * A list of units and buildings where every unit and building is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class UnitBuildingList implements Iterable<UnitBuilding>{

    /**
     * The inner list to manage the units and buildings.
     */
    private final ArrayList<UnitBuilding> unitBuildings;

    /**
     * Creating a new instance of an UnitBuildingList. At this moment this list
     * doesn't contain any object.
     */
    public UnitBuildingList() {
        this.unitBuildings = new ArrayList<UnitBuilding>();
    }

    /**
     * Returns the list of units and buildings.
     * @return The list of units and buildings.
     */
    public ArrayList<UnitBuilding> getUnitBuildings() {
        return (ArrayList<UnitBuilding>) unitBuildings.clone();
    }

    /**
     * Adds the given unitBuilding to this list of units and buildings if
	 * the given unitBuilding is not already in this list of units and buildings.
     * @param ub
     *		unitBuilding to be appended to this list of units and buildings.
     * @throws InvalidUnitBuildingException
     *		If the given unitBuilding is already in this UnitBuildingList.
     * @post This UnitBuildingList contains the given unitBuilding.
     */
    void addUnitBuilding(UnitBuilding ub) throws InvalidUnitBuildingException {
        for (int i = 0; i < getUnitBuildings().size(); ++i) {
            if (getUnitBuildings().get(i).getName().equals(ub.getName())) {
                throw new InvalidUnitBuildingException("Invalid unit or building.");
            }
        }
        unitBuildings.add(ub);
    }

    /**
     * Generates an iterator to iterate over the list of units and buildings.
     * @return An iterator to iterate of the list of units and buildings.
     */
    @Override
    public Iterator<UnitBuilding> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}