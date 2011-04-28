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
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.factories.EmergencyFactory;

/**
 * A class that represents the EmergencyDispatchApi
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
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
     *		If an exception occurs in the emergency dispatch system.
     */
    @Override
    public void registerNewEvent(IEvent event) throws EmergencyDispatchException {
        try {
            CreateEmergencyController cec = new CreateEmergencyController(getWorld());
            Map<String,String> parameters = event.getEventProperties();
            parameters.put("location", new GPSCoordinate(event.getLocation().getX(),event.getLocation().getY()).toString());
            parameters.put("severity", event.getSeverity());
            parameters.put("description", "");
            Emergency emergency = null;
            EmergencyFactory factory = this.getWorld().getEmergencyFactoryList().getGenericFactoryFromName(event.getType());
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
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<IEmergency> getListOfDisasters(EmergencyState state) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<IUnit> getListOfUnits(UnitState state) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<IHospital> getListOfHospitals() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public IUnitConfiguration getUnitConfiguration(IEmergency emergency) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void assignUnits(IUnitConfiguration configuration) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void createDisaster(String description, List<IEmergency> emergencies) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void selectHospital(IUnit unit, IHospital hospital) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void indicateEndOfTask(IUnit unit, IEmergency emergency) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void indicateProblem(IUnit unit) throws NotSupportedException, EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void indicateRepair(IUnit unit) throws NotSupportedException, EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void advanceTime(ITime time) throws EmergencyDispatchException {
		long seconds = time.getHours()*3600 + time.getMinutes()*60;

		

	}

	@Override
	public void withdrawUnit(IUnit unit, IEmergency emergency) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void cancelEmergency(IEmergency emergency) throws NotSupportedException, EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void clearSystem() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void loadEnvironment(File file) throws EmergencyDispatchException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
