package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.ITime;
import java.util.Map;

public abstract class Event implements IEvent {
	private final Time time;

	public Event(Time time) {
		this.time = time;
	}

	@Override
	public ITime getTime() {
		return time;
	}

	@Override
	public abstract String getType();

	@Override
	public abstract Map<String, String> getEventProperties();

	@Override
	public int compareTo(IEvent o) {
		return getTime().compareTo(o.getTime());
	}
}
