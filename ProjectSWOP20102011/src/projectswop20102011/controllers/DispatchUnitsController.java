package projectswop20102011.controllers;

import projectswop20102011.Emergency;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsController extends Controller {

    public DispatchUnitsController (World world) throws InvalidWorldException {
        super(world);
    }

    public Emergency getEmergencyFromId (long id) {
        return this.getWorld().getEmergencyList().getEmergencyFromId(id);
    }

}
