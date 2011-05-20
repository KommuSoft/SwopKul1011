package projectswop20102011.controllers;

import be.kuleuven.cs.swop.external.IExternalSystem;
import projectswop20102011.World;
import projectswop20102011.domain.EventHandler;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller used for the use case where the simulated time is advanced.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TimeAheadController extends Controller {

	/**
	 * An eventHandler where the notifications should be sent to.
	 */
	private EventHandler eventHandler;

	/**
	 * Creates a new instance of a TimeAhead controller with a given world.
	 * @param world
	 *		The world that will be modified by the controller.
	 * @param externalSystem
	 *		The externalSystem that is connected with this TimeAheadController.
	 * @effect The world of this controller is set to the given world.
	 *		| super(world)
	 * @post The TimeAheadAdapter of this controller constructed.
	 *		|new TimeAheadAdapter(getWorld().getIEmergencyDispatchApi())
	 * @throws InvalidWorldException
	 *		If the world is not effective.
	 */
	public TimeAheadController(World world, IExternalSystem externalSystem, EventHandler eventHandler) throws InvalidWorldException {
		super(world);
		this.eventHandler = eventHandler;
	}
	/**
	 * Performs a time ahead action, where all the time sensitive objects in the world will be modified under a certain time difference.
	 * @param seconds
	 *		The time difference in seconds.
	 * @throws InvalidDurationException
	 *		If the given amont of seconds is invalid.
	 */
	public void doTimeAheadAction(long seconds) throws InvalidDurationException {
		getWorld().getTimeSensitiveList().timeAhead(seconds);
		getWorld().setTime(getWorld().getTime() + seconds, eventHandler);
	}
}
