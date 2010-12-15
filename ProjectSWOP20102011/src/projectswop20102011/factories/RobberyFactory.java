package projectswop20102011.factories;

import java.security.InvalidParameterException;
import projectswop20102011.domain.Emergency;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;

/**
 * A class that represents an RobberyFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class RobberyFactory extends EmergencyFactory{

    /**
     * Creates a new RobberyFactory.
     *
     * @throws InvalidEmergencyTypeNameException
     *      If the type name of the new Robbery is invalid.
     */
    public RobberyFactory() throws InvalidEmergencyTypeNameException{
        super("robbery");
    }

    /**
     * Creates a new Robbery.
     * @param parameters
     * @return
     * @throws InvalidParameterException
     * @throws Exception
     */
    @Override
    public Emergency createEmergency(Object[] parameters) throws InvalidParameterException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @return
     */
    @Override
    public Class[] getParameterClasses() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}