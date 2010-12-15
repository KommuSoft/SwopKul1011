package projectswop20102011.factories;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Hospital;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;
import projectswop20102011.exceptions.ParsingException;
import projectswop20102011.utils.TextScanner;
import projectswop20102011.utils.parsers.GPSCoordinateParser;

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
     * @param s
     *		A string with the specifications of the new hospital.
     * @return The new Hospital.
     */
    @Override
    public Hospital createMapItem(String s){
        String name = parseName(s);
        GPSCoordinate homeLocation = null;
		try {
			homeLocation = parseHomeLocation(s);
		} catch (ParsingException ex) {
			Logger.getLogger(HospitalFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
        try {
            return new Hospital(name, homeLocation);
        } catch (InvalidMapItemNameException ex) {
            Logger.getLogger(HospitalFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidLocationException ex) {
            Logger.getLogger(HospitalFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
