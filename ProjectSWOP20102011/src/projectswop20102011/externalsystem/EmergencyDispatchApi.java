package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.IEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidWorldException;

public class EmergencyDispatchApi implements IEmergencyDispatchApi {

	private World world;

	public EmergencyDispatchApi(World world) throws InvalidWorldException {
		setWorld(world);
	}

	World getWorld() {
		return world;
	}

	private void setWorld(World world) {
		this.world = world;
	}

	@Override
	public void registerNewEvent(IEvent event) throws EmergencyDispatchException {
		Event e = (Event) event;
		CreateEmergencyController cec = null;
		try {
			cec = new CreateEmergencyController(getWorld());
		} catch (InvalidWorldException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
		//TODO deze methode heb ik ff public gezet
		cec.addCreatedEmergencyToTheWorld(e.getEmergency());
	}
}
