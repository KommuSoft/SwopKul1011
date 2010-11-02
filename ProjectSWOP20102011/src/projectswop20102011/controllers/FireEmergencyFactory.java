package projectswop20102011.controllers;

import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;

/**
 * An emergency factory for a fire.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireEmergencyFactory extends EmergencyFactory {

    public FireEmergencyFactory () throws InvalidEmergencyTypeNameException {
        super("fire");
    }

}
