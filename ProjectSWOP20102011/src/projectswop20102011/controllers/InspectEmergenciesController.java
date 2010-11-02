package projectswop20102011.controllers;

import projectswop20102011.Emergency;
import projectswop20102011.EmergencyEvaluationCriterium;
import projectswop20102011.EmergencyStatus;
import projectswop20102011.StatusEqualityEmergencyEvaluationCriterium;
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

}
