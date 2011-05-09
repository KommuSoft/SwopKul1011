package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.EmergencyState;
import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.IHospital;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.IUnitConfiguration;
import be.kuleuven.cs.swop.api.NotSupportedException;
import be.kuleuven.cs.swop.api.UnitState;
import be.kuleuven.cs.swop.events.Fire;
import be.kuleuven.cs.swop.events.PublicDisturbance;
import be.kuleuven.cs.swop.events.Robbery;
import be.kuleuven.cs.swop.events.TrafficAccident;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.Main;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.World;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.ReadEnvironmentDataController;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.ParsingException;
import projectswop20102011.externalsystem.adapters.EmergencyAdapter;
import projectswop20102011.externalsystem.adapters.UnitAdapter;
import projectswop20102011.externalsystem.adapters.UnitConfiguration;
import projectswop20102011.factories.EmergencyFactory;
import projectswop20102011.userinterface.EnvironmentReader;
import projectswop20102011.utils.parsers.EmergencyStatusParser;

/**
 * A class that represents the EmergencyDispatchApi
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class EmergencyDispatchApi implements IEmergencyDispatchApi {

	/**
	 * A variable registering the world that is connected with this EmergencyDispatchApi.
	 */
	private World world;

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
	 *		If an exception occurs in the emergency dispatch system.
	 */
	@Override
	public void registerNewEvent(IEvent event) throws EmergencyDispatchException {
		try {
			CreateEmergencyController cec = new CreateEmergencyController(getWorld());
			Map<String, String> parameters = new HashMap<String, String>(3);
			parameters.put("location", new GPSCoordinate(event.getLocation().getX(), event.getLocation().getY()).toString());
			parameters.put("severity", event.getSeverity().toString().toLowerCase()); //TODO: lowercase moet nog weg wanneer de parser klaar is
			parameters.put("description", "");

			if (event.getClass().getSimpleName().equals("Fire")) {
				Fire fire = (Fire) event;
				parameters.put("size", fire.getSize());
				parameters.put("chemical", Boolean.toString(fire.isChemical()));
				parameters.put("trappedPeople", Integer.toString(fire.getNumberOfTrappedPeople()));
				parameters.put("numberOfInjured", Integer.toString(fire.getNumberOfInjured()));
			} else if (event.getClass().getSimpleName().equals("PublicDisturbance")) {
				PublicDisturbance publicDisturbance = (PublicDisturbance) event;
				parameters.put("numberOfPeople", Integer.toString(publicDisturbance.getNumberOfPeople()));
			} else if (event.getClass().getSimpleName().equals("TrafficAccident")) {
				TrafficAccident trafficAccident = (TrafficAccident) event;
				parameters.put("numberOfCars", Integer.toString(trafficAccident.getNumberOfCars()));
				parameters.put("numberOfInjured", Integer.toString(trafficAccident.getNumberOfInjured()));
			} else if (event.getClass().getSimpleName().equals("Robbery")) {
				Robbery robbery = (Robbery) event;
				parameters.put("armed", Boolean.toString(robbery.isArmed()));
				parameters.put("inProgress", Boolean.toString(robbery.isInProgress()));
			}

			Emergency emergency = null;
			EmergencyFactory factory = this.getWorld().getEmergencyFactoryList().getGenericFactoryFromName(event.getClass().getSimpleName());
			try {
				emergency = factory.createInstance(factory.getInformation().generateParametersFromMap(this.getWorld().getParserList(), parameters));
				cec.addCreatedEmergencyToTheWorld(emergency);
			} catch (Exception ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
		} catch (InvalidWorldException ex) {
			throw new EmergencyDispatchException("The world is invalid");
		}
	}

	@Override
	public List<IEmergency> getListOfEmergencies(EmergencyState state) throws EmergencyDispatchException {
		InspectEmergenciesController iec = null;
		try {
			iec = new InspectEmergenciesController(world);
		} catch (InvalidWorldException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}

		EmergencyStatus status = null;
		EmergencyStatusParser ep = new EmergencyStatusParser();
		//TODO: onderstaande if-statements mogen weg wanneer de parser dit ondersteund
		if (state.toString().equalsIgnoreCase("unhandled")) {
			try {
				status = ep.parse("recorded but unhandled");
			} catch (ParsingException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else if (state.toString().equalsIgnoreCase("responded")) {
			try {
				status = ep.parse("response in progress");
			} catch (ParsingException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			try {
				status = ep.parse(state.toString());
			} catch (ParsingException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		Emergency[] emergencies = iec.inspectEmergenciesOnStatus(status);
		List<IEmergency> iEmergencies = new ArrayList<IEmergency>();
		for (int i = 0; i < emergencies.length; ++i) {
			EmergencyAdapter ea = new EmergencyAdapter(emergencies[i]);
			iEmergencies.add(ea);
		}

		return iEmergencies;
	}

	@Override
	public List<IEmergency> getListOfDisasters(EmergencyState state) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet2.");
	}

	@Override
	public List<IUnit> getListOfUnits(UnitState state) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet3.");
	}

	@Override
	public List<IHospital> getListOfHospitals() {
		throw new UnsupportedOperationException("Not supported yet4.");
	}

	@Override
	public IUnitConfiguration getUnitConfiguration(IEmergency emergency) throws EmergencyDispatchException {
		UnitConfiguration unitConfiguration = new UnitConfiguration(emergency, getWorld());
		return unitConfiguration;
	}

	@Override
	public void assignUnits(IUnitConfiguration configuration) throws EmergencyDispatchException {
		//TODO: dit is lelijk (getEmergency en getUnit staan nu public)
		DispatchUnitsController controller = null;
		try {
			controller = new DispatchUnitsController(getWorld());
		} catch (InvalidWorldException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}

		EmergencyAdapter emergency = (EmergencyAdapter) configuration.getEmergency();
		List<IUnit> iUnits = configuration.getAllUnits();
		HashSet<Unit> units = new HashSet<Unit>();

		for (IUnit u : iUnits) {
			UnitAdapter unitAdapter = (UnitAdapter) u;
			units.add(unitAdapter.getUnit());
		}
		try {
			controller.dispatchToEmergency(emergency.getEmergency(), units);
		} catch (InvalidEmergencyStatusException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void createDisaster(String description, List<IEmergency> emergencies) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet7.");
	}

	@Override
	public void selectHospital(IUnit unit, IHospital hospital) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet8.");
	}

	@Override
	public void indicateEndOfTask(IUnit unit, IEmergency emergency) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet9.");
	}

	@Override
	public void indicateProblem(IUnit unit) throws NotSupportedException, EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet10.");
	}

	@Override
	public void indicateRepair(IUnit unit) throws NotSupportedException, EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet11.");
	}

	@Override
	public void advanceTime(ITime time) throws EmergencyDispatchException {
		world.setTime(time.getHours() * 3600 + time.getMinutes() * 60 + world.getTime());
		try {
			getWorld().getTimeSensitiveList().timeAhead(time.getHours() * 3600 + time.getMinutes() * 60 + world.getTime());
		} catch (InvalidDurationException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void withdrawUnit(IUnit unit, IEmergency emergency) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet12.");
	}

	@Override
	public void cancelEmergency(IEmergency emergency) throws NotSupportedException, EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet13.");
	}

	@Override
	public void clearSystem() {
		try {
			world = Main.initWorld();
		} catch (Exception ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void loadEnvironment(File file) throws EmergencyDispatchException {
		clearSystem();
		EnvironmentReader er = null;
		try {
			try {
				er = new EnvironmentReader(new ReadEnvironmentDataController(world));
			} catch (InvalidWorldException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
		} catch (InvalidControllerException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			FileInputStream fis = new FileInputStream("thirditeration.dat"); //TODO: magic string
			er.readEnvironmentData(fis);
			fis.close();
		} catch (Exception ex) {
			System.out.println(String.format("ERROR: %s", ex.getMessage()));
			System.out.println("program will now stop.");
		}
	}
}
