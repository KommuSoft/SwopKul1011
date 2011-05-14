package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.IRobberyView;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.Severity;
import projectswop20102011.domain.Robbery;

/**
 * A RobberyAdapter that offers access to the data belonging to a Robbery.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class RobberyAdapter extends EmergencyAdapter implements IRobberyView{

	private final ITime time;

	/**
	 * Creates a RobberyAdapter from the given Robbery.
	 * @param robbery
	 *		The Robbery that is used to create the RobberyAdapter.
	 */
	public RobberyAdapter(Robbery robbery){
		super(robbery);
		time = new TimeAdapter();
	}

	public RobberyAdapter(Robbery robbery, TimeAdapter time){
		super(robbery);
		this.time = time;
	}

	/**
	 * Returns whether it concerns an armed robbery or not.
	 * @return True if it concerns an armed robbery, false otherwise.
	 */
	@Override
	public boolean isArmed() {
		return ((Robbery)getEmergency()).isArmed();
	}

	/**
	 * Indicates whether the robbery is in progress.
	 * @return True if the robbery is in progress, false otherwise.
	 */
	@Override
	public boolean isInProgress() {
		return ((Robbery)getEmergency()).isInProgress();
	}

	/**
	 * Returns the timestamp associated with the event.
	 * @return The timestamp.
	 */
	@Override
	public ITime getTime() {
		return time;
	}

	/**
	 * Retrieves the location of the event.
	 * @return The location of the event.
	 */
	@Override
	public ILocation getLocation() {
		return new LocationAdapter(getEmergency().getLocation());
	}

	/**
	 * Retrieves the severity of the event.
	 * @return The severity of the event.
	 */
	@Override
	public Severity getSeverity() {
		return Severity.valueOf(getEmergency().getSeverity().toString().toUpperCase());
	}

}
