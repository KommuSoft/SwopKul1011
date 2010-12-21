package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidWithdrawalException;

/**
 * A class that represents the not-withdraw behavior of a unit.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NotWithdraw implements WithdrawBehavior {

	/**
	 * Throw an error when this method is called because the given unit can't be withdrawn.
	 * @param unit
	 *		The unit that must be withdrawn.
	 * @throws InvalidWithdrawalException
	 *		Always thrown because the given unit can't be withdrawn.
	 */
    @Override
    public void withdraw(Unit unit) throws InvalidWithdrawalException {
        throw new InvalidWithdrawalException("This unit can't be withdrawn");
    }
}
