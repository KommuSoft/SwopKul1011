package projectswop20102011.factories;

import java.security.InvalidParameterException;
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
     * @throws InvalidUnitBuildingTypeNameException
     *		If the type name of the new PolicecarFactory is invalid.
     */
    public PolicecarFactory() throws InvalidMapItemTypeNameException {
        super("policecar");
    }

    /**
     * Creates a new Policecar.
     *
     * @param parameters
     *		The parameters to construct the Policecar.
     * @return The new Policecar.
     * @throws InvalidParameterException
     *		If the parameters are invalid for this Policecar.
     */
    @Override
    public Policecar createUnitBuilding(Object[] parameters) throws InvalidParameterException {
        if (areValidParameters(parameters)) {
            String name = (String) parameters[0];
            GPSCoordinate homeLocation = (GPSCoordinate) parameters[1];
            Long speed = (Long) parameters[2];
            try {
                return new Policecar(name, homeLocation, speed);
            } catch (InvalidLocationException ex) {
                Logger.getLogger(PolicecarFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidMapItemNameException ex) {
                Logger.getLogger(PolicecarFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidSpeedException ex) {
                Logger.getLogger(PolicecarFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new InvalidParameterException("Invalid parameters for this fire truck.");
        }
        return null;
    }

    /**
     * Returns true if the given parameters are valid parameters for a Policecar.
     * @param parameters
     *		The desired parameters of the Policecar.
     * @return
     *		True if the given parameters are valid for a Policecar; false otherwise.
     */
    public boolean areValidParameters(Object[] parameters) {
        if (parameters.length != 6) {
            return false;
        }
        if (parameters[0].getClass() != String.class
                || parameters[1].getClass() != GPSCoordinate.class
                || parameters[2].getClass() != Long.class
                || parameters[3].getClass() != GPSCoordinate.class
                || parameters[4].getClass() != GPSCoordinate.class
                || parameters[5].getClass() != Boolean.class) {
            return false;
        }
        return true;
    }
}
