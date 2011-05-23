package projectswop20102011.domain;

/**
 * A tuple of an emergency and u unit. A utility structure used for event handling.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EmergencyUnitTuple {
    
    /**
     * The Emergency of the tuple
     */
    private final Emergency emergency;
    /**
     * The Unit of the tuple
     */
    private final Unit unit;
    
    /**
     * Creates a new EmergencyUnitTuple with the specified Emergency and Unit.
     * @param emergency
     *          The emergency of the tuple.
     * @param unit 
     *          The unit of the tuple.
     */
    public EmergencyUnitTuple (Emergency emergency, Unit unit) {
        this.emergency = emergency;
        this.unit = unit;
    }
    
    /**
     * Returns the Emergency of this tuple.
     * @return the Emergency of this tuple.
     */
    public Emergency getEmergency () {
        return this.emergency;
    }
    /**
     * Returns the Unit of this tuple.
     * @return The Unit of this tuple.
     */
    public Unit getUnit () {
        return this.unit;
    }
    
}
