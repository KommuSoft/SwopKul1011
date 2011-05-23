package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.ITrafficAccidentView;
import be.kuleuven.cs.swop.api.Severity;
import projectswop20102011.domain.TrafficAccident;

/**
 * A adapter that offers access to the data belonging to a TrafficAccident.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TrafficAccidentAdapter extends EmergencyAdapter implements ITrafficAccidentView{

	private final ITime time;

	/**
	 * Creates an TrafficAccidentAdapter from the given TrafficAccident.
	 * @param trafficAccident
	 *		The TrafficAccident that is used to create the TrafficAccidentAdapter.
	 */
	public TrafficAccidentAdapter(TrafficAccident trafficAccident){
		super(trafficAccident);
		time = new TimeAdapter();
	}

	public TrafficAccidentAdapter(TrafficAccident trafficAccident, ITime time){
		super(trafficAccident);
		this.time = time;
	}

	/**
	 * Returns the number of involved cars.
	 * @return The number of cars involved.
	 */
	@Override
	public int getNumberOfCars() {
		return (int) ((TrafficAccident)getEmergency()).getNumberOfCars();
	}

	/**
	 * Returns the number of injured people in the traffic accident.
	 * @return The number of injured people.
	 */
	@Override
	public int getNumberOfInjured() {
		return (int) ((TrafficAccident)getEmergency()).getNumberOfInjured();
	}

	/**
	 * Returns the timestamp associated with the event.
	 * @return The timestamp associated with the event.
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
