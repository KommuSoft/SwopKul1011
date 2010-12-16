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
public class HospitalFactory extends MapItemFactory {

    /**
     * Creates a new HospitalFactory.
     *
     * @throws InvalidMapItemTypeNameException
     *		If the type name of the new HospitalFactory is invalid.
     */
    public HospitalFactory() throws InvalidMapItemTypeNameException {
        super("hospital");
    }

    /**
     * Creates a new Hospital.
	 * @param parameters
     *		The parameters of the constructor.
     * @return The new Hospital.
     */
    @Override
    public Hospital createMapItem(Object[] parameters){
		if (parameters.length != 2) {
			throw new InvalidParameterException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			try {
				//TODO hieronder nog zinvolle errors geven
				return new Hospital((String) parameters[0], (GPSCoordinate) parameters[1]);
			} catch (InvalidMapItemNameException ex) {
				Logger.getLogger(HospitalFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidLocationException ex) {
				Logger.getLogger(HospitalFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
			return null;
		}
    }
}
