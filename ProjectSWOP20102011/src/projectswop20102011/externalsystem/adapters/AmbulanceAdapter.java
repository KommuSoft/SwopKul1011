package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IAmbulanceView;
import be.kuleuven.cs.swop.api.UnitState;
import projectswop20102011.domain.Ambulance;

/**
 * An adapter for an IAmbulanceView that offers access to the data of an ambulance.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AmbulanceAdapter extends UnitAdapter implements IAmbulanceView{

	/**
	 * Creates an AmbulanceAdapter from the given Ambulance.
	 * @param ambulance
	 *		The Ambulance that is used to create an AmbulanceAdapter.
	 */
	public AmbulanceAdapter(Ambulance ambulance) {
		super(ambulance);
	}

	/**
	 * Indicates whether the ambulance is currently carrying a victim or not.
	 * @return True if the ambulance is carrying a victim; false otherwise.
	 */
	@Override
	public boolean isOccupied() {
		return getState().equals(UnitState.OCCUPIED);
	}
}
