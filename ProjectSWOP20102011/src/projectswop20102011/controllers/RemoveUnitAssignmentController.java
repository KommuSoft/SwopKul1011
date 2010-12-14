package projectswop20102011.controllers;

import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.WithdrawUnits;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidEmergencyException;
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

    public Emergency getEmergencyFromId(long id) {
        return this.getWorld().getEmergencyList().getEmergencyFromId(id);
    }

    public void withdrawUnits(Emergency selectedEmergency, String[] names) throws InvalidWithdrawalException, InvalidEmergencyException{
        WithdrawUnits wu = new WithdrawUnits(getWorld().getMapItemList());
        wu.withdraw(names);
    }
}
