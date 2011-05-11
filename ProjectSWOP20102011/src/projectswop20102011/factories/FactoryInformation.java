package projectswop20102011.factories;

import java.util.Map;
import projectswop20102011.domain.lists.ParserList;
import projectswop20102011.exceptions.InvalidParametersException;
import projectswop20102011.exceptions.ParsingException;

/**
 * A class that contains information about how the parameters a Factory expects.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @invar The list of parameters is always valid.
 *          |areValidParameters(getParameters())
 */
public class FactoryInformation {

    private final FactoryInformationParameter[] parameters;

    /**
     * Creates a new FactoryInformation object with a given list of parameters of the factory.
     * @param parameters The parameters of this GenericFactory it describes.
     * @throws InvalidParametersException If the given list of parameters is invalid.
     */
    public FactoryInformation (FactoryInformationParameter... parameters) throws InvalidParametersException {
        if(!areValidParameters(parameters)) {
            throw new InvalidParametersException("The list of parameters must be effective and it's elements.");
        }
        this.parameters = parameters;
    }

    /**
     * Gets the parameters of this FactoryInformation.
     * @return the parameters of this FactoryInformation.
     */
    public FactoryInformationParameter[] getParameters () {
		//TODO: mag deze (volgens netbeans) redundante cast hier weg?
		//Ja want bij arrays is a.clone().getClass() gelijk aan a.getClass()(
		return  this.parameters.clone();
    }

    /**
     * Checks if the given list of parameters is valid.
     * @param parameters The list of parameters to check.
     * @return True if the list is valid and all its objects, otherwise false.
     */
    public static boolean areValidParameters (FactoryInformationParameter[] parameters) {
        if(parameters != null) {
            for(FactoryInformationParameter efip : parameters) {
                if(efip == null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    /**
     * Checks if the Factory it describes can be called with the given set of parameters.
     * @param objects The instances of parameters to check.
     * @return True if the length of the given parameters is equal to the number of parameters and every parameter is a valid instance, otherwise false.
     */
    public boolean areValidParameterInstances (Object... objects) {
        FactoryInformationParameter[] parameters = this.getParameters();
        if(objects.length == parameters.length) {
            for(int i = 0; i < parameters.length; i++) {
                if(!parameters[i].canBeAParameter(objects[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Generates parameters instances based on a list of parser (to parse fundamental objects) and a Map that maps parameters to their textual values.
     * @param parserList A list of parsers that are available for parsing.
     * @param parameters A map that contains the parameters and their textual values.
     * @return A list of objects representing the parameters.
     * @throws ParsingException If the parameters can't be parsed.
     */
    public Object[] generateParametersFromMap (ParserList parserList, Map<String,String> parameters) throws ParsingException {
        FactoryInformationParameter[] fips = this.getParameters();
        Object[] parameterInstances = new Object[fips.length];
        for(int i = 0; i < fips.length; i++) {
            FactoryInformationParameter fip = fips[i];
            String value = parameters.get(fip.getName());
            if(value != null) {
                parameterInstances[i] = fip.parseParameter(value, parserList);
            }
            else {
                throw new ParsingException(String.format("Parameter \"%s\" not found!",fip.getName()));
            }
        }
        return parameterInstances;
    }

}