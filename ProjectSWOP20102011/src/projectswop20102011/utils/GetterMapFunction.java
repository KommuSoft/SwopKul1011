package projectswop20102011.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A mapper class that maps an object to the object returned by one of its getters
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class GetterMapFunction<TSource, TDestination> extends MapFunction<TSource, TDestination> {

    private final Method getterMethod;

    /**
     * Creates a new instance of the GetterMapFunction class, with a given sourceClass, destinationClass and getterMethod.
     * @param sourceClass The class from where the objects will be called.
     * @param destinationClass The class of the returned results of the method.
     * @param getterMethod The getterMethod that will be called.
     */
    public GetterMapFunction(Class<TSource> sourceClass, Class<TDestination> destinationClass, Method getterMethod) {
        if (isValidGetterMethod(sourceClass, destinationClass, getterMethod)) {
            this.getterMethod = getterMethod;
        } else {
            throw new IllegalArgumentException("Invalid getter method.");
        }
    }

    /**
     * Returns the getter method that will be called.
     * @return the getter method that will be called.
     */
    public Method getGetterMethod() {
        return this.getterMethod;
    }

    /**
     * Checks if the given getterMethod is a valid method.
     * @param <TSource> The source type (the class where the getter method belongs).
     * @param <TDestination> The destination type (the return type of the getter, or a superclass of it).
     * @param sourceClass The source class (equivalent to <TSource>).
     * @param destinationClass The destination class (equivalent to <TSource>).
     * @param getterMethod The getterMethod to check for.
     * @return True if all the parameters are effective, the getterMethod is accessible, there are no parameters in the getterMethod, The method is part of the sourceClass and the return type is a subtype of or the destinationClass, otherwise false.
     */
    public static <TSource, TDestination> boolean isValidGetterMethod(Class<TSource> sourceClass, Class<TDestination> destinationClass, Method getterMethod) {
        return (sourceClass != null && destinationClass != null && getterMethod != null && getterMethod.getParameterTypes().length == 0 && getterMethod.getDeclaringClass().isAssignableFrom(sourceClass) && destinationClass.isAssignableFrom(getterMethod.getReturnType()));
    }

    /**
     * Invokes the getter to map the source object to the object returned by the getter.
     * @param source The source object to invoke the getter from.
     * @return The object returned by the getter.
     * @throws Throwable If the getter throws a Throwable.
     */
    @Override
    public TDestination getFunctionResult(TSource source) throws Throwable {
        try {
            return (TDestination) getterMethod.invoke(source);
        } catch (IllegalAccessException ex) {
            //We assume this can't happen
            Logger.getLogger(GetterMapFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            //We assume this can't happen
            Logger.getLogger(GetterMapFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            throw ex.getCause();
        }
        return null;
    }
}
