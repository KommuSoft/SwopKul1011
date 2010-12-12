package projectswop20102011.controllers;

import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * An abstract class for a controller structure, with a reference to the world
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @invar The world of the controller is always valid | isValid(getWorld())
 */
public abstract class Controller {

    /**
     * The domain world, this is modified by the controller (not nessesary direct)
     */
    private final World world;

    /**
     * Creates a new controller with a specific domain world.
     * @param world The domainworld, that will be modified by the controller.
     * @throws InvalidWorldException When the world isn't effective.
     * @post The given world is equal to this world | world.equals(getWorld())
     */
    protected Controller (World world) throws InvalidWorldException {
        if(!isValidWorld(world)) {
            throw new InvalidWorldException("World must be effective.");
        }
        this.world = world;
    }

    /**
     * Gets the domainworld of the controller, this world will be modified by the controller.
     * @return the domain world of the controller.
     */
    protected World getWorld () {
        return this.world;
    }

    /**
     * Tests if the given world is a valid world for the controller structure.
     * @param world
     *      The given world to test.
     * @return true if the given world is effective, otherwise false.
     */
    public static boolean isValidWorld (World world) {
        return (world != null);
    }

}
