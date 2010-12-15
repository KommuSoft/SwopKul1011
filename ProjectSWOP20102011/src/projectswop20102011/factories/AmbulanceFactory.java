package projectswop20102011.factories;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;
import projectswop20102011.exceptions.ParsingException;

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
	 *
	 * @param s
	 *      A string with the specifications of the new ambulance.
	 * @return The new Ambulance.
	 */
	@Override
	public Ambulance createMapItem(String s) {
		String name = parseName(s);
		GPSCoordinate homeLocation = null;
		try {
			homeLocation = parseHomeLocation(s);
		} catch (ParsingException ex) {
			Logger.getLogger(AmbulanceFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
		Long speed = parseSpeed(s);
		try {
			return new Ambulance(name, homeLocation, speed);
		} catch (InvalidLocationException ex) {
			Logger.getLogger(AmbulanceFactory.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidMapItemNameException ex) {
			Logger.getLogger(AmbulanceFactory.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidSpeedException ex) {
			Logger.getLogger(AmbulanceFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
