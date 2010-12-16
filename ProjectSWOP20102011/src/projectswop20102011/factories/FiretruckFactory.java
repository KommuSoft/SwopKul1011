package projectswop20102011.factories;

import java.security.InvalidParameterException;
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
public class FiretruckFactory extends UnitFactory {

    /**
     * Creates a new FiretruckFactory.
     *
     * @throws InvalidMapItemTypeNameException
     *      If the type name of the new firetruck is invalid.
     */
    public FiretruckFactory() throws InvalidMapItemTypeNameException {
        super("firetruck");
    }

    /**
     * Creates a new Firetruck.
	 * @param parameters
     *		The parameters of the constructor.
     * @return The new Firetruck.
     */
    @Override
    public Firetruck createMapItem(Object[] parameters){
		if (parameters.length != 3) {
			throw new InvalidParameterException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			try {
				//TODO hieronder nog zinvolle errors geven
				return new Firetruck((String) parameters[0], (GPSCoordinate) parameters[1], (Long) parameters[2]);
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
