package projectswop20102011.externalsystem.api;

import java.util.Map;

public class RobberyEvent extends Event {

	public RobberyEvent(Time time) {
		super("Robbery", time);
	}

	@Override
	public Map<String, String> getEventProperties() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
