package projectswop20102011.controllers;

import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller for the inspect disasters use case.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InspectDisastersController extends Controller {

	/**
	 * Creates a new instance of the InspectDisastersController with a given world to be modified.
	 * @param world The world to be modified.
	 * @effect super(world)
	 * @throws InvalidWorldException If the given world is ineffective.
	 */
	public InspectDisastersController(World world) throws InvalidWorldException {
		super(world);
	}
}
