package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidWithdrawalException;

/**
 * A class that represents the not-withdraw behavior of a unit.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NotWithdraw implements WithdrawBehavior {

    @Override
    public void withdraw(Unit u) throws InvalidWithdrawalException {
        throw new InvalidWithdrawalException("This unit can't be withdrawn");
    }
}
