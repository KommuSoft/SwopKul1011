package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.EmergencyState;
import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IUnit;
import java.util.ArrayList;
import java.util.List;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.domain.Unit;

public class EmergencyAdapter implements IEmergency {

	private final Emergency emergency;

	public EmergencyAdapter(Emergency emergency) {
		this.emergency = emergency;
	}

	public Emergency getEmergency() {
		return emergency;
	}

	@Override
	public EmergencyState getState() {
		EmergencyStatus status = getEmergency().getStatus();
		if (status.toString().equalsIgnoreCase("recorded but unhandled")) {
			return EmergencyState.UNHANDLED;
		} else if (status.toString().equalsIgnoreCase("response in progress")) {
			return EmergencyState.RESPONDED;
		} else {
			return EmergencyState.COMPLETED;
		}
	}

	@Override
	public List<IUnit> getAssignedUnits() {
		List<Unit> units = getEmergency().getWorkingUnits();
		List<IUnit> iUnits = new ArrayList<IUnit>();
		for (Unit u : units) {
			iUnits.add(new UnitAdapter(u));
		}

		return iUnits;
	}

	@Override
	public boolean isPartOfDisaster() {
		throw new UnsupportedOperationException("Not supported yet.EA2");
	}
}
