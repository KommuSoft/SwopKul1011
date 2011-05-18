package projectswop20102011.controllers;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import projectswop20102011.World;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.Policecar;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.AvailableUnitsMapItemValidator;
import projectswop20102011.domain.validators.MapItemValidator;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.domain.validators.UnitToDisasterDistanceComparator;
import projectswop20102011.domain.validators.UnitToEmergencyDistanceComparator;
import projectswop20102011.exceptions.InvalidDisasterException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
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
	
	public Set<Unit> getUnitsByPolicyAllSeverities(Disaster disaster) {
		MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
		HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
		return disaster.getPolicyProposalAllSeverities(mapItems);
	}

	public List<Unit> getAvailableUnitsSorted(Disaster disaster) throws InvalidDisasterException, InvalidClassException {
		//TODO: dit kan waarschijnlijk een heel klein beetje mooier misschien wellicht?
		MapItemValidator criterium = new AvailableUnitsMapItemValidator();
		Set<Unit> units = getUnitsByPolicyAllSeverities(disaster);
		TypeUnitValidator tuv1 = new TypeUnitValidator(Ambulance.class);
		TypeUnitValidator tuv2 = new TypeUnitValidator(Policecar.class);
		TypeUnitValidator tuv3 = new TypeUnitValidator(Firetruck.class);
		boolean ambulance = false;
		boolean firetruck = false;
		boolean policecar = false;
		for (Unit u : units) {
			ambulance = ambulance || tuv1.isValid(u);
			firetruck = firetruck || tuv3.isValid(u);
			policecar = policecar || tuv2.isValid(u);
		}
		
		List<Unit> allUnits = this.getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getSortedCopy(new UnitToDisasterDistanceComparator(disaster));
		List<Unit> result = new ArrayList<Unit>(0);
		for (Unit u : allUnits) {
			if (ambulance && u instanceof Ambulance) {
				result.add(u);
			}
			if (policecar && u instanceof Policecar) {
				result.add(u);
			}
			if (firetruck && u instanceof Firetruck) {
				result.add(u);
			}
		}

		return result;
	}

	//TODO: string hiervan maken
	public String getRequiredUnits(Disaster disaster) {
		String s = "";

		for (int i = 0; i < disaster.getEmergencies().size(); ++i) {
			s += "For emergency " + (i + 1) + " in the disaster we want: ";
			s += disaster.getEmergencies().get(i).getDispatchConstraint().toString();
			s += "\n";
		}
		return s;
//		
//		MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
//		HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
//		return disaster.getPolicyProposalAllSeverities(mapItems);
	}

	public void dispatchToDisaster(Disaster disaster, Set<Unit> units) throws InvalidSendableStatusException, InvalidEmergencyException {
		disaster.assignUnits(units);
	}
}
