package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.IEvent;
import java.util.Map;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.lists.World;
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

public class EmergencyDispatchApi implements IEmergencyDispatchApi {

	/**
	 * A variable registering the world that is connected with this EmergencyDispatchApi.
	 */
	private final World world;

	/**
	 * Creates a new EmergencyDispatchApi with the given world.
	 * @param world
	 *		The world that this EmergencyDispatchApi is connected with.
	 * @effect The world is set to the given world.
	 *		|world.equals(getWorld())
	 */
	public EmergencyDispatchApi(World world) {
		this.world = world;
	}

	/**
	 * Returns the world of this EmergencyDispatchApi.
	 * @return The world of this EmergencyDispatchApi.
	 */
	private World getWorld() {
		return world;
	}

	/**
	 * Registers a new event.
	 * @param event
	 *		The event that must be registered.
	 * @throws EmergencyDispatchException
	 *		if an exception occurs in the emergency dispatch system.
	 */
	@Override
	public void registerNewEvent(IEvent event) throws EmergencyDispatchException {
		try {
			CreateEmergencyController cec = new CreateEmergencyController(getWorld());
			GPSCoordinate gps = new GPSCoordinate(event.getLocation().getX(), event.getLocation().getY());
			EmergencySeverity emergencySeverity = EmergencySeverity.parse(event.getSeverity());
			Emergency emergency = null;
			if (event.getType().equals("Fire")) {
				emergency = createFire(gps, emergencySeverity, event.getEventProperties());
			} else if (event.getType().equals("PublicDisturbance")) {
				emergency = createPublicDisturbance(gps, emergencySeverity, event.getEventProperties());
			} else if (event.getType().equals("Robbery")) {
				emergency = createRobbery(gps, emergencySeverity, event.getEventProperties());
			} else if (event.getType().equals("TrafficAccident")) {
				emergency = createTrafficAccident(gps, emergencySeverity, event.getEventProperties());
			}
			cec.addCreatedEmergencyToTheWorld(emergency);
		} catch (InvalidEmergencySeverityException ex) {
			throw new EmergencyDispatchException("The emergency severity is invalid");
		} catch (InvalidWorldException ex) {
			throw new EmergencyDispatchException("The world is invalid");
		}

	}

	/**
	 * Creates a fire with the given parameters.
	 * @param gps
	 *		The location of the fire.
	 * @param emergencySeverity
	 *		The severity of the fire.
	 * @param properties
	 *		The other properties of the fire.
	 * @return A fire with the given parameters.
	 * @throws EmergencyDispatchException
	 *		If an error occurs while the fire is created.
	 */
	private Emergency createFire(GPSCoordinate gps, EmergencySeverity emergencySeverity, Map<String, String> properties) throws EmergencyDispatchException {
		try {
			FireFactory factory = new FireFactory();
			return factory.createEmergency(new Object[]{gps, emergencySeverity, "", FireSize.parse(properties.get("size")), properties.get("chemical").equals("true") ? true : false, properties.get("trappedPeople").equals("true") ? 1L : 0L, Long.parseLong(properties.get("numberOfInjured"))});
		} catch (InvalidLocationException ex) {
			throw new EmergencyDispatchException("The location is invalid.");
		} catch (InvalidEmergencySeverityException ex) {
			throw new EmergencyDispatchException("The emergency severity is invalid");
		} catch (NumberOutOfBoundsException ex) {
			throw new EmergencyDispatchException("The number of trapped people or the number of injured people is invalid.");
		} catch (InvalidAmountOfParametersException ex) {
			throw new EmergencyDispatchException("The number of parameters is invalid.");
		} catch (InvalidFireSizeException ex) {
			throw new EmergencyDispatchException("The fire size is invalid.");
		} catch (InvalidEmergencyTypeNameException ex) {
			throw new EmergencyDispatchException("The emergency type name is invalid.");
		}
	}

