package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IFireTruckView;
import projectswop20102011.domain.Firetruck;

public class FireTruckAdapter implements IFireTruckView{
	final private Firetruck fireTruck;

	public FireTruckAdapter(Firetruck fireTruck){
		this.fireTruck = fireTruck;
	}

	private Firetruck getFireTruck(){
		return fireTruck;
	}

	@Override
	public int getCapacity() {
		return (int) getFireTruck().getCapacity();
	}

}
