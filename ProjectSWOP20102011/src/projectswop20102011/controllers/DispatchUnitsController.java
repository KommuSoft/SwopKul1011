package projectswop20102011.controllers;

import java.util.List;
import projectswop20102011.domain.validators.AvailableUnitsMapItemEvaluationCriterium;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.MapItemEvaluationCriterium;
import projectswop20102011.domain.validators.UnitToEmergencyDistanceComparator;
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
        MapItemEvaluationCriterium criterium = new AvailableUnitsMapItemEvaluationCriterium();
        return this.getWorld().getMapItemList().getMapItemsByCriterium(criterium).sort(new UnitToEmergencyDistanceComparator(emergency)).toArray(new Unit[0]);
    }

    public UnitsNeeded getUnitsNeeded(Emergency selectedEmergency) {
        return selectedEmergency.getUnitsNeeded();
    }

    public void dispatchToEmergency(Emergency selectedEmergency, List<Unit> units) throws InvalidEmergencyException {
        selectedEmergency.getUnitsNeeded().assignUnitsToEmergency(units);
    }

}
