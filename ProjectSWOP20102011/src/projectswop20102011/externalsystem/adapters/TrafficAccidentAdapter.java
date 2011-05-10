package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.ITrafficAccidentView;
import be.kuleuven.cs.swop.api.Severity;
import projectswop20102011.domain.TrafficAccident;

public class TrafficAccidentAdapter extends EmergencyAdapter implements ITrafficAccidentView{

	public TrafficAccidentAdapter(TrafficAccident trafficAccident){
		super(trafficAccident);
	}

	@Override
	public int getNumberOfCars() {
		return (int) ((TrafficAccident)getEmergency()).getNumberOfCars();
	}

	@Override
	public int getNumberOfInjured() {
		return (int) ((TrafficAccident)getEmergency()).getNumberOfInjured();
	}

	@Override
	public ITime getTime() {
		throw new UnsupportedOperationException("Not supported yet.TAA1");
	}

	@Override
	public ILocation getLocation() {
		return new LocationAdapter(getEmergency().getLocation());
	}

	@Override
	public Severity getSeverity() {
		return Severity.valueOf(getEmergency().getSeverity().toString());
	}

}
