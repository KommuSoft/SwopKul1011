package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.ITime;
import java.util.Map;

public class Event implements IEvent{

	@Override
	public ITime getTime() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String getType() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Map<String, String> getEventProperties() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int compareTo(IEvent o) {
		return getTime().compareTo(o.getTime());
	}

}
