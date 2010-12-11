package projectswop20102011.externalsystem.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import projectswop20102011.controllers.CreateEmergencyController;
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
		CreateEmergencyController cec = new CreateEmergencyController(world);
		cec.createFireEmergency(location, severity, description, size, chemical, trappedPeople, numberOfInjured);
	}

	@Override
	public Map<String, String> getEventProperties() {
		ConcurrentHashMap<String, String> result = new ConcurrentHashMap<String, String>();

		String name;
		String properties;
		result.put(name, properties);
		//naam van de emergency in key steken
		//longInformation in value steken

		return result;
	}

	@Override
	public String getType() {
		return "Fire";
	}
}
