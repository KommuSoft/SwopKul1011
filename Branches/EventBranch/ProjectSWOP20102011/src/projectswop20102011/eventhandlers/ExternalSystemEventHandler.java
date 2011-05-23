package projectswop20102011.eventhandlers;

import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.IExternalSystem;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EventHandler;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.externalsystem.adapters.EmergencyAdapter;
import projectswop20102011.externalsystem.adapters.TimeAdapter;
import projectswop20102011.externalsystem.adapters.UnitAdapter;

public class ExternalSystemEventHandler extends EventHandler{
	
	private final IExternalSystem externalSystem;
	
	public ExternalSystemEventHandler(IExternalSystem externalSystem){
		this.externalSystem = externalSystem;
	}
	
	private IExternalSystem getExternalSystem(){
		return externalSystem;
	}

	@Override
	public void doAssign(Emergency emergency, Unit unit) {
		try {
			IEmergency iEmergency = new EmergencyAdapter(emergency);
			IUnit iUnit = new UnitAdapter(unit);
			getExternalSystem().notifyAssignment(iUnit, iEmergency);
		} catch (ExternalSystemException ex) {
			Logger.getLogger(ExternalSystemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(ExternalSystemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void doRelease(Emergency emergency, Unit unit) {
		try {
			IEmergency iEmergency = new EmergencyAdapter(emergency);
			IUnit iUnit = new UnitAdapter(unit);
			getExternalSystem().notifyRelease(iUnit, iEmergency);
		} catch (ExternalSystemException ex) {
			Logger.getLogger(ExternalSystemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(ExternalSystemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void doTimeSet(long time) {
		int hours = (int) (time / 3600);
		int minutes = (int) ((time % 3600) / 60);
		try {
			getExternalSystem().notifyTimeChanged(new TimeAdapter(hours, minutes));
		} catch (ExternalSystemException ex) {
			Logger.getLogger(ExternalSystemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(ExternalSystemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NumberOutOfBoundsException ex) {
			Logger.getLogger(ExternalSystemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
