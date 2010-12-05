package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidWithdrawalException;

/**
 * A class that represents the units to be withdrawn
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class WithdrawUnits {

    private MapItemList mapItemList;

    public WithdrawUnits(MapItemList mapItemList){
        setMapItemList(mapItemList);

    }
    public void withdraw(String[] names) throws InvalidWithdrawalException, InvalidEmergencyException {
        for (String name : names) {
            ((Unit) getMapItemList().getMapItemFromName(name)).withdraw();
        }
    }

    private MapItemList getMapItemList() {
        return mapItemList;
    }

    private void setMapItemList(MapItemList mapItemList) {
        this.mapItemList = mapItemList;
    }
}
