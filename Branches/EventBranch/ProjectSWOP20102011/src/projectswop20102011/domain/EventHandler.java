package projectswop20102011.domain;

/**
 * An abstract class that represent a couple of events.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class EventHandler {

	/**
	 * A assignment of a unit to an emergency happens.
	 * @param emergency
	 *		The emergency where the unit is assigned to.
	 * @param unit 
	 *		The unit that is asssigned.
	 */
	public abstract void doAssign(Emergency emergency, Unit unit);

	/**
	 * A release of a unit from an emergency happens.
	 * @param emergency
	 *		The emergency where the unit is released from.
	 * @param unit
	 *		The unit that is released.
	 */
	public abstract void doRelease(Emergency emergency, Unit unit);

	/**
	 * Set a time to the given time.
	 * @param time
	 *		The new time.
	 */
	public abstract void doTimeSet(long time);
}
