package projectswop20102011;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import projectswop20102011.exceptions.InvalidUnitBuildingException;

/**
 * A list of units and buildings where every unit and building is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar Every UnitBuilding in this UnitBuildingList is unique.
 */
public class UnitBuildingList implements Iterable<UnitBuilding>{

    /**
     * The inner list to manage the units and buildings.
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
     * Returns the list of units and buildings.
     * @return The list of units and buildings.
     */
    public ArrayList<UnitBuilding> getUnitBuildings() {
        return (ArrayList<UnitBuilding>) unitBuildings.clone();
    }

	/**
     * Returns all the UnitBuildings in this UnitBuildingList that are valid to a certain UnitBuildingCriterium.
     * @param criterium
	 *		The criterium to validate potential solution on.
     * @return a list with all the UnitBuildings in this UnitBuildingList who are validated by the UnitBuildingCriterium.
     */
    public UnitBuildingList getUnitBuildingsByCriterium(UnitBuildingEvaluationCriterium criterium) throws InvalidUnitBuildingException {
        UnitBuildingList list = new UnitBuildingList();
        for(UnitBuilding u : this) {
            if(criterium.isValidUnitBuilding(u)) {
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
     * @throws InvalidUnitBuildingException
     *		If the given UnitBuilding is already in this UnitBuildingList.
     * @post This UnitBuildingList contains the given UnitBuilding.
     */
    void addUnitBuilding(UnitBuilding ub) throws InvalidUnitBuildingException {
        for (int i = 0; i < getUnitBuildings().size(); ++i) {
            if (getUnitBuildings().get(i) == ub) {
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
        return unitBuildings.iterator();
    }

	//@TODO: zelfde distance is probleem
	public ArrayList<Hospital> sort(Emergency e){
		HashMap<Double, Hospital> hm = new HashMap<Double, Hospital>();
		DistanceCalculator dc = new DistanceCalculator(e.getLocation());

		for(int i=0; i<getUnitBuildings().size(); ++i){
			double distance = dc.getDistanceTo(getUnitBuildings().get(i).getHomeLocation());
			hm.put(distance, (Hospital) getUnitBuildings().get(i));
		}
		ArrayList<Hospital> hospitals = new ArrayList<Hospital>();
		for(Hospital h: hm.values()){
			System.out.println(h);
			hospitals.add(h);
		}

		return hospitals;
	}
}