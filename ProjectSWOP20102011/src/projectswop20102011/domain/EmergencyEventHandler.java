package projectswop20102011.domain;

public interface EmergencyEventHandler {
	public void doEvent();
	public void setEmergency(Emergency emergency);
	public void setUnit(Unit unit);
}
