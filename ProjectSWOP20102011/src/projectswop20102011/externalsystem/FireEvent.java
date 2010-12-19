package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.ITime;
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

public class FireEvent extends Event {

	public FireEvent(ITime time, World world, Location location, EmergencySeverity severity, String description, FireSize size, boolean chemical, long trappedPeople, long numberOfInjured) throws InvalidWorldException, InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyTypeNameException, InvalidAmountOfParametersException {
		super(time, location, null, world);

		FireFactory ff = new FireFactory();
		Object[] parameters = {convertLocation(location), severity, description, size, chemical, trappedPeople, numberOfInjured};
		setEmergency(ff.createEmergency(parameters));
	}

	//TODO adapter pattern?
	private GPSCoordinate convertLocation(Location location){
		return new GPSCoordinate(location.getX(), location.getY());
	}

	@Override
	public String getType() {
		return "Fire";
	}
}
