package projectswop20102011.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.Emergency;
import projectswop20102011.exceptions.InvalidClassParameterException;

/**
 * A class that represents parameters of the Emergency class, this class is used for search queries by the dispatcher
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EmergencyParameter extends ClassParameter {

    public EmergencyParameter(String parameterName) throws InvalidClassParameterException {
        super(Emergency.class,parameterName);
    }

    /**
     * Retrieves the value of the parameter of a specific Emergency.
     * @param emergency the emergency to retrieve the value of the parameter from.
     * @return the value of the parameter of a specific Emergency.
     */
    public Object getParameterValueFromEmergency(Emergency emergency) throws Throwable {
        try {
            return this.getGetterMethod().invoke(emergency);
        } catch (IllegalAccessException ex) {
            //can't be true, is checked by the super class, getter must be public
            Logger.getLogger(EmergencyParameter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IllegalArgumentException ex) {
            //cant be true, is checked by the super class, getter can't have any arguments.
            Logger.getLogger(EmergencyParameter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (InvocationTargetException ex) {
            throw ex.getCause();
        }
    }
    
}
