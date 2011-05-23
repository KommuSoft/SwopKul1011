package projectswop20102011.controllers;

import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.SendableSeverity;
import projectswop20102011.domain.Fire;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.PublicDisturbance;
import projectswop20102011.domain.Robbery;
import projectswop20102011.domain.TrafficAccident;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A controller used for the use case  where an emergency is created.
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
        getWorld().getEmergencyList().addEmergency(emergency);
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
    public void createFireEmergency(GPSCoordinate location, SendableSeverity severity, String description, FireSize fireSize, boolean chemical, long trappedPeople, long numberOfInjured) throws InvalidLocationException, InvalidSendableSeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        this.addCreatedEmergencyToTheWorld(new Fire(location, severity, description, fireSize, chemical, trappedPeople, numberOfInjured));
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
    public void createRobberyEmergency(GPSCoordinate location, SendableSeverity severity, String description, boolean armed, boolean inProgress) throws InvalidLocationException, InvalidSendableSeverityException {
        this.addCreatedEmergencyToTheWorld(new Robbery(location, severity, description, armed, inProgress));
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
    public void createPublicDisturbanceEmergency(GPSCoordinate location, SendableSeverity severity, String description, long numberOfPeople) throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException {
        this.addCreatedEmergencyToTheWorld(new PublicDisturbance(location, severity, description, numberOfPeople));
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
    public void createTrafficAccidentEmergency(GPSCoordinate location, SendableSeverity severity, String description, long numberOfCars, long numberOfInjured) throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException {
        this.addCreatedEmergencyToTheWorld(new TrafficAccident(location, severity, description, numberOfCars, numberOfInjured));
    }
}
