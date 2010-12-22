package projectswop20102011.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import projectswop20102011.domain.validators.AvailableUnitsMapItemValidator;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.MapItemValidator;
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

	public Set<Unit> getUnitsByPolicy(Emergency emergency) throws InvalidEmergencyException {
		MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
		HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
		ArrayList<Unit> availableUnits = new ArrayList<Unit>();
		for(Unit u: mapItems){
			availableUnits.add(u);
		}
		return emergency.getPolicyProposal(availableUnits);
	}

	public List<Unit> getAvailableUnitsSorted(Emergency emergency) throws InvalidEmergencyException {
		MapItemValidator criterium = new AvailableUnitsMapItemValidator();
		return this.getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getSortedCopy(new UnitToEmergencyDistanceComparator(emergency));
	}

	public String getRequiredUnits(Emergency emergency){
		return emergency.getDispatchConstraint().toString();
	}

	public void dispatchToEmergency(Emergency selectedEmergency, Set<Unit> units) throws InvalidEmergencyStatusException, Exception {
		selectedEmergency.assignUnits(units);
	}
}
