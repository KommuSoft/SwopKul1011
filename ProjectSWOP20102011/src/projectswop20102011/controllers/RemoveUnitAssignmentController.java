package projectswop20102011.controllers;

import java.util.ArrayList;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidMapItemException;
import projectswop20102011.exceptions.InvalidWithdrawalException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class RemoveUnitAssignmentController extends Controller {

    /**
     * Creates a new RemoveUnitAssignmentController with a given world.
     * @param world
     *      The world that will be manipulated by the controller.
     * @throws InvalidWorldException If the world is invalid.
     */
    public RemoveUnitAssignmentController(World world) throws InvalidWorldException {
        super(world);
    }

    public ArrayList<Unit> getWorkingUnits(Emergency selectedEmergency) {
        return selectedEmergency.getWorkingUnits();
    }

    public void withdrawUnit(Unit unit) throws InvalidWithdrawalException, InvalidEmergencyException, InvalidEmergencyStatusException, InvalidMapItemException {
        if (unit != null) {
            unit.withdraw();
        } else {
            throw new InvalidMapItemException("MapItem must be a Unit and exist in the World!");
        }
    }
}
