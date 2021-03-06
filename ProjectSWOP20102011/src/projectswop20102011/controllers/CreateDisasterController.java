package projectswop20102011.controllers;

import java.util.List;
import projectswop20102011.World;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.Emergency;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller used for the use case where a disaster is created.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class CreateDisasterController extends Controller {

	public CreateDisasterController(World world) throws InvalidWorldException {
		super(world);
	}

	/**
	 * Adding the given Emergency to the world.
	 * @param disaster the disaster to add
	 */
	public void addCreatedDisasterToTheWorld(Disaster disaster) {
		getWorld().getDisasterList().addDisaster(disaster);
	}

	public void createDisaster(List<Emergency> emergencies, String description) throws InvalidEmergencyException, InvalidConstraintListException{
		this.addCreatedDisasterToTheWorld(new Disaster(emergencies,description));
	}
}
