package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidWorldException;

public class EmergencyDispatchApi implements IEmergencyDispatchApi{
	private ExternalSystem es;

	public EmergencyDispatchApi() throws InvalidWorldException{
		es = new ExternalSystem(new World());
	}

	@Override
	public void registerNewEvent(IEvent event) throws EmergencyDispatchException {
		try {
			try {
				es.addEvent(event);
			} catch (InvalidWorldException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
			es.notifyTimeChanged(event.getTime());
		} catch (ExternalSystemException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
