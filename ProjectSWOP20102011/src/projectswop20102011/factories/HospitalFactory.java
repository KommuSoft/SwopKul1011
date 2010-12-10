package projectswop20102011.factories;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Hospital;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents a HospitalFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class HospitalFactory extends MapItemFactory{

	/**
	 * Creates a new HospitalFactory.
	 *
	 * @throws InvalidUnitBuildingTypeNameException
	 *		If the type name of the new HospitalFactory is invalid.
	 */
	public HospitalFactory() throws InvalidMapItemTypeNameException{
		super("hospital");
	}

	/**
	 * Creates a new Hospital.
	 *
	 * @param parameters
	 *		The parameters to construct the Hospital.
	 * @return The new Hospital.
	 * @throws InvalidParameterException
	 *		If the parameters are invalid for this Hospital.
	 */
	@Override
	public Hospital createUnitBuilding(Object[] parameters) throws InvalidParameterException {
		if(areValidParameters(parameters)){
			String name = (String) parameters[0];
			GPSCoordinate homeLocation = (GPSCoordinate) parameters[1];
            try {
                return new Hospital(name, homeLocation);
            } catch (InvalidMapItemNameException ex) {
                Logger.getLogger(HospitalFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidLocationException ex) {
                Logger.getLogger(HospitalFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
		} else {
			throw new InvalidParameterException("Invalid parameters for this fire truck.");
		}
		return null;
	}

	/**
	 * Returns true if the given parameters are valid parameters for a Hospital.
	 * @param parameters
	 *		The desired parameters of the Hospital.
	 * @return
	 *		True if the given parameters are valid for a Hospital; false otherwise.
	 */
	public boolean areValidParameters(Object[] parameters){
		if(parameters.length != 2){
			return false;
		}
		if (parameters[0].getClass() != String.class ||
				parameters[1].getClass() != GPSCoordinate.class){
			return false;
		}
		return true;
	}

}