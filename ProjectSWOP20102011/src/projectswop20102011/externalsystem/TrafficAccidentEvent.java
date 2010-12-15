package projectswop20102011.externalsystem;

import java.util.Map;

public class TrafficAccidentEvent extends Event {

	public TrafficAccidentEvent(Time time) {
		super(null, null, null, null);
	}

	@Override
	public Map<String, String> getEventProperties() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String getType() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
