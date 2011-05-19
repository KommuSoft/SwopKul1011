package projectswop20102011.eventhandlers;

import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EventHandler;
import projectswop20102011.domain.Unit;

public class NullEventHandler extends EventHandler{

	@Override
	public void doAssign(Emergency emergency, Unit unit) {
	}

	@Override
	public void doRelease(Emergency emergency, Unit u) {
	}

	@Override
	public void doTimeSet(long time) {
	}
	
}
