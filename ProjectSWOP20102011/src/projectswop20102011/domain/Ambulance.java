package projectswop20102011.domain;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
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
	 * @effect The emergency of this unit is null
	 *		| this.getEmergency().equals(null)
	 * @effect The flag wasAlreadyAtSite is set to false.
	 *		|this.getWasAlreadyAtSite().equals(false)
	 * @throws InvalidFinishJobException
	 *          If the unit can't finish his job (not assigned to an emergency, not at it's destination).
	 * @throws InvalidEmergencyStatusException
	 *          If the status of the emergency where this unit is assigned to, does not allow units to finish their job.
	 */
	@Override
	public void finishedJob() throws InvalidEmergencyStatusException, InvalidFinishJobException {
		if (!canFinishJob()) {
			throw new InvalidFinishJobException("Unit can't finish his job.");
		} else {
			getEmergency().finishUnit(this);
			Emergency e = getEmergency();
			setEmergency(null);
			setWasAlreadyAtSite(false);
			setUnitStatus(UnitStatus.IDLE);
			if (isRequired()) {
				HashSet<Unit> ambulance = new HashSet<Unit>(0);
				ambulance.add(this);
				try {
					e.getStatus().assignUnits(e.getUnitsNeeded(), ambulance);
				} catch (InvalidEmergencyException ex) {
					Logger.getLogger(Ambulance.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

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

	@Override
	public boolean arePresent() {
		//TODO: deze methode zal niet gebruikt worden, hoe oplossen?
		return false;
	}
}
