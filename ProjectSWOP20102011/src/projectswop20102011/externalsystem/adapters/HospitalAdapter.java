package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IHospital;
import be.kuleuven.cs.swop.api.ILocation;
import projectswop20102011.domain.Hospital;

/**
 * A HospitalAdapter that offers operations to inspect the properties of a hospital.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class HospitalAdapter implements IHospital {

	/**
	 * The Hospital of this HospitalAdapter.
	 */
	private final Hospital hospital;

	/**
	 * Creates a HospitalAdapter from the given Hospital.
	 * @param hospital
	 *		The Hospital that is used to create the HospitalAdapter.
	 */
	public HospitalAdapter(Hospital hospital) {
		this.hospital = hospital;
	}

	/**
	 * Returns the Hospital of this HospitalAdapter.
	 * @return The Hospital of this HospitalAdapter.
	 */
	public Hospital getHospital(){
		return hospital;
	}

	/**
	 * Retrieves the name of the hospital.
	 * @return The name of the hospital.
	 */
	@Override
	public String getName() {
		return getHospital().getName();
	}

	/**
	 * Retrieves the location of the hospital.
	 * @return The location of the hospital.
	 */
	@Override
	public ILocation getLocation() {
		return new LocationAdapter(getHospital().getHomeLocation());
	}
}
