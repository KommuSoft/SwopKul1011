package projectswop20102011.domain;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;

/**
 * A class that represents a policecar.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Policecar extends Unit {

	/**
	 * Initialize a new policecar with given parameters.
	 *
	 * @param name
	 *		The name of the new policecar.
	 * @param homeLocation
	 *		The home location of the new policecar.
	 * @param speed
	 *		The speed of the new policecar.
	 * @effect The new policecar is a unit with given name, home location, speed.
	 *		| super(name,homeLocation,speed);
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a policecar.
	 * @throws InvalidMapItemNameException
	 *		If the given name is an invalid name for a policecar.
	 * @throws InvalidSpeedException
	 *		If the given speed is an invalid speed for a policecar.
	 */
	public Policecar(String name, GPSCoordinate homeLocation, long speed) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException {
		super(name, homeLocation, speed);
	}

	/**
	 * Clone this policecar
	 * @return A clone of this policecar.
	 */
	@Override
	public Policecar clone() {
		Policecar pol = null;
		try {
			pol = new Policecar(this.getName(), this.getHomeLocation(), this.getSpeed());
		} catch (InvalidLocationException ex) {
			Logger.getLogger(Ambulance.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidMapItemNameException ex) {
			Logger.getLogger(Ambulance.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidSpeedException ex) {
			Logger.getLogger(Ambulance.class.getName()).log(Level.SEVERE, null, ex);
		}
		return pol;
	}

	/**
	 * Checks whether all assigned policecars are present at the location of the emergency
	 * @return True if all assigned policecars of this type are present at the location of the emergency, false otherwise.
	 */
	@Override
	public boolean arePresent() {
		ArrayList<Unit> workingUnits = getEmergency().getWorkingUnits();
		TypeUnitValidator tuv = null;
		try {
			tuv = new TypeUnitValidator(Policecar.class);
		} catch (InvalidClassException ex) {
			Logger.getLogger(Ambulance.class.getName()).log(Level.SEVERE, null, ex);
		}
		for (Unit u : workingUnits) {
			if (tuv.isValid(u) && !u.isAtDestination()) {
				return false;
			}
		}
		return true;
	}
}
