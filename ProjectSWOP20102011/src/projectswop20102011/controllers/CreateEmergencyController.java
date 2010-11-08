package projectswop20102011.controllers;

import projectswop20102011.Emergency;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.Fire;
import projectswop20102011.FireSize;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.PublicDisturbance;
import projectswop20102011.Robbery;
import projectswop20102011.TrafficAccident;
import projectswop20102011.World;
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

	/**
	 *
	 * @param world
	 * @throws InvalidWorldException
	 */
    public CreateEmergencyController (World world) throws InvalidWorldException {
        super(world);
    }

	/**
	 *
	 * @param emergency
	 */
    private void addCreatedEmergencyToTheWorld (Emergency emergency) {
        this.getWorld().getEmergencyList().addEmergency(emergency);
    }

	/**
	 *
	 * @param location
	 * @param severity
	 * @param fireSize
	 * @param chemical
	 * @param trappedPeople
	 * @param numberOfInjured
	 * @throws InvalidLocationException
	 * @throws InvalidEmergencySeverityException
	 * @throws InvalidFireSizeException
	 * @throws NumberOutOfBoundsException
	 */
    public void createFireEmergency (GPSCoordinate location, EmergencySeverity severity, FireSize fireSize, boolean chemical, long trappedPeople,long numberOfInjured) throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        addCreatedEmergencyToTheWorld(new Fire(location,severity,fireSize,chemical,trappedPeople,numberOfInjured));
    }

	/**
	 *
	 * @param location
	 * @param severity
	 * @param armed
	 * @param inProgress
	 * @throws InvalidLocationException
	 * @throws InvalidEmergencySeverityException
	 */
    public void createRobberyEmergency (GPSCoordinate location, EmergencySeverity severity, boolean armed, boolean inProgress) throws InvalidLocationException, InvalidEmergencySeverityException {
        addCreatedEmergencyToTheWorld(new Robbery(location,severity,armed,inProgress));
    }

	/**
	 *
	 * @param location
	 * @param severity
	 * @param numberOfPeople
	 * @throws InvalidLocationException
	 * @throws InvalidEmergencySeverityException
	 * @throws NumberOutOfBoundsException
	 */
    public void createPublicDisturbanceEmergency (GPSCoordinate location, EmergencySeverity severity, long numberOfPeople) throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        addCreatedEmergencyToTheWorld(new PublicDisturbance(location,severity,numberOfPeople));
    }

	/**
	 *
	 * @param location
	 * @param severity
	 * @param numberOfCars
	 * @param numberOfInjured
	 * @throws InvalidLocationException
	 * @throws InvalidEmergencySeverityException
	 * @throws NumberOutOfBoundsException
	 */
    public void createTrafficAccidentEmergency (GPSCoordinate location, EmergencySeverity severity,long numberOfCars, long numberOfInjured) throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        addCreatedEmergencyToTheWorld(new TrafficAccident(location,severity,numberOfCars,numberOfInjured));
    }

}