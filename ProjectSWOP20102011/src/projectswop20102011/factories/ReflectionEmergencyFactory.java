 package projectswop20102011.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.Emergency;
import projectswop20102011.exceptions.InvalidEmergencyFactoryClassException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;

/**
 * An Emergency factory that will be initialized from a class reference.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ReflectionEmergencyFactory extends EmergencyFactory {

    /**
     * The constructor for a specific type of emergency.
     */
    private final Constructor emergencyConstructor;

    public ReflectionEmergencyFactory(String emergencyTypeName, Class emergencyClass) throws InvalidEmergencyFactoryClassException, InvalidEmergencyTypeNameException {
        super(emergencyTypeName);
        if (!isValidEmergencyFactoryClass(emergencyClass)) {
            throw new InvalidEmergencyFactoryClassException("The emergency factory class must be effective, a subclass of Emergency, public, not abstract and contains a public constructor.");
        }
        this.emergencyConstructor = emergencyClass.getConstructors()[0];
    }

    /**
     * Tests if the given class can be a class in the ReflectionEmergencyFactory.
     * @param emergencyClass The class to validate.
     * @return True if the Class is effective, is a subclass of Emergency, public, not abstract and contains a public constructor, otherwise false.
     */
    public static boolean isValidEmergencyFactoryClass(Class emergencyClass) {
        if (emergencyClass == null || !Emergency.class.isAssignableFrom(emergencyClass)) {
            return false;
        }
        int modifier = emergencyClass.getModifiers();
        if (Modifier.isAbstract(modifier) || !Modifier.isPublic(modifier)) {
            return false;
        }
        return (emergencyClass.getConstructors().length > 0);
    }

    /**
     * Creates a new special emergency from the given class in the constructor.
     * @param parameters The parameters that will be used to create the special Emergency.
     * @return A special emergency created by the constructor.
     * @throws InvalidParameterException If the number of parameters doesn't match or at least one of the parameters hasn't a compatible type.
     * @throws Exception If the constructor in the factory throws an exception.
     */
    @Override
    public Emergency createEmergency(Object[] parameters) throws InvalidParameterException, Exception {
        if (!this.areValidParameters(parameters)) {
            throw new InvalidParameterException("At least one of the parameters has an invalid type or the number of parameters doesn't match.");
        }
        try {
            return (Emergency) this.getEmergencyConstructor().newInstance(parameters);
        } catch (InstantiationException ex) {
            //Cannot happen
            Logger.getLogger(ReflectionEmergencyFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //Cannot happen
            Logger.getLogger(ReflectionEmergencyFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            //Cannot happen
            Logger.getLogger(ReflectionEmergencyFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
        return null;
    }

    @Override
    public Class[] getParameterClasses() {
        return this.getEmergencyConstructor().getParameterTypes();
    }

    /**
     * Returns the constructor of a special emergency used by this factory.
     * @return the constructor of a special emergency used by this factory.
     */
    private Constructor getEmergencyConstructor() {
        return emergencyConstructor;
    }
}
