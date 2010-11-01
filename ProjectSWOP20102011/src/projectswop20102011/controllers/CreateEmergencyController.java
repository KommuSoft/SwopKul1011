package projectswop20102011.controllers;

import projectswop20102011.EmergencySeverity;
import projectswop20102011.FireSize;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller used for use case #1, where an emergency is created.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class CreateEmergencyController extends Controller {

    public CreateEmergencyController (World world) throws InvalidWorldException {
        super(world);
    }

    public void createFire (GPSCoordinate location, EmergencySeverity emergencySeverity, FireSize fireSize, boolean chemical, boolean trappedPeople) {
        
    }

}
