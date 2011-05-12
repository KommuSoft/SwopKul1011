package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.IPublicDisturbanceView;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.Severity;
import projectswop20102011.domain.PublicDisturbance;

/**
 * A PublicDisturbanceAdapter that offers access to the data belonging to a PublicDisturbance.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class PublicDisturbanceAdapter extends EmergencyAdapter implements IPublicDisturbanceView {

	/**
	 * Creates a PublicDisturbanceAdapter from the given PublicDisturbance.
	 * @param publicDisturbance
	 *		The PublicDisturbance that is used to create the PublicDisturbanceAdapter.
	 */
	public PublicDisturbanceAdapter(PublicDisturbance publicDisturbance) {
		super(publicDisturbance);
	}

	/**
	 * Returns the number of people involved in the public disturbance.
	 * @return The number of involved people.
	 */
	@Override
	public int getNumberOfPeople() {
		return (int) ((PublicDisturbance) getEmergency()).getNumberOfPeople();
	}

	/**
	 * Returns the timestamp associated with the event.
	 * @return The timestamp associated with the event.
	 */
	@Override
	public ITime getTime() {
		//TODO: misschien implementeren
		throw new UnsupportedOperationException("Not supported yet.PDA1");
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
		return Severity.valueOf(getEmergency().getSeverity().toString());
	}
}
