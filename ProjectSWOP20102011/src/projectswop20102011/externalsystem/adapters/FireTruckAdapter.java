package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IFireTruckView;
import projectswop20102011.domain.Firetruck;

/**
 * An adapter that offers access to the data of a fire truck.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireTruckAdapter extends UnitAdapter implements IFireTruckView{

	/**
	 * Creates a FireTruckAdapter from the given Firetruck.
	 * @param fireTruck
	 *		The FireTruck that is used to create the FireTruckAdapter.
	 */
	public FireTruckAdapter(Firetruck fireTruck){
		super(fireTruck);
	}

	/**
	 * Retrieves the capacity of a fire truck.
	 * @return The capacity of the fire truck.
	 */
	@Override
	public int getCapacity() {
		return (int) ((Firetruck) getUnit()).getCapacity();
	}

}
