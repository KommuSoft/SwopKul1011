package projectswop20102011.factories;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents an AmbulanceFactory.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AmbulanceFactory extends UnitFactory {

	/**
	 * Creates a new AmbulanceFactory.
	 *
	 * @throws InvalidMapItemTypeNameException
	 *		If the type name of the new AmbulanceFactory is invalid.
	 */
	public AmbulanceFactory() throws InvalidMapItemTypeNameException {
		super("ambulance");
	}

	/**
	 * Creates a new Ambulance.
	 * @param parameters
     *		The parameters of the constructor.
	 * @return The new Ambulance.
	 */
	@Override
	public Ambulance createMapItem(Object[] parameters) {
		if (parameters.length != 3) {
			throw new InvalidParameterException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			try {
				//TODO hieronder nog zinvolle errors geven
				return new Ambulance((String) parameters[0], (GPSCoordinate) parameters[1], (Long) parameters[2]);
			} catch (InvalidLocationException ex) {
				Logger.getLogger(PolicecarFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidMapItemNameException ex) {
				Logger.getLogger(PolicecarFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidSpeedException ex) {
				Logger.getLogger(PolicecarFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
			return null;
		}
	}
		

}
