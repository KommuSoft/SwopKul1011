package projectswop20102011.controllers;

import projectswop20102011.domain.AvailableUnitsUnitBuildingEvaluationCriterium;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.UnitBuildingEvaluationCriterium;
import projectswop20102011.domain.UnitToEmergencyDistanceComparator;
import projectswop20102011.domain.UnitsNeeded;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsController extends Controller {

    public DispatchUnitsController (World world) throws InvalidWorldException {
        super(world);
    }

    public Emergency getEmergencyFromId (long id) {
        return this.getWorld().getEmergencyList().getEmergencyFromId(id);
    }
    public Unit[] getAvailableUnitsSorted (Emergency emergency) throws InvalidEmergencyException {
        UnitBuildingEvaluationCriterium criterium = new AvailableUnitsUnitBuildingEvaluationCriterium();
        return this.getWorld().getUnitBuildingList().getUnitBuildingsByCriterium(criterium).sort(new UnitToEmergencyDistanceComparator(emergency)).toArray(new Unit[0]);
    }

    public UnitsNeeded getNeededUnits(Emergency selectedEmergency) {
        return selectedEmergency.getUnitsNeeded();
    }

    public void dispatchToEmergency(Emergency selectedEmergency, Unit[] units) throws InvalidEmergencyException {
        selectedEmergency.getUnitsNeeded().assignUnitsToEmergency(units);
    }

}
