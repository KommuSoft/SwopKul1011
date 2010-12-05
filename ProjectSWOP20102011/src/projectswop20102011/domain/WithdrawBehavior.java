package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidWithdrawalException;

/**
 * An interface that represents the withdraw behavior of a unit.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface WithdrawBehavior {

    /**
     * Withdraw this unit from the emergency he is currently assigned to
     *
     * @param u
     *      The unit to be withdrawn
     * @throws InvalidWithdrawalException
     *			If the unit is already at site of the emergency
     * @throws InvalidEmergencyException
     *			If the unit isn't assigned to an emergency
     */
    public void withdraw(Unit u) throws InvalidWithdrawalException, InvalidEmergencyException;
}
