package projectswop20102011.factories;

import java.security.InvalidParameterException;
import projectswop20102011.domain.Emergency;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;

/**
 * A class that represents a TrafficAccidentFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TrafficAccidentFactory extends EmergencyFactory{

    /**
     * Creates a new TrafficAccidentFactory.
     *
     * @throws InvalidEmergencyTypeNameException
     *      If the type name of the new TrafficAccident is invalid.
     */
    public TrafficAccidentFactory() throws InvalidEmergencyTypeNameException{
        super("traffic accident");
    }

    /**
     * Creates a new TrafficAccident.
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
