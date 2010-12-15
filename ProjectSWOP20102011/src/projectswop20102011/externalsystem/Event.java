package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.ITime;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A class that represents an event.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class Event implements IEvent {

	private final ITime time;
	private final Location location;
	private Emergency emergency;
	private final World world;

	public Event(ITime time, Location location, Emergency emergency, World world) {
		this.time = time;
		this.location = location;
		this.world = world;
	}

	@Override
	public ITime getTime() {
		return time;
	}

	@Override
	public ILocation getLocation(){
		return location;
	}

	@Override
	public String getSeverity(){
		return getEmergency().getSeverity().getTextual();
	}

	@Override
	public abstract String getType();

	protected Emergency getEmergency() {
		return emergency;
	}

	protected void setEmergency(Emergency emergency){
		this.emergency = emergency;
	}

	protected World getWorld() {
		return world;
	}

	@Override
	public Map<String, String> getEventProperties(){
		InspectEmergenciesController iec = null;
		try {
			iec = new InspectEmergenciesController(getWorld());
		} catch (InvalidWorldException ex) {
			Logger.getLogger(FireEvent.class.getName()).log(Level.SEVERE, null, ex);
		}

		ConcurrentHashMap<String, String> result = new ConcurrentHashMap<String, String>();
		Set<Entry<String, String>> entries = iec.getEmergencyShortInformation(getEmergency());
		result.put("time", getTime().toString());
		for (Entry<String, String> entry : entries) {
			if(entry.getKey().equals("location") || entry.getKey().equals("severity") || entry.getKey().equals("type")){
				result.put(entry.getKey(), entry.getValue());
			}
		}

		return result;
	}

	@Override
	public int compareTo(IEvent o) {
		return getTime().compareTo(o.getTime());
	}
}
