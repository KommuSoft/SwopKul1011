package projectswop20102011;

import java.util.ArrayList;
import java.util.Iterator;
import projectswop20102011.exceptions.InvalidNotTimeSensitiveUnitBuildingException;

/**
 * A list of units and buildings-that are not time sensitive-where every unit and building is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar Every NotTimeSensitiveUnitBuilding in this NotTimeSensitiveUnitBuildingList is unique.
 */
public class NotTimeSensitiveUnitBuildingList implements Iterable<NotTimeSensitiveUnitBuilding>{

    /**
     * The inner list to manage the not time sensitive units and buildings.
     */
    private final ArrayList<NotTimeSensitiveUnitBuilding> notTimeSensitiveUnitBuildings;

    /**
     * Creating a new instance of an NotTimeSensitiveUnitBuildingList. At this moment this list
     * doesn't contain any object.
     */
    public NotTimeSensitiveUnitBuildingList() {
        this.notTimeSensitiveUnitBuildings = new ArrayList<NotTimeSensitiveUnitBuilding>();
    }

    /**
     * Returns the list of units and buildings that are not time sensitive.
     * @return The list of units and buildings that are not time sensitive.
     */
    public ArrayList<NotTimeSensitiveUnitBuilding> getNotTimeSensitiveUnitBuildings() {
        return (ArrayList<NotTimeSensitiveUnitBuilding>) notTimeSensitiveUnitBuildings.clone();
    }

    /**
     * Adds the given NotTimeSensitiveUnitBuilding to this list of not time
	 * sensitive units and buildings if the given NotTimeSensitiveUnitBuilding
	 * is not already in this list of not time sensitive units and buildings.
     * @param ub
     *		NotTimeSensitiveUnitBuilding to be appended to this list of not time
	 *		sensitive units and buildings.
     * @throws InvalidNotTimeSensitiveUnitBuildingException
     *		If the given NotTimeSensitiveUnitBuilding is already in this NotTimeSensitiveUnitBuildingList.
     * @post This NotTimeSensitiveUnitBuildingList contains the given NotTimeSensitiveUnitBuilding.
     */
    void addNotTimeSensitiveUnitBuilding(NotTimeSensitiveUnitBuilding ub) throws InvalidNotTimeSensitiveUnitBuildingException {
        for (int i = 0; i < getNotTimeSensitiveUnitBuildings().size(); ++i) {
            if (getNotTimeSensitiveUnitBuildings().get(i).getName().equals(ub.getName())) {
                throw new InvalidNotTimeSensitiveUnitBuildingException("Invalid unit or building.");
            }
        }
        notTimeSensitiveUnitBuildings.add(ub);
    }

    /**
     * Generates an iterator to iterate over the list of not time sensitive units and buildings.
     * @return An iterator to iterate of the list of not time sensitive units and buildings.
     */
    @Override
    public Iterator<NotTimeSensitiveUnitBuilding> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}