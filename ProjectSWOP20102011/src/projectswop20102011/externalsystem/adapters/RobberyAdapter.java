package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.IRobberyView;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.Severity;
import projectswop20102011.domain.Robbery;

public class RobberyAdapter extends EmergencyAdapter implements IRobberyView{

	public RobberyAdapter(Robbery robbery){
		super(robbery);
	}

	@Override
	public boolean isArmed() {
		return ((Robbery)getEmergency()).isArmed();
	}

	@Override
	public boolean isInProgress() {
		return ((Robbery)getEmergency()).isInProgress();
	}

	@Override
	public ITime getTime() {
		throw new UnsupportedOperationException("Not supported yet.");
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
