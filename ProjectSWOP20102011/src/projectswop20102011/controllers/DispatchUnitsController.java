package projectswop20102011.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import projectswop20102011.domain.validators.AvailableUnitsMapItemEvaluationCriterium;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.MapItem;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.MapItemEvaluationCriterium;
import projectswop20102011.domain.validators.UnitToEmergencyDistanceComparator;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsController extends Controller {

	public DispatchUnitsController(World world) throws InvalidWorldException {
		super(world);
	}

	public Emergency getEmergencyFromId(long id) {
		return this.getWorld().getEmergencyList().getEmergencyFromId(id);
	}

	public List<Unit> getUnitsByPolicy(Emergency emergency) throws InvalidEmergencyException {
		MapItemEvaluationCriterium criterium = new AvailableUnitsMapItemEvaluationCriterium();
		for(MapItem u : getWorld().getMapItemList().getMapItems()){
		}
		HashSet<MapItem> mapItems = getWorld().getMapItemList().getMapItemsByCriterium(criterium).getMapItems();
		ArrayList<Unit> availableUnits = new ArrayList<Unit>();
		for (MapItem u : mapItems) {
			availableUnits.add((Unit) u);
		}
		ArrayList<Unit> unitsByPolicy = new ArrayList<Unit>();
		for (Unit u : emergency.getPolicyProposal(availableUnits)) {
			unitsByPolicy.add(u);
		}
		return unitsByPolicy;
	}

	public List<Unit> getAvailableUnitsSorted(Emergency emergency) throws InvalidEmergencyException {
		MapItemEvaluationCriterium criterium = new AvailableUnitsMapItemEvaluationCriterium();
		return this.getWorld().getMapItemList().getMapItemsByCriterium(criterium).sort(new UnitToEmergencyDistanceComparator(emergency));
	}

	public void dispatchToEmergency(Emergency selectedEmergency, List<Unit> units) throws InvalidEmergencyStatusException, Exception {
		selectedEmergency.assignUnits(units);
	}
}
