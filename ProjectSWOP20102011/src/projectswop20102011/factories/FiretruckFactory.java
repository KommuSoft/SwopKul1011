package projectswop20102011.factories;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents a FiretruckFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FiretruckFactory extends MapItemFactory {

    /**
     *
     * @throws InvalidMapItemTypeNameException
     */
    public FiretruckFactory() throws InvalidMapItemTypeNameException {
        super("firetruck");
    }

    /**
     * Creates a new Firetruck.
     * @param s
     *      A string with the specifications of the new firetruck.
     * @return The new Firetruck.
     */
    @Override
    public Firetruck createMapItem(String s){
        String name = parseName(s);
        GPSCoordinate homeLocation = parseHomeLocation(s);
        Long speed = parseSpeed(s);
        try {
            return new Firetruck(name, homeLocation, speed);
        } catch (InvalidLocationException ex) {
            Logger.getLogger(FiretruckFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidMapItemNameException ex) {
            Logger.getLogger(FiretruckFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidSpeedException ex) {
            Logger.getLogger(FiretruckFactory.class.getName()).log(Level.SEVERE, null, ex);
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
