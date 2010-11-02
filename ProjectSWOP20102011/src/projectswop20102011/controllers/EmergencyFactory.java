package projectswop20102011.controllers;

import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;

/**
 * An abstract factory class, used to create new emergencies.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class EmergencyFactory {

    /**
     * The name of the type of the emergency the factory will produce ("fire", "robbery",...)
     */
    private final String typeName;


    /**
     * Creates a new instance of an EmergencyFactory with a given type name
     * @param typeName The type name of the EmergencyFactor (example: "fire", "robbery",...)
     * @throws InvalidEmergencyTypeNameException If the given typeName is invalid.
     */
    protected EmergencyFactory (String typeName) throws InvalidEmergencyTypeNameException {
        if(!isValidTypeName(typeName)) {
            throw new InvalidEmergencyTypeNameException(String.format("\"%s\" is not a valid emergency type name.",typeName));
        }
        this.typeName = typeName;
    }

    /**
     * Tests if the given name is a valid type name for an EmergencyFactory.
     * @param name The given name to test.
     * @return True if the given name is effective and not empty, otherwise false.
     */
    public static boolean isValidTypeName (String name) {
        return (name != null && name.length() > 0);
    }

}
