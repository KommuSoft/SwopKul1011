package projectswop20102011.domain;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidHospitalException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidTargetableException;

/**
 * A class that represents an ambulance.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Ambulance extends Unit {

    /**
     * A variable registering the selected hospital of the ambulance.
     */
    private Hospital currentHospital;

    /**
     * Initialize a new ambulance with given parameters.
     *
     * @param name
     *		The name of the new ambulance.
     * @param homeLocation
     *		The home location of the new ambulance.
     * @param speed
     *		The speed of the new ambulance.
     * @effect The new ambulance is a unit with given name, home location, speed.
     *		|super(name,homeLocation,speed)
     * @effect The new ambulance has no current hospital. Its value is null.
     *		|getCurrentHospital.equals(null)
     * @effect The withdrawBehavior of this new ambulance is set to the given normalwithdraw behavior.
     *      |setWithdrawBehavior(new NormalWithdraw())
     * @throws InvalidLocationException
     *		If the given location is an invalid location for an ambulance.
     * @throws InvalidMapItemNameException
     *		If the given name is an invalid name for an ambulance.
     * @throws InvalidSpeedException
     *		If the given speed is an invalid speed for an ambulance.
     */
    public Ambulance(String name, GPSCoordinate homeLocation, long speed) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException {
        super(name, homeLocation, speed);
        setCurrentHospital(null);
    }

    /**
     * Returns the current hospital where the ambulance is assigned to.
     * @return The current hospital where the ambulance is assigned to.
     */
    public Hospital getCurrentHospital() {
        return currentHospital;
    }

    /**
     * TODO: mag waarschijnlijk weg, werken nu met target (DELETE COMMENTED CODE)
     * Returns the destination of this Ambulance.
     * @return The location of the assigned hospital if the ambulance has a hospital as destination,
     *         otherwise the homelocation or the location of the emergency.
    @Override
    public GPSCoordinate getDestination() {
    if (getCurrentHospital() == null) {
    return super.getDestination();
    } else {
    return getCurrentHospital().getHomeLocation();
    }
    }*/
    /**
     * Sets the current hospital chosen by this ambulance.
     * @param currentHospital
     *		The hospital chosen by this ambulance.
     * @post The current hospital of this ambulance is set to the given.
     *		| this.currentHospital = new.currentHospital
     */
    private void setCurrentHospital(Hospital currentHospital) {
        this.currentHospital = currentHospital;
        if (currentHospital != null) {
            try {
                this.setTarget(currentHospital);
            } catch (InvalidTargetableException ex) {
                //We assume this can't happen
                Logger.getLogger(Ambulance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Select a hospital where the ambulance will drive the injured patients to.
     * @param hospital
     *		The hospital to assign to the ambulance.
     * @effect The selected hopsital of this ambulance is equal to the given hospital.
     *		| this.getCurrentHospital().equals(hospital)
     * @throws InvalidAmbulanceException
     *		If this ambulance is not assigned to an emergency or is not at the location of the emergency.
     * @throws InvalidHospitalException
     *		If the given hospital is not effective.
     */
    public void selectHospital(Hospital hospital) throws InvalidAmbulanceException, InvalidHospitalException {
        if (hospital == null) {
            throw new InvalidHospitalException("The selected hospital must be effective");
        }
        if (!isAssigned() || !isAtDestination()) {
            throw new InvalidAmbulanceException("Ambulance must be assigned and at the emergency.");
        }
        setCurrentHospital(hospital);
    }

    /**
     * Finishes the job of the ambulance.
     * @effect the selected hospital of this ambulance is null
     *		| this.getHospital().equals(null)
     * @throws InvalidEmergencyException
     *		If the unit is not assigned to an emergency.
     * @throws InvalidLocationException
     *		If the unit is not at the location of the emergency.
     * @throws InvalidEmergencyStatusException
     *		If the emergency status is invalid.
     * @throws Exception
     *		If an error occured.
     */
    @Override
    public void finishedJob() throws InvalidEmergencyException, InvalidLocationException, InvalidEmergencyStatusException {
        if (isAssigned()) {
            if (getCurrentHospital() != null && this.isAtDestination()) {
                getEmergency().finishUnit(this);
                setEmergency(null);
                setWasAlreadyAtSite(false);
            } else {
                throw new InvalidLocationException("The unit is not at its destination.");
            }
        } else {
            throw new InvalidEmergencyException("The unit is not assigned to an emergency so it can't finishes its job.");
        }
    }
}
