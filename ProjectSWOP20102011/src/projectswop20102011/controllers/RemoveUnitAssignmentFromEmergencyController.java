package projectswop20102011.controllers;

import java.util.ArrayList;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.World;
import projectswop20102011.domain.EventHandler;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidMapItemException;
import projectswop20102011.exceptions.InvalidWithdrawalException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller class that handles a remove unit assignment use case.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class RemoveUnitAssignmentFromEmergencyController extends Controller {
	
	private EventHandler eventHandler;

    /**
     * Creates a new RemoveUnitAssignmentController with a given world.
     * @param world
     *      The world that will be manipulated by the controller.
     * @throws InvalidWorldException If the world is invalid.
     */
    public RemoveUnitAssignmentFromEmergencyController(World world, EventHandler eventHandler) throws InvalidWorldException {
        super(world);
		this.eventHandler = eventHandler;
    }

    /**
     * Generates a list containing all the Units that are working on the given Emergency.
     * @param selectedEmergency The emergency where we generate the working units list from.
     * @return A list containing all the Units that working on the given Emergency.
     */
    public ArrayList<Unit> getWorkingUnits(Emergency selectedEmergency) {
        return selectedEmergency.getWorkingUnits();
    }

    /**
     * Withdraws a Unit from its emergency.
     * @param unit The unit to withdraw from it's emergency.
     * @throws InvalidWithdrawalException If the unit cannot withdraw.
     * @throws InvalidEmergencyStatusException If the status of the emergency does not allow units to withdraw.
     * @throws InvalidMapItemException If the given unit is not effective.
     */
    public void withdrawUnit(Unit unit) throws InvalidWithdrawalException, InvalidSendableStatusException, InvalidMapItemException {
        if (unit != null) {
            unit.withdraw(eventHandler);
        } else {
            throw new InvalidMapItemException("MapItem must be a Unit and exist in the World!");
        }
    }
}