	/**
	 * Creates a public disturbance with the given parameters.
	 * @param gps
	 *		The location of the public disturbance.
	 * @param emergencySeverity
	 *		The severity of the public disturbance.
	 * @param properties
	 *		The other properties of the public disturbance.
	 * @return A public disturbance with the given parameters.
	 * @throws EmergencyDispatchException
	 *		If an error occurs while the public disturbance is created.
	 */
	private Emergency createPublicDisturbance(GPSCoordinate gps, EmergencySeverity emergencySeverity, Map<String, String> properties) throws EmergencyDispatchException {
		try {
			PublicDisturbanceFactory factory = new PublicDisturbanceFactory();
			return factory.createEmergency(new Object[]{gps, emergencySeverity, "", Long.parseLong(properties.get("numberOfPeople"))});
		} catch (InvalidAmountOfParametersException ex) {
			throw new EmergencyDispatchException("The number of parameters is invalid.");
		} catch (InvalidLocationException ex) {
			throw new EmergencyDispatchException("The location is invalid.");
		} catch (InvalidEmergencySeverityException ex) {
			throw new EmergencyDispatchException("The emergency severity is invalid.");
		} catch (NumberOutOfBoundsException ex) {
			throw new EmergencyDispatchException("The number of involved people is invalid.");
		} catch (InvalidEmergencyTypeNameException ex) {
			throw new EmergencyDispatchException("The emergency type name is invalid.");
		}
	}

	/**
	 * Creates a robbery with the given parameters.
	 * @param gps
	 *		The location of the robbery.
	 * @param emergencySeverity
	 *		The severity of the robbery.
	 * @param properties
	 *		The other properties of the robbery.
	 * @return A robbery with the given parameters.
	 * @throws EmergencyDispatchException
	 *		If an error occurs while the robbery is created.
	 */
	private Emergency createRobbery(GPSCoordinate gps, EmergencySeverity emergencySeverity, Map<String, String> properties) throws EmergencyDispatchException {
		try {
			RobberyFactory factory = new RobberyFactory();
			return factory.createEmergency(new Object[]{gps, emergencySeverity, "", properties.get("armed").equals("true") ? true : false, properties.get("inProgress").equals("true") ? true : false});
		} catch (InvalidLocationException ex) {
			throw new EmergencyDispatchException("The location is invalid.");
		} catch (InvalidEmergencySeverityException ex) {
			throw new EmergencyDispatchException("The emergency severity is invalid.");
		} catch (InvalidAmountOfParametersException ex) {
			throw new EmergencyDispatchException("The number of parameters is invalid.");
		} catch (InvalidEmergencyTypeNameException ex) {
			throw new EmergencyDispatchException("The emergency type name is invalid.");
		}
	}

	/**
	 * Creates a traffic accident with the given parameters.
	 * @param gps
	 *		The location of the traffic accident.
	 * @param emergencySeverity
	 *		The severity of the traffic accident.
	 * @param properties
	 *		The other properties of the traffic accident.
	 * @return A traffic accident with the given parameters.
	 * @throws EmergencyDispatchException
	 *		If an error occurs while the traffic accident is created.
	 */
	private Emergency createTrafficAccident(GPSCoordinate gps, EmergencySeverity emergencySeverity, Map<String, String> properties) throws EmergencyDispatchException {
		try {
			TrafficAccidentFactory factory = new TrafficAccidentFactory();
			return factory.createEmergency(new Object[]{gps, emergencySeverity, "", Long.parseLong(properties.get("numberOfCars")), Long.parseLong(properties.get("numberOfInjured"))});
		} catch (InvalidLocationException ex) {
			throw new EmergencyDispatchException("The location is invalid.");
		} catch (InvalidEmergencySeverityException ex) {
			throw new EmergencyDispatchException("The emergency severity is invalid.");
		} catch (NumberOutOfBoundsException ex) {
			throw new EmergencyDispatchException("The number of involved cars or the number of injured pople is invalid.");
		} catch (InvalidAmountOfParametersException ex) {
			throw new EmergencyDispatchException("The number of parameters is invalid.");
		} catch (InvalidEmergencyTypeNameException ex) {
			throw new EmergencyDispatchException("The emergency type name is invalid.");
		}
	}
}
