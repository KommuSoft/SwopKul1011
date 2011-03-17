package projectswop20102011.controllers;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import projectswop20102011.domain.validators.AvailableUnitsMapItemValidator;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.validators.EmergencyEvaluationCriterium;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.domain.validators.StatusEqualityEmergencyEvaluationCriterium;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.MapItemValidator;
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
	 * @throws InvalidWorldException
     */
    private Emergency inspectEmergencyDetailOnStatusId(EmergencyStatus status, long id) throws InvalidWorldException {
		return new EmergencyController(getWorld()).getEmergencyFromId(id);
    }

    public Set<Entry<String, String>> getEmergencyShortInformation(Emergency e) {
        return e.getShortInformation().entrySet();
    }
    public boolean canBeAssigned (Emergency e) {
        MapItemValidator criterium = new AvailableUnitsMapItemValidator();
        Set<Unit> availableUnitsList = this.getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
        return e.canBeResolved(availableUnitsList);
    }

    public Set<Entry<String, String>> getEmergencyLongInformation(Emergency emergency) {
        return emergency.getLongInformation().entrySet();
    }

}
