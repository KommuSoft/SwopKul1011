package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IPoliceCarView;
import projectswop20102011.domain.Policecar;

public class PoliceCarAdapter implements IPoliceCarView {

	final private Policecar policeCar;

	public PoliceCarAdapter(Policecar policeCar) {
		this.policeCar = policeCar;
	}

	private Policecar getPoliceCar() {
		return policeCar;
	}
}
