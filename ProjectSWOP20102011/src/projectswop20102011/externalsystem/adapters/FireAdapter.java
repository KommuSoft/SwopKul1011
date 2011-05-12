package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IFireView;
import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.Severity;
import projectswop20102011.domain.Fire;

/**
 * An adapter that offers access to the data belonging to a Fire.
 *  @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireAdapter extends EmergencyAdapter implements IFireView {

	/**
	 * Create a FireAdapter from the given Fire.
	 * @param fire
	 *		The Fire that is used to create the FireAdapter.
	 */
	public FireAdapter(Fire fire){
		super(fire);
	}

	/**
	 * Returns the size of the fire.
	 * @return The size of the fire.
	 * @note Possible values are "local", "house" an "facility".
	 */
	@Override
	public String getSize() {
		return ((Fire)getEmergency()).getSize().toString();
	}

	/**
	 * Returns whether the fire is chemical or not.
	 * @return True if it concers a chemical fire; false otherwise.
	 */
	@Override
	public boolean isChemical() {
		return ((Fire)getEmergency()).isChemical();
	}

	/**
	 * Returns the number of injured people in the fire.
	 * @return The number of injured people.
	 */
	@Override
	public int getNumberOfInjured() {
		return (int) ((Fire)getEmergency()).getNumberOfInjured();
	}

	/**
	 * Returns the number of trapped people in the fire.
	 * @return The number of trapped people in the fire.
	 */
	@Override
	public int getNumberOfTrappedPeople() {
		return (int) ((Fire)getEmergency()).getTrappedPeople();
	}

	/**
	 * Returns the timestamp associated with the event.
	 * @return The timestamp associated with the even.
	 */
	@Override
	public ITime getTime() {
		//TODO: misschien implementeren
		throw new UnsupportedOperationException("Not supported yet.FA1");
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
