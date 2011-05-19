package projectswop20102011.domain;

public abstract class EventHandler {
	public abstract void doAssign(Emergency emergency, Unit unit);
	public abstract void doRelease(Emergency emergency, Unit u);
	public abstract void doTimeSet(long time);
}
