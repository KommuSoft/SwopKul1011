package projectswop20102011.factories;

import java.security.InvalidParameterException;
import projectswop20102011.domain.Emergency;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;

/**
 * A class that represents an PublicDisturbanceFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class PublicDisturbanceFactory extends EmergencyFactory{

    /**
     * Creates a new PublicDisturbance.
     *
     * @throws InvalidEmergencyTypeNameException
     *      If the type name of the new PublicDisturbance is invalid.
     */
    public PublicDisturbanceFactory() throws InvalidEmergencyTypeNameException{
        super("public disturbance");
    }

    /**
     * Creates a new PublicDisturbance.
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
