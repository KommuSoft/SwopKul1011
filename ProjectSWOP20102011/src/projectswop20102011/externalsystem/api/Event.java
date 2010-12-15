package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.ILocation;
import be.kuleuven.cs.swop.api.ITime;
import java.util.Map;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.World;

/**
 * A class that represents an event.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class Event implements IEvent {

	private final Time time;
	private final Location location;
	private Emergency emergency;
	private final World world;

	public Event(Time time, Location location, Emergency emergency, World world) {
		this.time = time;
		this.location = location;
		setEmergency(emergency);
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
	public abstract Map<String, String> getEventProperties();

	@Override
	public int compareTo(IEvent o) {
		return getTime().compareTo(o.getTime());
	}
}
