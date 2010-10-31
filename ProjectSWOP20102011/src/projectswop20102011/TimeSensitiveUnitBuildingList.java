package projectswop20102011;

import java.util.ArrayList;
import java.util.Iterator;
import projectswop20102011.exceptions.InvalidTimeSensitiveUnitBuildingException;

/**
 * A list of units and buildings-that are time sensitive-where every unit and building is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar Every TimeSensitiveUnitBuilding in this TimeSensitiveUnitBuildingList is unique.
 */
public class TimeSensitiveUnitBuildingList implements Iterable<TimeSensitiveUnitBuilding>{

    /**
     * The inner list to manage the time sensitive units and buildings.
     */
    private final ArrayList<TimeSensitiveUnitBuilding> timeSensitiveUnitBuildings;

    /**
     * Creating a new instance of an TimeSensitiveUnitBuildingList. At this moment this list
     * doesn't contain any object.
	 * @effect The new TimeSensitiveUnitBuildingList is a list with no elements in it.
     */
    public TimeSensitiveUnitBuildingList() {
        this.timeSensitiveUnitBuildings = new ArrayList<TimeSensitiveUnitBuilding>();
    }

    /**
     * Returns the list of units and buildings that are time sensitive.
     * @return The list of units and buildings that are time sensitive.
     */
    public ArrayList<TimeSensitiveUnitBuilding> getTimeSensitiveUnitBuildings() {
        return (ArrayList<TimeSensitiveUnitBuilding>) timeSensitiveUnitBuildings.clone();
    }

    /**
     * Adds the given TimeSensitiveUnitBuilding to this list of time sensitive
	 * units and buildings if the given TimeSensitiveUnitBuilding is not already
	 * in this list of time sensitive units and buildings.
     * @param ub
     *		TimeSensitiveUnitBuilding to be appended to this list of time
	 *		sensitive units and buildings.
     * @throws InvalidTimeSensitiveUnitBuildingException
     *		If the given TimeSensitiveUnitBuilding is already in this TimeSensitiveUnitBuildingList.
     * @post This TimeSensitiveUnitBuildingList contains the given TimeSensitiveUnitBuilding.
     */
    void addTimeSensitiveUnitBuilding(TimeSensitiveUnitBuilding ub) throws InvalidTimeSensitiveUnitBuildingException {
        for (int i = 0; i < getTimeSensitiveUnitBuildings().size(); ++i) {
            if (getTimeSensitiveUnitBuildings().get(i).getName().equals(ub.getName())) {
                throw new InvalidTimeSensitiveUnitBuildingException("Invalid unit or building.");
            }
        }
        timeSensitiveUnitBuildings.add(ub);
    }

    /**
     * Generates an iterator to iterate over the list of time sensitive units and buildings.
     * @return An iterator to iterate of the list of time sensitive units and buildings.
     */
    @Override
    public Iterator<TimeSensitiveUnitBuilding> iterator() {
        return timeSensitiveUnitBuildings.iterator();
    }
}