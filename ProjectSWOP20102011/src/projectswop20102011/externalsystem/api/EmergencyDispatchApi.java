package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.IEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidWorldException;

public class EmergencyDispatchApi implements IEmergencyDispatchApi{
	CreateEmergencyController cec;

	public EmergencyDispatchApi(World world){
		try {
			cec = new CreateEmergencyController(world);
		} catch (InvalidWorldException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void registerNewEvent(IEvent event) throws EmergencyDispatchException {
		//TODO throw error
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
