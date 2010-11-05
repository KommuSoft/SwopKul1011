package projectswop20102011.factories;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.Ambulance;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;
import projectswop20102011.exceptions.InvalidUnitBuildingTypeNameException;

/**
 * A class that represents an AmbulanceFactory.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AmbulanceFactory extends UnitBuildingFactory {

    /**
     * Creates a new AmbulanceFactory.
     *
     * @throws InvalidUnitBuildingTypeNameException
     *		If the type name of the new AmbulanceFactory is invalid.
     */
    public AmbulanceFactory() throws InvalidUnitBuildingTypeNameException {
        super("ambulance");
    }

    /**
     * Creates a new Ambulance.
     *
     * @param parameters
     *		The parameters to construct the Ambulance.
     * @return The new Ambulance.
     * @throws InvalidParameterException
     *		If the parameters are invalid for this Ambulance.
     */
    @Override
    public Ambulance createUnitBuilding(Object[] parameters) throws InvalidParameterException {
        //TODO: reimplement
        /*if (areValidParameters(parameters)) {
            String name = (String) parameters[0];
            GPSCoordinate homeLocation = (GPSCoordinate) parameters[1];
            Long speed = (Long) parameters[2];
            GPSCoordinate currentLocation = (GPSCoordinate) parameters[3];
            GPSCoordinate destination = (GPSCoordinate) parameters[4];
            Boolean assigned = (Boolean) parameters[5];
            try {
                return new Ambulance(name, homeLocation, speed, currentLocation, destination, assigned);
            } catch (InvalidLocationException ex) {
                Logger.getLogger(AmbulanceFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidUnitBuildingNameException ex) {
                Logger.getLogger(AmbulanceFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidSpeedException ex) {
                Logger.getLogger(AmbulanceFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new InvalidParameterException("Invalid parameters for this fire truck.");
        }*/
        return null;
    }

    /**
     * Returns true if the given parameters are valid parameters for an Ambulance.
     * @param parameters
     *		The desired parameters of the Ambulance.
     * @return
     *		True if the given parameters are valid for an Ambulance; false otherwise.
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
