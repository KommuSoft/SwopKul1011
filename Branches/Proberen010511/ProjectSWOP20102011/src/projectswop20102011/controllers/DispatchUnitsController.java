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
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller to handle unit dispatching.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsController extends Controller {

    /**
     * Creates a new instance of a DispatchUnitsController with a given word to operate on.
     * @param world The world where the controller will operate on.
     * @throws InvalidWorldException If the given world is invalid.
     */
    public DispatchUnitsController(World world) throws InvalidWorldException {
        super(world);
    }

    /**
     * Generates a proposal for unit allocation for the given emergency.
     * @param emergency The emergency to generate a proposal for.
     * @return A set of Units that represent the proposal for the emergency.
     * @throws InvalidEmergencyException If the given emergency is invalid.
     */
    public Set<Unit> getUnitsByPolicy(Emergency emergency) throws InvalidEmergencyException {
        MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
        HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
        ArrayList<Unit> availableUnits = new ArrayList<Unit>();
        for (Unit u : mapItems) {
            availableUnits.add(u);
        }
        return emergency.getPolicyProposal(availableUnits);
    }

    /**
     * Returns a list of available units sorted on the policy used by the givem emergency.
     * @param emergency The emergency to sort the units.
     * @return A list of available units sorted on the policy used by the given emergency.
     * @throws InvalidEmergencyException
     *          If the given emergency is invalid.
     */
    public List<Unit> getAvailableUnitsSorted(Emergency emergency) throws InvalidEmergencyException {
        MapItemValidator criterium = new AvailableUnitsMapItemValidator();
        return this.getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getSortedCopy(new UnitToEmergencyDistanceComparator(emergency));
    }

    /**
     * Returns a textual representation of the unit constraints of the given emergency.
     * @param emergency The given emergency.
     * @return A textual representation of the unit constraints of the given emergency.
     */
    public String getRequiredUnits(Emergency emergency) {
        return emergency.getDispatchConstraint().toString();
    }

    /**
     * Dispatches the given set of units to the given emergency.
     * @param emergency The emergency where the units will be assigned to.
     * @param units The set of units to assign to the given emergency.
     * @throws InvalidEmergencyStatusException
     *          If the emergency is in a state where units can't be dispatched to.
     * @throws Exception
     *          If another reason disables dispatching the given units to the given emergency.
     */
    public void dispatchToEmergency(Emergency emergency, Set<Unit> units) throws InvalidEmergencyStatusException, Exception {
        emergency.assignUnits(units);
    }
}
