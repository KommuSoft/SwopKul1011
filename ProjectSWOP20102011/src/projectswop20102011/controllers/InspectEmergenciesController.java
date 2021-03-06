package projectswop20102011.controllers;

import java.util.Map.Entry;
import java.util.Set;
import projectswop20102011.domain.validators.AvailableUnitsMapItemValidator;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.validators.EmergencyEvaluationCriterium;
import projectswop20102011.domain.SendableStatus;
import projectswop20102011.domain.validators.StatusEqualityEmergencyEvaluationCriterium;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.MapItemValidator;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller for the use case where emergencies are inspected  based on their status.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InspectEmergenciesController extends Controller {

    /**
     * Creates a new instance of the CreateEmergenciesController with a given world to be modified.
     * @param world The world to be modified.
     * @effect super(world)
     * @throws InvalidWorldException If the given world is ineffective.
     */
    public InspectEmergenciesController(World world) throws InvalidWorldException {
        super(world);
    }

    /**
     * Inspects all the emergencies in the world on a certain status, and returns a list with emergencies that have that status.
     * @param status The status of to check the emrgencies on.
     * @return A list with emergencies with the given status.
     */
    public Emergency[] inspectEmergenciesOnStatus(SendableStatus status) {
        EmergencyEvaluationCriterium criterium = new StatusEqualityEmergencyEvaluationCriterium(status);
        return this.getWorld().getEmergencyList().getEmergenciesByCriterium(criterium).toArray();
    }

	/**
     * Generates a set of entries with parts of information of the given emergency.
     * @param emergency The emergency to generate information from.
     * @return A set of entries containing the key and value of a piece of information of the emergency.
     */
    public Set<Entry<String, String>> getEmergencyShortInformation(Emergency emergency) {
        return emergency.getShortInformation().entrySet();
    }

    /**
     * Checks if the given emergency can be resolved with all available units.
     * @param emergency The emergency to check for.
     * @return True if the emergency can be resolved, otherwise false.
     */
    public boolean canBeResolved(Emergency emergency) {
        MapItemValidator criterium = new AvailableUnitsMapItemValidator();
        Set<Unit> availableUnitsList = this.getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
        return emergency.canBeResolved(availableUnitsList);
    }

    /**
     * Generates a table of all the information of the given emergency.
     * @param emergency The emergency to generate the table for.
     * @return A set of entries of a table containing all the information of the emergency.
     */
    public Set<Entry<String, String>> getEmergencyLongInformation(Emergency emergency) {
        return emergency.getLongInformation().entrySet();
    }
}
