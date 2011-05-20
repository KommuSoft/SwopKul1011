package projectswop20102011.domain;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidFinishJobException;
import projectswop20102011.exceptions.InvalidHospitalException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidTargetableException;

/**
 * A class that represents an ambulance.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Ambulance extends Unit {

    /**
     * The maximum number of units an abulance can transport.
     */
    public static final long MAXIMUM_NUMBER_OF_PATIENTS = 2;
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
     * @effect The new ambulance is a unit with given name, home location and speed.
     *		|super(name,homeLocation,speed)
     * @effect The new ambulance has no current hospital. Its value is null.
     *		|getCurrentHospital() == null
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
     * Sets the current hospital chosen by this ambulance.
     * @param currentHospital
     *		The hospital chosen by this ambulance.
     * @post The current hospital of this ambulance is set to the given.
     *		| this.currentHospital = new.currentHospital
	 * @effect The target of this ambulance is set to the hospital if the hospital is effective.
	 *		| setTarget(currentHospital)
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
     *		| this.getCurrentHospital() = hospital
	 * @effect The status of this unit is set to occupied.
	 *		| setUnitStatus(UnitStatus.OCCUPIED)
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
        setUnitStatus(UnitStatus.OCCUPIED);
    }

    /**
     * Tests if the unit can finish it's job.
     * @return True if the conditions for a unit are true and the unit current hospital is effective, otherwise false.
     */
    @Override
    public boolean canFinishJob() {
        return super.canFinishJob() && getCurrentHospital() != null;
    }

    /**
     * Finishes the job of this Unit.
	 * @param eventHandler 
	 *		The event handler where the finished should be signaled to.
	 * @effect The emergency of this unit is null.
     *		| this.getEmergency().equals(null)
     * @effect The flag wasAlreadyAtSite is set to false.
     *		| this.getWasAlreadyAtSite().equals(false)
	 * @effect The unit status is set to IDLE.
	 *		| setUnitStatus(UnitStatus.IDLE)
	 * @effect The hospital of this ambulance is set to null.
	 *		| setCurrentHospital(null)
     * @throws InvalidFinishJobException
	 *		If the unit can't finish his job (not assigned to an emergency, not at it's destination).
	 * @throws InvalidSendableStatusException
	 *		If the status of the sendable where this unit is assigned to, does not allow units to finish their job.
     */
    @Override
    public void finishedJob(EventHandler eventHandler) throws InvalidFinishJobException, InvalidSendableStatusException {
        if (!canFinishJob()) {
            throw new InvalidFinishJobException("Unit can't finish his job.");
        } else {
            boolean isRequired = isRequired();
            Sendable manager = this.getManagingSendable();
            Emergency e = getEmergency();
            setWasAlreadyAtSite(false);
            setUnitStatus(UnitStatus.IDLE);
            getManagingSendable().finishUnit(this,eventHandler);
            setEmergency(null);
            setCurrentHospital(null);

            if (isRequired) {//TODO: wat doet deze structuur hier?
                HashSet<Unit> ambulance = new HashSet<Unit>(0);
                ambulance.add(this);
                try {
                    e.getStatus().assignUnits(e.getUnitsNeeded(), ambulance, eventHandler);
                } catch (InvalidEmergencyException ex) {
                    Logger.getLogger(Ambulance.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    manager.afterFinish(this,eventHandler);
                } catch (Exception ex) {
                    Logger.getLogger(Ambulance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

	/**
	 * Clones this ambulance.
	 * @return A clone of this ambulance.
	 */
    @Override
    public Ambulance clone() {
        Ambulance amb = null;
        try {
            amb = new Ambulance(this.getName(), this.getHomeLocation(), this.getSpeed());
        } catch (InvalidLocationException ex) {
            Logger.getLogger(Ambulance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidMapItemNameException ex) {
            Logger.getLogger(Ambulance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidSpeedException ex) {
            Logger.getLogger(Ambulance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amb;
    }

	/**
	 * Checks whether all needed units of this type are present at the location of the emergency.
	 * @return True if all needed units of this  type are present at the location of the emergency; false otherwise.
	 */
    @Override
    public boolean arePresent() {
        //TODO: deze methode zal niet gebruikt worden, hoe oplossen?
		//TODO: ofwel is de naam slecht ofwel hoort deze methode hier niet. Ik [jonas] verwacht dat er isPresent zou staan.
		//Als het effectief arePresent moet zijn: wat doe deze methode hier. Een ambulance is toch niet verantwoordelijk of de andere ambulances present zijn?
        return false;
    }

    /**
     * Calculates the minimum number of ambulances based on the number of patients.
     * @param patients
	 *		The number of patients to transport.
     * @return The minimum number of ambulances to the patients.
     */
    public static long getMinimumNumberOfAmbulances(long patients) {
        return (patients + MAXIMUM_NUMBER_OF_PATIENTS - 1) / MAXIMUM_NUMBER_OF_PATIENTS;
    }

    /**
     * Calculates the maximum number of ambulances based on the number of patients.
     * @param patients
	 *		The number of patients to transport.
     * @return The maximum number of ambulances to transport the patients.
     */
    public static long getMaximumNumberOfAmbulances(long patients) {
        return patients;
    }
}
