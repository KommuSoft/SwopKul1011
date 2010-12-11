package projectswop20102011.externalsystem.api;

import java.util.Map;

public class TrafficAccidentEvent extends Event {

	public TrafficAccidentEvent(Time time) {
		super("TrafficAccident", time);
	}

	@Override
	public Map<String, String> getEventProperties() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
