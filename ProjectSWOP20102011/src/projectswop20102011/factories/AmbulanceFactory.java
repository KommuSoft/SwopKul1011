package projectswop20102011.factories;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.MapItem;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents an AmbulanceFactory.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AmbulanceFactory extends MapItemFactory {

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
     * @param parameters
     *		The parameters to construct the Ambulance.
     * @return The new Ambulance.
     * @throws InvalidParameterException
     *		If the parameters are invalid for this Ambulance.
     */
    public Ambulance createMapItem(String s) throws InvalidParameterException {

        String name = parseName(s);
        GPSCoordinate homeLocation = parseHomeLocation(s);
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

    private String parseName(String s) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private GPSCoordinate parseHomeLocation(String s) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Long parseSpeed(String s) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

