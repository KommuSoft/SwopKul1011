package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IPoliceCarView;
import projectswop20102011.domain.Policecar;

/**
 * A PoliceCarAdapter that offers access to the data of a police car.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class PoliceCarAdapter extends UnitAdapter implements IPoliceCarView {

	/**
	 * Creates a PoliceCarAdapter from the given Policecar.
	 * @param policeCar
	 *		The Policecar that is used to create the PoliceCarAdapter.
	 */
	public PoliceCarAdapter(Policecar policeCar) {
		super(policeCar);
	}
}
