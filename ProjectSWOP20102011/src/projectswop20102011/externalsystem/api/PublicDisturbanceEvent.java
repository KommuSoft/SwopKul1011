package projectswop20102011.externalsystem.api;

import java.util.Map;

public class PublicDisturbanceEvent extends Event {

	public PublicDisturbanceEvent(){
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
