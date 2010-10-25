package projectswop20102011.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import projectswop20102011.exceptions.InvalidClassParameterException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ClassParameter {

    private final Class parameterClass;
    private Method getterMethod;

    public ClassParameter (Class parameterClass, String parameterName) throws InvalidClassParameterException {
         this.getterMethod = getGetterFromParameterName(parameterClass,parameterName);
         if(this.getterMethod == null) {
             throw new InvalidClassParameterException(String.format("Can't find parameter %s in class %s.",parameterName,parameterClass));
         }
         this.parameterClass = this.getterMethod.getReturnType();
    }

    /**
     * Searches the specific parameterClass for a method with a ReferenceableParameterGetter annotation with the specific parameterName
     * @param parameterClass the class where to search.
     * @param parameterName the name of the parameter to be found.
     * @return thet getterMethod if the ReferenceableParameterGetter is found, otherwise a null pointer
     */
    private Method getGetterFromParameterName(Class parameterClass, String parameterName) {
        for (Method m : parameterClass.getMethods()) {
            if (m.getParameterTypes().length == 0 && m.getReturnType() != void.class) {
                for (Annotation a : m.getAnnotations()) {
                    if (a instanceof ReferenceableParameterGetter) {
                        ReferenceableParameterGetter rpg = (ReferenceableParameterGetter) a;
                        if (rpg.name().equals(parameterName)) {
                            return m;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns the getter method, this method can be invoked by subclasses.
     * @return the getter method.
     */
    protected Method getGetterMethod() {
        return getterMethod;
    }

}
