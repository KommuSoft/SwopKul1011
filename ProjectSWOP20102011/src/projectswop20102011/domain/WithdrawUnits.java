package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidWithdrawalException;

/**
 * A class that represents the units to be withdrawn.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class WithdrawUnits {

    /**
     * A variable representing the mapitemlist with int. al. units than can be withdrawn.
     */
    private MapItemList mapItemList;

    /**
     * Creates a new object that withdraws the units from an emergency.
     *
     * @param mapItemList
     *      The units int. al. that can be withdrawn
     */
    public WithdrawUnits(MapItemList mapItemList) {
        setMapItemList(mapItemList);

    }

    /**
     * Returns the mapitemlist with int. al. units than can be withdrawn.
     * @return The mapitemlsit with int. al. units than can be withdrawn.
     */
    private MapItemList getMapItemList() {
        return mapItemList;
    }

    /**
     * Sets the mapitemlist with int. al. units that can be withdrawn.
     *
     * @param mapItemList
     *      The units int. al. that can be withdrawn
     */
    private void setMapItemList(MapItemList mapItemList) {
        this.mapItemList = mapItemList;
    }

    /**
     * Withdraw units from an emergency.
     *
     * @param names
     *      The names of the units to be withdrawn
     * @throws InvalidWithdrawalException
     *			If the unit is already at site of the emergency
     * @throws InvalidEmergencyException
     *			If the unit isn't assigned to an emergency
     */
    public void withdraw(String[] names) throws InvalidWithdrawalException, InvalidEmergencyException {
        for (String name : names) {
            ((Unit) getMapItemList().getMapItemFromName(name)).withdraw();
        }
    }
}
