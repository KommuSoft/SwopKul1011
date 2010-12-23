package projectswop20102011.controllers;

import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.events.Time;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.IExternalSystem;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.lists.World;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller used to go forward in time (use case #7).
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TimeAheadController extends Controller {

	/**
	 * A variabele registering the externalSystem of this timeAheadController.
	 */
	private final IExternalSystem externalSystem;

	/**
	 * Creates a new instance of a TimeAhead controller with a given world.
	 * @param world
	 *		The world that will be modified by the controller.
	 * @param externalSystem
	 *		The externalSystem that is connected with this TimeAheadController.
	 * @effect The world of this controller is set to the given world.
	 *		| super(world)
	 * @post The externalSystem of this controller is set to the given externalSystem.
	 *		| externalSystem.equals(getExternalSystem)
	 * @throws InvalidWorldException
	 *		If the world is not effective.
	 */
	public TimeAheadController(World world, IExternalSystem externalSystem) throws InvalidWorldException {
		super(world);
		this.externalSystem = externalSystem;
	}

	/**
	 * Creates a new instance of a TimeAhead controller with a given world.
	 * @param world
	 *		The world that will be modified by the controller.
	 * @effect The world of this controller is set to the given world.
	 *		|super(world)
	 * @post The externalSystem of this controller is set to null.
	 *		|getExternalSystem() == null
	 * @throws InvalidWorldException
	 *		If the world is not effective.
	 */
	public TimeAheadController(World world) throws InvalidWorldException {
		super(world);
		this.externalSystem = null;
	}

	/**
	 * Returns the externalSystem of this TimeAheadController.
	 * @return The externalSystem of this TimeAheadContrller.
	 */
	private IExternalSystem getExternalSystem(){
		return externalSystem;
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
		if (getExternalSystem() != null) {
			int s = (int) (seconds + getWorld().getTime());
			int hours = (s / 3600);
			int minutes = ((s - (3600 * hours)) / 60);
			ITime time = new Time(hours, minutes);
			try {
				getExternalSystem().notifyTimeChanged(time);
			} catch (ExternalSystemException ex) {
				Logger.getLogger(TimeAheadController.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalArgumentException ex) {
				//Logger.getLogger(TimeAheadController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		getWorld().setTime(getWorld().getTime() + seconds);
	}
}
