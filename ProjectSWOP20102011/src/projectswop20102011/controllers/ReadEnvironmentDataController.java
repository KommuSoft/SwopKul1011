package projectswop20102011.controllers;

import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller that reads in the environmental data, and constructs the units and buildings described in it.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ReadEnvironmentDataController extends Controller {

    public ReadEnvironmentDataController (World world) throws InvalidWorldException {
        super(world);
    }

}
