package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IFireTruckView;
import projectswop20102011.domain.Firetruck;

public class FireTruckAdapter extends UnitAdapter implements IFireTruckView{

	public FireTruckAdapter(Firetruck fireTruck){
		super(fireTruck);
	}

	@Override
	public int getCapacity() {
		return (int) ((Firetruck) getUnit()).getCapacity();
	}

}
