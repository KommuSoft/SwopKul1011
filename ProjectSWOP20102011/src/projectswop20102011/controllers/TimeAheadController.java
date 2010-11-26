package projectswop20102011.controllers;

import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller used to go forward in time (use case #7).
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TimeAheadController extends Controller {

    /**
     * Creates a new instance of a TimeAhead controller with a given world.
     * @param world The world that will be modified by the controller.
     * @effect super(world)
     * @throws InvalidWorldException If the world is not effective.
     */
    public TimeAheadController (World world) throws InvalidWorldException {
       super(world);
    }

    /**
     * performs a time ahead action, where all the time sensitive objects in the world will be modified under a certain time difference.
     * @param seconds The time difference in seconds.
     * @throws InvalidDurationException If the given amont of seconds is invalid
     */
    public void doTimeAheadAction (int seconds) throws InvalidDurationException {
        this.getWorld().getTimeSensitiveList().timeAhead(seconds);
    }
}
