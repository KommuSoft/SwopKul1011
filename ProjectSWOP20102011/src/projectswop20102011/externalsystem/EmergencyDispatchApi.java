package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.IEvent;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.factories.EmergencyFactory;
import projectswop20102011.factories.FireFactory;
import projectswop20102011.factories.PublicDisturbanceFactory;
import projectswop20102011.factories.RobberyFactory;
import projectswop20102011.factories.TrafficAccidentFactory;

public class EmergencyDispatchApi implements IEmergencyDispatchApi {

	private World world;

	public EmergencyDispatchApi(World world) {
		setWorld(world);
	}

	private void setWorld(World world) {
		this.world = world;
	}

	private World getWorld() {
		return world;
	}

	@Override
	public void registerNewEvent(IEvent event) throws EmergencyDispatchException {
		CreateEmergencyController cec = null;
		try {
			cec = new CreateEmergencyController(getWorld());
		} catch (InvalidWorldException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}

		Emergency emergency = null;
		EmergencyFactory factory = null;
		Map<String, String> properties;
		properties = event.getEventProperties();
		GPSCoordinate gps = new GPSCoordinate(event.getLocation().getX(), event.getLocation().getY());
		EmergencySeverity es = null;
		try {
			es = EmergencySeverity.parse(event.getSeverity());
		} catch (InvalidEmergencySeverityException ex) {
			Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (event.getType().equals("Fire")) {
			try {
				factory = new FireFactory();
			} catch (InvalidEmergencyTypeNameException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
			FireSize fs = null;
			try {
				fs = FireSize.parse(properties.get("size"));
			} catch (InvalidFireSizeException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {
				emergency = factory.createEmergency(new Object[]{gps, es, "", fs, properties.get("chemical").equals("true") ? true : false, properties.get("trappedPeople").equals("true") ? 1L : 0L, Long.parseLong(properties.get("numberOfInjured"))});
			} catch (Exception ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else if (event.getType().equals("PublicDisturbance")) {
			try {
				factory = new PublicDisturbanceFactory();
			} catch (InvalidEmergencyTypeNameException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {
				emergency = factory.createEmergency(new Object[]{gps, es, "", Long.parseLong(properties.get("numberOfPeople"))});
			} catch (Exception ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else if (event.getType().equals("Robbery")) {
			try {
				factory = new RobberyFactory();
			} catch (InvalidEmergencyTypeNameException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {
				emergency = factory.createEmergency(new Object[]{gps, es, "", properties.get("armed").equals("true") ? true : false, properties.get("inProgress").equals("true") ? true : false});
			} catch (Exception ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else if (event.getType().equals("TrafficAccident")) {try {
				factory = new TrafficAccidentFactory();
			} catch (InvalidEmergencyTypeNameException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {
				emergency = factory.createEmergency(new Object[]{gps, es, "", Long.parseLong(properties.get("numberOfCars")), Long.parseLong(properties.get("numberOfInjured"))});
			} catch (Exception ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
		}


		if (event.getTime().getHours() * 3600 + event.getTime().getMinutes() * 60 <= getWorld().getTime()) {
			//TODO deze methode heb ik ff public gezet
			cec.addCreatedEmergencyToTheWorld(emergency);
		} else {
			TimeAheadController tac = null;
			try {
				tac = new TimeAheadController(world);
			} catch (InvalidWorldException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {
				tac.doTimeAheadAction((event.getTime().getHours() * 3600 + event.getTime().getMinutes() * 60) - getWorld().getTime());
			} catch (InvalidDurationException ex) {
				Logger.getLogger(EmergencyDispatchApi.class.getName()).log(Level.SEVERE, null, ex);
			}
			cec.addCreatedEmergencyToTheWorld(emergency);
		}
	}
}
