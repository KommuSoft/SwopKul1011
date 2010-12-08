package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.ITime;
import java.util.Map;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidWorldException;

public class Event implements IEvent{
	private InspectEmergenciesController iec;
	private Time time;

	public Event(World world) throws InvalidWorldException{
		iec = new InspectEmergenciesController(world);
	}

	@Override
	public ITime getTime() {
		//moet dit gekloond worden of niet (en wat is het fucking nut van die time class)
		return time;
	}

	@Override
	public String getType() {
		//type filteren uit de shortinformation
	}

	@Override
	public Map<String, String> getEventProperties() {
		//naam van de emergency in key steken
		//longInformation in value steken
	}

	@Override
	public int compareTo(IEvent o) {
		return getTime().compareTo(o.getTime());
	}

}
