package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.IPublicDisturbanceView;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.Severity;
import projectswop20102011.domain.PublicDisturbance;

public class PublicDisturbanceAdapter extends EmergencyAdapter implements IPublicDisturbanceView {

	public PublicDisturbanceAdapter(PublicDisturbance publicDisturbance) {
		super(publicDisturbance);
	}

	@Override
	public int getNumberOfPeople() {
		return (int) ((PublicDisturbance) getEmergency()).getNumberOfPeople();
	}

	@Override
	public ITime getTime() {
		throw new UnsupportedOperationException("Not supported yet.PDA1");
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
