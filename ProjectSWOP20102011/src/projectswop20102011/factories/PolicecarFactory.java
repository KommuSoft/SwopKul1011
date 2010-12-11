package projectswop20102011.factories;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Policecar;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents a PolicecarFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class PolicecarFactory extends MapItemFactory {

    /**
     * Creates a new PolicecarFactory.
     *
     * @throws InvalidMapItemTypeNameException
     *		If the type name of the new PolicecarFactory is invalid.
     */
    public PolicecarFactory() throws InvalidMapItemTypeNameException {
        super("policecar");
    }

    /**
     * Creates a new Policecar.
     *
     * @param s
     *      A string with specifications of the new policecar.
     * @return The new Policecar.
     */
    @Override
    public Policecar createMapItem(String s){
        String name = parseName(s);
        GPSCoordinate homeLocation = parseHomeLocation(s);
        Long speed = parseSpeed(s);
        try {
            return new Policecar(name, homeLocation, speed);
        } catch (InvalidLocationException ex) {
            Logger.getLogger(PolicecarFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidMapItemNameException ex) {
            Logger.getLogger(PolicecarFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidSpeedException ex) {
            Logger.getLogger(PolicecarFactory.class.getName()).log(Level.SEVERE, null, ex);
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
