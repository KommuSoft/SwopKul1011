package projectswop20102011.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.factories.FireFactory;
import projectswop20102011.factories.PublicDisturbanceFactory;
import projectswop20102011.factories.RobberyFactory;
import projectswop20102011.factories.TrafficAccidentFactory;

/**
 * A controller used for use case #1, where an emergency is created.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class CreateEmergencyController extends Controller {

	/**
	 * Creates a new CreateEmergencyController with a given world.
	 * @param world The world that will be manipulated by the controller.
	 * @throws InvalidWorldException If the world is invalid.
	 */
	public CreateEmergencyController(World world) throws InvalidWorldException {
		super(world);
	}

	/**
	 * Adding the given Emergency to the world.
	 * @param emergency The emergency to add to the world.
	 */
	public void addCreatedEmergencyToTheWorld(Emergency emergency) {
		this.getWorld().getEmergencyList().addEmergency(emergency);
	}

	/**
	 * Creates a new fire in the world, and add's it to the world.
	 * @param location
	 *		The location of the fire.
	 * @param severity
	 *		The severity level of the fire.
	 * @param description
	 *		The description of the fire.
	 * @param fireSize
	 *		The size of the fire.
	 * @param chemical
	 *		An indicator that indicates if the fire is chemical.
	 * @param trappedPeople
	 *		The number of trapped people in the fire.
	 * @param numberOfInjured
	 *		The number of injured people in the fire.
	 * @throws InvalidLocationException
	 *		If the given location is invalid.
	 * @throws InvalidEmergencySeverityException
	 *		If the given severity level is invalid.
	 * @throws InvalidFireSizeException
	 *		If the given size is invalid.
	 * @throws NumberOutOfBoundsException
	 *		If the given number of trapped or injured people is invalid.
	 */
	public void createFireEmergency(GPSCoordinate location, EmergencySeverity severity, String description, FireSize fireSize, boolean chemical, long trappedPeople, long numberOfInjured) throws  InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
		FireFactory ff = null;
		try {
			ff = new FireFactory();
		} catch (InvalidEmergencyTypeNameException ex) {
			//This should never happen.
			Logger.getLogger(CreateEmergencyController.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			addCreatedEmergencyToTheWorld(ff.createEmergency(new Object[]{location, severity, description, fireSize, chemical, trappedPeople, numberOfInjured}));
		} catch (InvalidAmountOfParametersException ex) {
			//This can never happen.
			Logger.getLogger(CreateEmergencyController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Create a robbery in the world.
	 * @param location The location of the robbery.
	 * @param severity The severity level of the robbery.
	 * @param description The description of the robbery.
	 * @param armed An indicator that indicates if the robber is armed.
	 * @param inProgress An indicator that indicates if the robbery is still in progress.
	 * @throws InvalidLocationException If the given location is invalid.
	 * @throws InvalidEmergencySeverityException If the severity level is invalid.
	 */
	public void createRobberyEmergency(GPSCoordinate location, EmergencySeverity severity, String description, boolean armed, boolean inProgress) throws InvalidLocationException, InvalidEmergencySeverityException {
		RobberyFactory rf = null;
		try {
			rf = new RobberyFactory();
		} catch (InvalidEmergencyTypeNameException ex) {
			//We assume this can never happen.
			Logger.getLogger(CreateEmergencyController.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			addCreatedEmergencyToTheWorld(rf.createEmergency(new Object[]{location, severity, description, armed, inProgress}));
		} catch (InvalidAmountOfParametersException ex) {
			//This can not happen.
			Logger.getLogger(CreateEmergencyController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Creates a new public disturbance in the world.
	 * @param location The location of the public disturbance.
	 * @param severity The severity level of the public disturbance.
	 * @param description The description of the public disturbance.
	 * @param numberOfPeople The number of people involved in the public disturbance.
	 * @throws InvalidLocationException If the given location is invalid.
	 * @throws InvalidEmergencySeverityException If the given severity level is invalid.
	 * @throws NumberOutOfBoundsException If the given number of envolved people is invalid.
	 */
	public void createPublicDisturbanceEmergency(GPSCoordinate location, EmergencySeverity severity, String description, long numberOfPeople) throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
		PublicDisturbanceFactory pdf = null;
		try {
			pdf = new PublicDisturbanceFactory();
		} catch (InvalidEmergencyTypeNameException ex) {
			//Impossible that this happens.
			Logger.getLogger(CreateEmergencyController.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			addCreatedEmergencyToTheWorld(pdf.createEmergency(new Object[]{location, severity, description, numberOfPeople}));
		} catch (InvalidAmountOfParametersException ex) {
			//This can't happen.
			Logger.getLogger(CreateEmergencyController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Creates a new traffic accident in the world.
	 * @param location The location of the traffic accident.
	 * @param severity The severity level of the traffic accident.
	 * @param description The description of the traffic accident.
	 * @param numberOfCars The number of cars involved in the traffic accident.
	 * @param numberOfInjured The number of injured people.
	 * @throws InvalidLocationException If the given location is invalid.
	 * @throws InvalidEmergencySeverityException If the severity level of the traffic accident.
	 * @throws NumberOutOfBoundsException If the given number of injured people is invalid.
	 */
	public void createTrafficAccidentEmergency(GPSCoordinate location, EmergencySeverity severity, String description, long numberOfCars, long numberOfInjured) throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
		TrafficAccidentFactory taf = null;
		try {
			taf = new TrafficAccidentFactory();
		} catch (InvalidEmergencyTypeNameException ex) {
			//This shouldn't happen.
			Logger.getLogger(CreateEmergencyController.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			addCreatedEmergencyToTheWorld(taf.createEmergency(new Object[]{location, severity, description, numberOfCars, numberOfInjured}));
		} catch (InvalidAmountOfParametersException ex) {
			//All hell breakes loose.
			Logger.getLogger(CreateEmergencyController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
