package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.EmergencyState;
import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.IFireView;
import be.kuleuven.cs.swop.api.IHospital;
import be.kuleuven.cs.swop.api.IPublicDisturbanceView;
import be.kuleuven.cs.swop.api.IRobberyView;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.ITrafficAccidentView;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.IUnitConfiguration;
import be.kuleuven.cs.swop.api.NotSupportedException;
import be.kuleuven.cs.swop.api.UnitState;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.Main;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.World;
import projectswop20102011.controllers.CreateDisasterController;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.controllers.EndOfTaskController;
import projectswop20102011.controllers.InspectDisastersController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.ReadEnvironmentDataController;
import projectswop20102011.controllers.RemoveUnitAssignmentFromEmergencyController;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.domain.Hospital;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.lists.MapItemList;
import projectswop20102011.domain.validators.TypeMapItemValidator;
import projectswop20102011.exceptions.CancelEmergencyNotSupportedException;
import projectswop20102011.exceptions.IndicateProblemNotSupportedException;
import projectswop20102011.exceptions.IndicateRepairNotSupportedException;
import projectswop20102011.exceptions.InvalidAddedDisasterException;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFinishJobException;
import projectswop20102011.exceptions.InvalidHospitalException;
import projectswop20102011.exceptions.InvalidMapItemException;
import projectswop20102011.exceptions.InvalidUnitException;
import projectswop20102011.exceptions.InvalidWithdrawalException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.ParsingException;
import projectswop20102011.externalsystem.adapters.AmbulanceAdapter;
import projectswop20102011.externalsystem.adapters.EmergencyAdapter;
import projectswop20102011.externalsystem.adapters.HospitalAdapter;
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
	private ConcurrentHashMap<Emergency, Long> todoEmergencies;

	/**
	 * Creates a new EmergencyDispatchApi with the given world.
	 * @param world
	 *		The world that this EmergencyDispatchApi is connected with.
	 * @effect The world is set to the given world.
	 *		|world.equals(getWorld())
	 */
	public EmergencyDispatchApi(World world) {
		this.world = world;
		todoEmergencies = new ConcurrentHashMap<Emergency, Long>(0);
	}

	private ConcurrentHashMap<Emergency, Long> getTodoEmergencies() {
		return todoEmergencies;
	}

	private void addTodoEmergency(Emergency e, long time) {
		todoEmergencies.put(e, time);
	}

	private void deleteTodoEmergency(Emergency e) {
		todoEmergencies.remove(e);
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
		CreateEmergencyController cec = null;
		try {
			cec = new CreateEmergencyController(getWorld());
		} catch (InvalidWorldException ex) {
			throw new EmergencyDispatchException("The world is invalid");
		}

		Map<String, String> parameters = new HashMap<String, String>(3);
		parameters.put("location", new GPSCoordinate(event.getLocation().getX(), event.getLocation().getY()).toString());
		parameters.put("severity", event.getSeverity().toString().toLowerCase()); //TODO: lowercase moet nog weg wanneer de parser klaar is
		parameters.put("description", "");

		String nameFactory = null;
		if (event instanceof IFireView) {
			IFireView fire = (IFireView) event;
			parameters.put("size", fire.getSize());
			parameters.put("chemical", Boolean.toString(fire.isChemical()));
			parameters.put("trappedPeople", Integer.toString(fire.getNumberOfTrappedPeople()));
			parameters.put("numberOfInjured", Integer.toString(fire.getNumberOfInjured()));
			nameFactory = "fire";
		} else if (event instanceof IPublicDisturbanceView) {
			IPublicDisturbanceView publicDisturbance = (IPublicDisturbanceView) event;
			parameters.put("numberOfPeople", Integer.toString(publicDisturbance.getNumberOfPeople()));
			nameFactory = "publicDisturbance";
		} else if (event instanceof ITrafficAccidentView) {
			ITrafficAccidentView trafficAccident = (ITrafficAccidentView) event;
			parameters.put("numberOfCars", Integer.toString(trafficAccident.getNumberOfCars()));
			parameters.put("numberOfInjured", Integer.toString(trafficAccident.getNumberOfInjured()));
			nameFactory = "trafficAccident";
		} else if (event instanceof IRobberyView) {
			IRobberyView robbery = (IRobberyView) event;
			parameters.put("armed", Boolean.toString(robbery.isArmed()));
			parameters.put("inProgress", Boolean.toString(robbery.isInProgress()));
			nameFactory = "robbery";
		}

		Emergency emergency = null;
		EmergencyFactory factory = this.getWorld().getEmergencyFactoryList().getGenericFactoryFromName(nameFactory);
		try {
			emergency = factory.createInstance(factory.getInformation().generateParametersFromMap(this.getWorld().getParserList(), parameters));
		} catch (Exception ex) {
			//TODO: mag de logger blijven of moet dit doorgegeven worden met een EmergencyDispatchException
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
		//TODO: voor timestamp
//		if (world.getTime() >= event.getTime().getHours() * 3600 + event.getTime().getMinutes() * 60) {
//			cec.addCreatedEmergencyToTheWorld(emergency);
//		} else {
//			addTodoEmergency(emergency, event.getTime().getHours() * 3600 + event.getTime().getMinutes() * 60);
//		}
		cec.addCreatedEmergencyToTheWorld(emergency);
	}

	@Override
	public List<IEmergency> getListOfEmergencies(EmergencyState state) throws EmergencyDispatchException {
		InspectEmergenciesController iec = null;
		try {
			iec = new InspectEmergenciesController(world);
		} catch (InvalidWorldException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
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
		} else if (state.toString().equalsIgnoreCase("completed")) {
			try {
				status = ep.parse(state.toString().toLowerCase());
			} catch (ParsingException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			HashSet<Emergency> emergencies = getWorld().getEmergencyList().getEmergencies();
			ArrayList<IEmergency> iEmergencies = new ArrayList<IEmergency>();
			for (Emergency e : emergencies) {
				EmergencyAdapter ea = new EmergencyAdapter(e);
				iEmergencies.add(ea);
			}
			return iEmergencies;
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
		InspectDisastersController idc = null;
		try {
			idc = new InspectDisastersController(getWorld());
		} catch (InvalidWorldException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
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
		} else if (state.toString().equalsIgnoreCase("completed")) {
			try {
				status = ep.parse(state.toString());
			} catch (ParsingException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			HashSet<Disaster> disasters = getWorld().getDisasterList().getDisasters();
			ArrayList<IEmergency> iEmergencies = new ArrayList<IEmergency>();
			for (Disaster d : disasters) {
				for (Emergency e : d.getEmergencies()) {
					EmergencyAdapter ea = new EmergencyAdapter(e);
					iEmergencies.add(ea);
				}
			}
			return iEmergencies;
		}

		Disaster[] disasters = null;
		try {
			disasters = idc.inspectDisastersOnStatus(status);
		} catch (InvalidAddedDisasterException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}

		ArrayList<IEmergency> iEmergencies = new ArrayList<IEmergency>();
		for (int i = 0; i < disasters.length; ++i) {
			for (int j = 0; j < disasters[i].getEmergencies().size(); ++j) {
				iEmergencies.add(new EmergencyAdapter(disasters[i].getEmergencies().get(j)));
			}
		}

		return iEmergencies;
	}

	@Override
	public List<IUnit> getListOfUnits(UnitState state) throws EmergencyDispatchException {
		//TODO: kan er in deze methode iets mislopen? Ze gaan er namelijk van uit dat er een exception gegooid kan worden.

		MapItemList mil = world.getMapItemList();
		TypeMapItemValidator<Unit> miv = new TypeMapItemValidator<Unit>(Unit.class);
		ArrayList<Unit> units = mil.getSubMapItemListByValidator(miv).toArrayList();
		ArrayList<IUnit> result = new ArrayList<IUnit>();

		for (Unit u : units) {
			UnitAdapter ua = new UnitAdapter(u);
			if (ua.getState().equals(state)) {
				result.add(ua);
			} else if (state.equals(UnitState.ANY)) {
				result.add(ua);
			}
		}

		return result;
	}

	@Override
	public List<IHospital> getListOfHospitals() {
		MapItemList mil = world.getMapItemList();

		TypeMapItemValidator<Hospital> miv = new TypeMapItemValidator<Hospital>(Hospital.class);
		ArrayList<Hospital> hospitals = mil.getSubMapItemListByValidator(miv).toArrayList();
		ArrayList<IHospital> result = new ArrayList<IHospital>();

		for (Hospital h : hospitals) {
			HospitalAdapter ha = new HospitalAdapter(h);
			result.add(ha);
		}

		return result;
	}

	@Override
	public IUnitConfiguration getUnitConfiguration(IEmergency emergency) throws EmergencyDispatchException {
		//TODO: kan er in deze methode iets mislopen? Ze gaan er namelijk van uit dat er een exception gegooid kan worden.
		return new UnitConfiguration(emergency, getWorld());
	}

	@Override
	public void assignUnits(IUnitConfiguration configuration) throws EmergencyDispatchException {
		//TODO: dit is lelijk (getEmergency en getUnit staan nu public)
		DispatchUnitsController controller = null;
		try {
			controller = new DispatchUnitsController(getWorld());
		} catch (InvalidWorldException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
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
			throw new EmergencyDispatchException(ex.getMessage());
		} catch (InvalidEmergencyException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
		}
	}

	@Override
	public void createDisaster(String description, List<IEmergency> iEmergencies) throws EmergencyDispatchException {
		CreateDisasterController controller = null;
		try {
			controller = new CreateDisasterController(getWorld());
		} catch (InvalidWorldException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
		}
		EmergencyAdapter emergencyAdapter;
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>(0);
		for (IEmergency em : iEmergencies) {
			emergencyAdapter = (EmergencyAdapter) em;
			emergencies.add(emergencyAdapter.getEmergency());
		}
		try {
			controller.createDisaster(emergencies, description);
		} catch (InvalidEmergencyException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
		} catch (InvalidConstraintListException ex) {
			//TODO: mag de logger blijven of moet dit doorgegeven worden met een EmergencyDispatchException
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void selectHospital(IUnit unit, IHospital hospital) throws EmergencyDispatchException {
		if (unit == null) {
			throw new EmergencyDispatchException("The unit is not effective.");
		}
		if (hospital == null) {
			throw new EmergencyDispatchException("The hospital is not effective.");
		}

		SelectHospitalController controller = null;
		try {
			controller = new SelectHospitalController(getWorld());
		} catch (InvalidWorldException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
		}
		AmbulanceAdapter ambulanceAdapter = (AmbulanceAdapter) unit;
		Ambulance amb = (Ambulance) ambulanceAdapter.getUnit();

		HospitalAdapter hospitalAdapter = (HospitalAdapter) hospital;
		Hospital hosp = hospitalAdapter.getHospital();

		try {
			controller.selectHospital(amb, hosp);
		} catch (InvalidAmbulanceException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
		} catch (InvalidHospitalException ex) {
			//We assume this can't happen.
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void indicateEndOfTask(IUnit unit, IEmergency emergency) throws EmergencyDispatchException {
		if (unit == null) {
			throw new EmergencyDispatchException("The unit must be effective");
		}

		EndOfTaskController controller = null;
		try {
			controller = new EndOfTaskController(getWorld());
		} catch (InvalidWorldException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
		}
		UnitAdapter unitAdapter = (UnitAdapter) unit;
		Unit u = unitAdapter.getUnit();
		try {
			controller.indicateEndOfTask(u);
		} catch (InvalidUnitException ex) {
			//We assume this can't happen
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidEmergencyStatusException ex) {
			//TODO: kan dit eigenlijk voorkomen?
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidFinishJobException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
		}
	}

	@Override
	public void indicateProblem(IUnit unit) throws  EmergencyDispatchException {
		throw new IndicateProblemNotSupportedException("Units can't encounter a problem.");
	}

	@Override
	public void indicateRepair(IUnit unit) throws EmergencyDispatchException {
		throw new IndicateRepairNotSupportedException("Units can't be repaired.");
	}

	@Override
	public void advanceTime(ITime time) throws EmergencyDispatchException {
		world.setTime(time.getHours() * 3600 + time.getMinutes() * 60 + world.getTime());

		//TODO: voor timestamp
//		CreateEmergencyController cec = null;
//		try {
//			cec = new CreateEmergencyController(getWorld());
//		} catch (InvalidWorldException ex) {
//			throw new EmergencyDispatchException("The world is invalid");
//		}
//		for (Emergency e : getTodoEmergencies().keySet()) {
//			if (getTodoEmergencies().get(e) <= world.getTime()) {
//				cec.addCreatedEmergencyToTheWorld(e);
//				getWorld().getEmergencyList().addEmergency(e);
//				deleteTodoEmergency(e);
//			}
//		}

		try {
			getWorld().getTimeSensitiveList().timeAhead(time.getHours() * 3600 + time.getMinutes() * 60 + world.getTime());
		} catch (InvalidDurationException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
		}
	}

	@Override
	public void withdrawUnit(IUnit unit, IEmergency emergency) throws EmergencyDispatchException {
		RemoveUnitAssignmentFromEmergencyController ruac = null;
		try {
			ruac = new RemoveUnitAssignmentFromEmergencyController(getWorld());
		} catch (InvalidWorldException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}

		Unit u = ((UnitAdapter) unit).getUnit();
		try {
			ruac.withdrawUnit(u);
		} catch (InvalidWithdrawalException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
		} catch (InvalidEmergencyStatusException ex) {
			//TODO: kan dit eigenlijk voorkomen?
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidMapItemException ex) {
			//We assume this can't happen.
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void cancelEmergency(IEmergency emergency) throws EmergencyDispatchException {
		throw new CancelEmergencyNotSupportedException("An emergency can't be cancelled.");
	}

	@Override
	public void clearSystem() {
		try {
			world = Main.initWorld();
		} catch (InvalidEmergencyTypeNameException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void loadFile(File file) throws EmergencyDispatchException {
		EnvironmentReader er = null;
		try {
			er = new EnvironmentReader(new ReadEnvironmentDataController(world));
		} catch (InvalidControllerException ex) {
			//We assume this can't happen
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidWorldException ex) {
			throw new EmergencyDispatchException(ex.getMessage());
		}

		try {
			FileInputStream fis = new FileInputStream(file);
			er.readEnvironmentData(fis);
			fis.close();
		} catch (Exception ex) {
			System.out.println(String.format("ERROR: %s", ex.getMessage()));
			System.out.println("program will now stop.");
		}
	}

	@Override
	public void loadEnvironment(File file) throws EmergencyDispatchException {
		clearSystem();
		loadFile(file);
	}

	public void loadEnvironmentWithoutClear(File file) throws EmergencyDispatchException {
		loadFile(file);
	}
}
