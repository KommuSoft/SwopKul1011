package projectswop20102011.controllers;

import projectswop20102011.TimeSensitive;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

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
     * @throws NumberOutOfBoundsException If the number of secons is smaller than zero.
     */
    public void doTimeAheadAction (long seconds) throws NumberOutOfBoundsException {
        if(seconds < 0) {
            throw new NumberOutOfBoundsException(String.format("The difference in time must be larger or equal to zero and not \"%s\"", seconds));
        }
        for(TimeSensitive ts : this.getWorld().getTimeSensitiveList()) {
            ts.timeAhead(seconds);
        }
    }
}
