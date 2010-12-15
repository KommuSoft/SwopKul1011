package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.ITime;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.factories.FireFactory;

public class FireEvent extends Event {

	public FireEvent(ITime time, World world, Location location, EmergencySeverity severity, String description, FireSize size, boolean chemical, long trappedPeople, long numberOfInjured) throws InvalidWorldException, InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyTypeNameException, InvalidParameterException {
		super(time, location, null, world);

		FireFactory ff = new FireFactory();
		//TODO location is hier van de klasse Location en niet van het verwachte type GPSCoordinate
		Object[] parameters = {convertLocation(location), severity, description, size, chemical, trappedPeople, numberOfInjured};
		setEmergency(ff.createEmergency(parameters));
	}

	//TODO adapter pattern?
	private GPSCoordinate convertLocation(Location location){
		return new GPSCoordinate(location.getX(), location.getY());
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
		Set<Entry<String, String>> entries = iec.getEmergencyLongInformation(getEmergency());
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
