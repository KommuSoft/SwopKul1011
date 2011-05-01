package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ITrafficAccidentView;
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

}
