package projectswop20102011.eventhandlers;

import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.IExternalSystem;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.externalsystem.adapters.EmergencyAdapter;
import projectswop20102011.externalsystem.adapters.TimeAdapter;
import projectswop20102011.externalsystem.adapters.UnitAdapter;
import projectswop20102011.domain.EmergencyUnitTuple;

public class ExternalSystemEventHandler {

	private final IExternalSystem externalSystem;

	public ExternalSystemEventHandler(IExternalSystem externalSystem) {
		this.externalSystem = externalSystem;
	}

	private IExternalSystem getExternalSystem() {
		return externalSystem;
	}

	private class AssignExternalSystemEventHandler implements EventHandler<EmergencyUnitTuple>{

		@Override
		public void handleEvent(EmergencyUnitTuple argument) {
			try {
				IEmergency iEmergency = new EmergencyAdapter(argument.getEmergency());
				IUnit iUnit = new UnitAdapter(argument.getUnit());
				getExternalSystem().notifyAssignment(iUnit, iEmergency);
			} catch (ExternalSystemException ex) {
				Logger.getLogger(ExternalSystemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalArgumentException ex) {
				Logger.getLogger(ExternalSystemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	private class ReleaseExternalSystemEventHandler implements EventHandler<EmergencyUnitTuple> {

		@Override
		public void handleEvent(EmergencyUnitTuple argument) {
			try {
				IEmergency iEmergency = new EmergencyAdapter(argument.getEmergency());
				IUnit iUnit = new UnitAdapter(argument.getUnit());
				getExternalSystem().notifyRelease(iUnit, iEmergency);
			} catch (ExternalSystemException ex) {
				Logger.getLogger(ExternalSystemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalArgumentException ex) {
				Logger.getLogger(ExternalSystemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private class TimeAheadExternalSystemEventHandler implements EventHandler<Long> {

		@Override
		public void handleEvent(Long argument) {
			int hours = (int) (argument.longValue()/ 3600);
			int minutes = (int) ((argument.longValue() % 3600) / 60);
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
	
	public AssignExternalSystemEventHandler makeAssignedEventHandler() {
		return new AssignExternalSystemEventHandler();
	}
	
	public ReleaseExternalSystemEventHandler makeReleaseEventHandler() {
		return new ReleaseExternalSystemEventHandler();
	}
	
	public TimeAheadExternalSystemEventHandler makeTimeAheadEventHandler() {
		return new TimeAheadExternalSystemEventHandler();
	}
}
