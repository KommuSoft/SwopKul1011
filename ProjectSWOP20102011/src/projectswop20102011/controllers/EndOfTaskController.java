package projectswop20102011.controllers;

import projectswop20102011.domain.validators.TypeMapItemValidator;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.MapItemValidator;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidFinishJobException;
import projectswop20102011.exceptions.InvalidUnitException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller to handle the End-of-Task use case.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EndOfTaskController extends Controller {

    /**
     * Creates a new instance of an EndOfTaskException with a given world to operate on.
     * @param world The world to operate on.
     * @throws InvalidWorldException
     *          If the given world is invalid.
     */
    public EndOfTaskController(World world) throws InvalidWorldException {
        super(world);
    }

    /**
     * Searches for a Unit in the world with the given name.
     * @param unitName The name of the unit to search for.
     * @return The Unit with the given name, if in the world, otherwise false.
     */
    public Unit findUnit(String unitName) {
        MapItemValidator criterium = new TypeMapItemValidator(Unit.class);
        return (Unit) this.getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItemFromName(unitName);
    }

    /**
     * Lets the unit finish his job.
     * @param unit The unit that will finish his job.
     * @throws InvalidUnitException
     *          If the given unit is not effective.
     * @throws InvalidEmergencyStatusException
     *          If the state of the emergency of the unit does not allow units to finish their jobs.
     * @throws InvalidFinishJobException
     *          If the unit can't finish his job.
     */
    public void indicateEndOfTask(Unit unit) throws InvalidUnitException, InvalidEmergencyStatusException, InvalidFinishJobException {
        if (unit == null) {
            throw new InvalidUnitException("The given unit must be effective.");
        }
        unit.finishedJob();
    }
}
