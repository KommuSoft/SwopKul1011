package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.ITime;
import java.util.Map;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.World;

public abstract class Event implements IEvent {

	private final Time time;
	private Emergency emergency;
	private World world;

	public Event(Time time, Emergency emergency) {
		this.time = time;
		setEmergency(emergency);
	}

	@Override
	public ITime getTime() {
		return time;
	}

	Emergency getEmergency() {
		return emergency;
	}

	private void setEmergency(Emergency emergency) {
		this.emergency = emergency;
	}

	@Override
	public abstract String getType();

	protected void setWorld(World world) {
		this.world = world;
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
