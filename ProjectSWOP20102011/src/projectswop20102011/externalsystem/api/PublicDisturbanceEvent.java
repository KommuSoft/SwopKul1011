package projectswop20102011.externalsystem.api;

import java.util.Map;

public class PublicDisturbanceEvent extends Event {

	public PublicDisturbanceEvent(Time time) {
		super("PublicDisturbance", time);
	}

	@Override
	public Map<String, String> getEventProperties() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
