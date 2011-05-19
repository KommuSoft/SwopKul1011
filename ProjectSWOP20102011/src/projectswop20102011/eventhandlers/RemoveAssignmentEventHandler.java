package projectswop20102011.eventhandlers;

import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.IExternalSystem;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencyEventHandler;
import projectswop20102011.domain.Unit;
import projectswop20102011.externalsystem.adapters.EmergencyAdapter;
import projectswop20102011.externalsystem.adapters.UnitAdapter;

public final class RemoveAssignmentEventHandler implements EmergencyEventHandler {

	private final IExternalSystem externalSystem;
	private Emergency emergency;
	private Unit unit;

	public RemoveAssignmentEventHandler(IExternalSystem externalSystem) {
		this.externalSystem = externalSystem;
	}

	@Override
	public void setEmergency(Emergency emergency){
		this.emergency = emergency;
	}
	
	@Override
	public void setUnit(Unit unit){
		this.unit = unit;
	}
	
	private IExternalSystem getExternalSystem() {
		return externalSystem;
	}

	private Emergency getEmergency() {
		return emergency;
	}

	private Unit getUnit() {
		return unit;
	}

	@Override
	public void doEvent() {
		try {
			IEmergency iEmergency = new EmergencyAdapter(getEmergency());
			IUnit iUnit = new UnitAdapter(getUnit());
			getExternalSystem().notifyRelease(iUnit, iEmergency);
		} catch (ExternalSystemException ex) {
			Logger.getLogger(AssignmentEventHandler.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(AssignmentEventHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
