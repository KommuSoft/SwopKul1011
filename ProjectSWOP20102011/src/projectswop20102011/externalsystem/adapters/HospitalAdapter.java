package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IHospital;
import be.kuleuven.cs.swop.api.ILocation;
import projectswop20102011.domain.Hospital;

public class HospitalAdapter implements IHospital {

	private final Hospital hospital;

	public HospitalAdapter(Hospital hospital) {
		this.hospital = hospital;
	}

	public Hospital getHospital(){
		return hospital;
	}

	@Override
	public String getName() {
		return getHospital().getName();
	}

	@Override
	public ILocation getLocation() {
		return new LocationAdapter(getHospital().getHomeLocation());
	}
}
