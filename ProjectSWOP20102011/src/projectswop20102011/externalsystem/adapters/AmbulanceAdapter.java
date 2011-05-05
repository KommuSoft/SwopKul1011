package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IAmbulanceView;
import be.kuleuven.cs.swop.api.UnitState;
import projectswop20102011.domain.Ambulance;

public class AmbulanceAdapter extends UnitAdapter implements IAmbulanceView{

	public AmbulanceAdapter(Ambulance ambulance) {
		super(ambulance);
	}

	@Override
	public boolean isOccupied() {
		return getState().equals(UnitState.OCCUPIED);
	}
}
