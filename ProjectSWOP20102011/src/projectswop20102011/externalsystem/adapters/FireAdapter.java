package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IFireView;
import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.Severity;
import projectswop20102011.domain.Fire;

public class FireAdapter extends EmergencyAdapter implements IFireView {

	public FireAdapter(Fire fire){
		super(fire);
	}

	@Override
	public String getSize() {
		return ((Fire)getEmergency()).getSize().toString();
	}

	@Override
	public boolean isChemical() {
		return ((Fire)getEmergency()).isChemical();
	}

	@Override
	public int getNumberOfInjured() {
		return (int) ((Fire)getEmergency()).getNumberOfInjured();
	}

	@Override
	public int getNumberOfTrappedPeople() {
		return (int) ((Fire)getEmergency()).getTrappedPeople();
	}

	@Override
	public ITime getTime() {
		throw new UnsupportedOperationException("Not supported yet.FA1");
	}

	@Override
	public ILocation getLocation() {
		return new LocationAdapter((int)getEmergency().getLocation().getX(), (int)getEmergency().getLocation().getY());
	}

	@Override
	public Severity getSeverity() {
		return Severity.valueOf(getEmergency().getSeverity().toString());
	}
}
