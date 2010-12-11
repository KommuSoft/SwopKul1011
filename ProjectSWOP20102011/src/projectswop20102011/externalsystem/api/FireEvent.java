package projectswop20102011.externalsystem.api;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class FireEvent extends Event {

	public FireEvent(Time time, World world, GPSCoordinate location, EmergencySeverity severity, String description, FireSize size, boolean chemical, long trappedPeople, long numberOfInjured) throws InvalidWorldException, InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
		super(time);
		setWorld(world);
		CreateEmergencyController cec = new CreateEmergencyController(world);
		cec.createFireEmergency(location, severity, description, size, chemical, trappedPeople, numberOfInjured);
	}

	@Override
	public Map<String, String> getEventProperties() {
		InspectEmergenciesController iec = null;
		try {
			iec = new InspectEmergenciesController(getWorld());
		} catch (InvalidWorldException ex) {
			Logger.getLogger(FireEvent.class.getName()).log(Level.SEVERE, null, ex);
		}

		ConcurrentHashMap<String, String> result = new ConcurrentHashMap<String, String>();
		Set<Entry<String, String>> entries = iec.getEmergencyLongInformation(iec.inspectEmergencyOnId(id));
		for (Entry<String, String> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

	@Override
	public String getType() {
		return "Fire";
	}
}
