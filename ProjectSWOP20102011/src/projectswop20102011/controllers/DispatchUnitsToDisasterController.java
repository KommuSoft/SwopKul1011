package projectswop20102011.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import projectswop20102011.World;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.AvailableUnitsMapItemValidator;
import projectswop20102011.domain.validators.MapItemValidator;
import projectswop20102011.domain.validators.UnitToDisasterDistanceComparator;
import projectswop20102011.exceptions.InvalidDisasterException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 *
 */
public class DispatchUnitsToDisasterController extends Controller {

	public DispatchUnitsToDisasterController(World world) throws InvalidWorldException {
		super(world);
	}

	public Set<Unit> getUnitsByPolicy(Disaster disaster) {
		MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
		HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
		return disaster.getPolicyProposal(mapItems);
	}

	public List<Unit> getAvailableUnitsSorted(Disaster disaster) throws InvalidDisasterException {
		MapItemValidator criterium = new AvailableUnitsMapItemValidator();
		return this.getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getSortedCopy(new UnitToDisasterDistanceComparator(disaster));
	}

	//TODO: string hiervan maken
	public Set<Unit> getRequiredUnits(Disaster disaster) {
		MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
		HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
		return disaster.getPolicyProposalAllSeverities(mapItems);
	}

	public void dispatchToDisaster(Disaster disaster, Set<Unit> units) throws InvalidEmergencyStatusException, InvalidEmergencyException {
		disaster.assignUnits(units);
	}
}
