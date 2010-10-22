package projectswop20102011;
/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class World {

    private EmergencyList emergencyList;

    public World () {
        this.emergencyList = new EmergencyList();
    }

    public EmergencyList getEmergencyList () {
        return this.emergencyList;
    }

}