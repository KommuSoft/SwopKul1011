package projectswop20102011.controllers;

import java.util.Map.Entry;
import java.util.Set;
import projectswop20102011.domain.AvailableUnitsUnitBuildingEvaluationCriterium;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencyEvaluationCriterium;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.domain.StatusEqualityEmergencyEvaluationCriterium;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.UnitBuilding;
import projectswop20102011.domain.UnitBuildingEvaluationCriterium;
import projectswop20102011.domain.UnitBuildingList;
import projectswop20102011.domain.World;
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

    public Set<Entry<String, String>> getEmergencyLongInformation(Emergency emergency) {
        return emergency.getLongInformation().entrySet();
    }

}
