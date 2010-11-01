package projectswop20102011.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.Emergency;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.Fire;
import projectswop20102011.FireSize;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.PublicDisturbance;
import projectswop20102011.Robbery;
import projectswop20102011.TrafficAccident;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A controller used for use case #1, where an emergency is created.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class CreateEmergencyController extends Controller {

    public CreateEmergencyController (World world) throws InvalidWorldException {
        super(world);
    }

    private void addCreatedEmergencyToTheWorld (Emergency emergency) {
        try {
            this.getWorld().getEmergencyList().addEmergency(emergency);
        } catch (InvalidEmergencyException ex) {
            //can't be thrown: the emergence is just created so it can't be already in the list.
            Logger.getLogger(CreateEmergencyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void createFireEmergency (GPSCoordinate location, EmergencySeverity severity, FireSize fireSize, boolean chemical, boolean trappedPeople,long numberOfInjured) throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        addCreatedEmergencyToTheWorld(new Fire(location,severity,fireSize,chemical,trappedPeople,numberOfInjured));
    }
    public void createRobberyEmergency (GPSCoordinate location, EmergencySeverity severity, boolean armed, boolean inProgress) throws InvalidLocationException, InvalidEmergencySeverityException {
        addCreatedEmergencyToTheWorld(new Robbery(location,severity,armed,inProgress));
    }
    public void createPublicDisturbanceEmergency (GPSCoordinate location, EmergencySeverity severity, long numberOfPeople) throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        addCreatedEmergencyToTheWorld(new PublicDisturbance(location,severity,numberOfPeople));
    }
    public void createTrafficAccidentEmergency (GPSCoordinate location, EmergencySeverity severity,long numberOfCars, long numberOfInjured) throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        addCreatedEmergencyToTheWorld(new TrafficAccident(location,severity,numberOfCars,numberOfInjured));
    }

}