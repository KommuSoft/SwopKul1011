package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IAmbulanceView;
import projectswop20102011.domain.Ambulance;

public class AmbulanceAdapter implements IAmbulanceView {

	final private Ambulance ambulance;

	public AmbulanceAdapter(Ambulance ambulance) {
		this.ambulance = ambulance;
	}

	private Ambulance getAmbulance() {
		return ambulance;
	}

	@Override
	public boolean isOccupied() {
		getAmbulance().isOccupied();
	}
}
