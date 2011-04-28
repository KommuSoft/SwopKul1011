package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.EmergencyState;
import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.Severity;
import be.kuleuven.cs.swop.events.Location;
import java.util.List;
import projectswop20102011.domain.Emergency;

public class EmergencyAdapter implements IEmergency{
	private final Emergency emergency;


	public EmergencyAdapter(Emergency emergency){
		this.emergency = emergency;
	}

	protected Emergency getEmergency(){
		return emergency;
	}

	@Override
	public EmergencyState getState() {
		return EmergencyState.valueOf(getEmergency().getStatus().toString());
	}

	@Override
	public List<IUnit> getAssignedUnits() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isPartOfDisaster() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ITime getTime() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ILocation getLocation() {
		return new Location((int)getEmergency().getLocation().getX(), (int) getEmergency().getLocation().getY());
	}

	@Override
	public Severity getSeverity() {
		return Severity.valueOf(getEmergency().getSeverity().toString());
	}

	@Override
	public int compareTo(IEvent o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}