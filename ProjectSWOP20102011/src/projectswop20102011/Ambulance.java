package projectswop20102011;

import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidHospitalException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

/**
 * A class that represents an ambulance.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Ambulance extends Unit {

    /**
     * The selected hospital of the ambulance.
     */
    private Hospital currentHospital = null;

    /**
     * Initialize a new ambulance with given parameters.
     *
     * @param name
     *		The name of the new ambulance.
     * @param homeLocation
     *		The home location of the new ambulance.
     * @param speed
     *		The speed of the new ambulance.
     * @effect The new ambulance is a unit with given name, home location, speed,
     *			current location, destination and assigned indicator.
     *         |super(name,homeLocation,speed,currentLocation,destination,assigned);
     * @throws InvalidLocationException
     *		If the given location is an invalid location for an ambulance.
     * @throws InvalidUnitBuildingNameException
     *		If the given name is an invalid name for an ambulance.
     * @throws InvalidSpeedException
     *		If the given speed is an invalid speed for an ambulance.
     */
    public Ambulance(String name, GPSCoordinate homeLocation, long speed) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        super(name, homeLocation, speed);
    }

    /**
     * Returns the current hospital where the ambulance is assigned to.
     * @return the current hospital where the ambulance is assigned to.
     */
    public Hospital getCurrentHospital() {
        return this.currentHospital;
    }

    /**
     * Sets the current hospital chosen by this ambulance.
     * @param hospital The hospital chosen by this ambulance.
     */
    private void setCurrentHospital(Hospital hospital) {
        this.currentHospital = hospital;
    }

    /**
     * Select a hospital where the ambulance will drive the injured patients to.
     * @param hospital The hospital to assign to the ambulance.
     * @post The selected hopsital of this ambulance is equal to the given hospital | this.getCurrentHospital().equals(hospital)
     * @throws InvalidAmbulanceException If this ambulance is not assigned to an emergency or is not at the location of the emergency.
     * @throws InvalidHospitalException If the given hospital is not effective.
     */
    public void selectHospital(Hospital hospital) throws InvalidAmbulanceException, InvalidHospitalException {
        if (hospital == null) {
            throw new InvalidHospitalException("The selected hospital must be effective");
        }
        if (!this.isAssigned() || !this.isAtDestination()) {
            throw new InvalidAmbulanceException("Ambulance must be assigned and at the emergency.");
        }
        this.setCurrentHospital(hospital);
    }

    /**
     * Finishing the job of the ambulance.
     * @effect super.finishedJob()
     * @post the selected hospital of this ambulance is null | this.getHospital().equals(null)
     * @throws InvalidEmergencyException
     *		If the unit is not assigned to an emergency.
     * @throws InvalidLocationException
     *		If the unit is not at the location of the emergency.
     */
    @Override
    public void finishedJob() throws InvalidEmergencyException, InvalidLocationException {
        this.setCurrentHospital(null);
        super.finishedJob();
    }
}
