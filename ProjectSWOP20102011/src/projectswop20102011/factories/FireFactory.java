package projectswop20102011.factories;

import java.security.InvalidParameterException;
import projectswop20102011.domain.Emergency;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;

/**
 * A class that represents an FireFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireFactory extends EmergencyFactory{

    /**
     * Creates a new FireFactory.
     *
     * @throws InvalidEmergencyTypeNameException
     *      If the type name of the new FireFactory is invalid.
     */
    public FireFactory() throws InvalidEmergencyTypeNameException{
        super("fire");
    }

    /**
     * Creates a new Ambulance.
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
