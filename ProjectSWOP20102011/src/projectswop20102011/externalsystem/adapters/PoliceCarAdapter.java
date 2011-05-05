package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IPoliceCarView;
import projectswop20102011.domain.Policecar;

public class PoliceCarAdapter extends UnitAdapter implements IPoliceCarView {

	public PoliceCarAdapter(Policecar policeCar) {
		super(policeCar);
	}
}
