package projectswop20102011.controllers;

import java.util.Map.Entry;
import java.util.Set;
import projectswop20102011.AvailableUnitsUnitBuildingEvaluationCriterium;
import projectswop20102011.Emergency;
import projectswop20102011.EmergencyEvaluationCriterium;
import projectswop20102011.EmergencyStatus;
import projectswop20102011.StatusEqualityEmergencyEvaluationCriterium;
import projectswop20102011.Unit;
import projectswop20102011.UnitBuilding;
import projectswop20102011.UnitBuildingEvaluationCriterium;
import projectswop20102011.UnitBuildingList;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller for the inspect emergencies use case.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InspectEmergenciesController extends Controller {


    /**
     * Creates a new instance of the CreateEmergenciesController with a given world to be modified.
     * @param world The world to be modified.
     * @effect super(world)
     * @throws InvalidWorldException If the given world is ineffective.
     */
    public InspectEmergenciesController (World world) throws InvalidWorldException {
        super(world);
    }

    /**
     * Inspects all the emergencies in the world on a certain status, and returns a list with emergencies that have that status.
     * @param status The status of to check the emrgencies on.
     * @return A list with emergencies with the given status.
     */
    public Emergency[] inspectEmergenciesOnStatus (EmergencyStatus status) {
        EmergencyEvaluationCriterium criterium = new StatusEqualityEmergencyEvaluationCriterium(status);
        return this.getWorld().getEmergencyList().getEmergenciesByCriterium(criterium).toArray();
    }

    /**
     * Inspect all the emergencies in the world on a certain status, and searches for an emergency with that status who has also the given id.
     * @param status The status of emergencies to search for.
     * @param id The id number to search for.
     * @return The emergency who mets the conditions, if no condition succeeds, null is returned.
     */
    public Emergency inspectEmergencyDetailOnStatusId(EmergencyStatus status, long id) {
        EmergencyEvaluationCriterium criterium = new StatusEqualityEmergencyEvaluationCriterium(status);
        return this.getWorld().getEmergencyList().getEmergenciesByCriterium(criterium).getEmergencyFromId(id);
    }

    public Set<Entry<String, String>> getEmergencyShortInformation(Emergency e) {
        return e.getShortInformation().entrySet();
    }
    public boolean canBeAssigned (Emergency e) {
        UnitBuildingEvaluationCriterium criterium = new AvailableUnitsUnitBuildingEvaluationCriterium();
        UnitBuildingList availableUnitsList = this.getWorld().getUnitBuildingList().getUnitBuildingsByCriterium(criterium);
        UnitBuilding[] ubs = availableUnitsList.toArray();
        Unit[] units = new Unit[ubs.length];
        int i = 0;
        for(UnitBuilding ub : ubs) {
            units[i++] = (Unit) ub;
        }
        return e.getUnitsNeeded().canAssignUnitsToEmergency(units);
    }

}
