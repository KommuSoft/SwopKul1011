package projectswop20102011.controllers;

import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 *
 * @author Pieter-Jan
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
}
