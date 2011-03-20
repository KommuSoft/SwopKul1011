package projectswop20102011.domain;

import java.io.InvalidClassException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.validators.AndDispatchUnitsConstraint;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.domain.validators.FiretruckFireSizeValidator;
import projectswop20102011.domain.validators.NumberDispatchUnitsConstraint;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidDispatchUnitsConstraintException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class FireUnitsNeededCalculator {

    /**
     * private constructor: this class is a static class
     */
    private FireUnitsNeededCalculator () {}

    /**
     * Calculates the units needed for a fire.
     * @param fire
     *		The fire where the units needed must be calculated for.
     * @return The units needed for a fire.
     */
    protected static UnitsNeeded calculateUnitsNeeded(Fire fire) {
        //TODO: de volledige berekening van de UnitsNeeded moet niet verhuizen naar de calculator. Waarom niet enkel de informatie afhankelijk van de size berekenen (switch-blok dus)
        long firetrucks = 0;
        long policecars = 0;

        switch (fire.getSize()) {
            case LOCAL:
                firetrucks = 1;
                break;
            case HOUSE:
                firetrucks = 2;
                policecars = 1;
                break;
            case FACILITY:
                firetrucks = 4;
                policecars = 3;
        }
        try {
            DispatchUnitsConstraint fir = new NumberDispatchUnitsConstraint(new FiretruckFireSizeValidator(fire.getSize()), firetrucks);
            DispatchUnitsConstraint amb = new NumberDispatchUnitsConstraint(new TypeUnitValidator(Ambulance.class), fire.getNumberOfInjured() + fire.getTrappedPeople());
            DispatchUnitsConstraint pol = new NumberDispatchUnitsConstraint(new TypeUnitValidator(Policecar.class), policecars);
            UnitsNeeded un = new UnitsNeeded(fire, new AndDispatchUnitsConstraint(fir, amb, pol));
            un.pushPolicy(new ASAPDispatchPolicy(un, new FireSizeDispatchPolicy(un)));
            return un;
        } catch (InvalidEmergencyException ex) {
            //we assume this can't happen
            Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDispatchUnitsConstraintException ex) {
            //we assume this can't happen
            Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberOutOfBoundsException ex) {
            //we assume this can't happen
            Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidUnitValidatorException ex) {
            //we assume this can't happen
            Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidClassException ex) {
            //we assume this can't happen
            Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidConstraintListException ex) {
            //we assume this can't happen
            Logger.getLogger(TrafficAccident.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDispatchPolicyException ex) {
            //We assume this can't happen
            Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidUnitsNeededException ex) {
            //We assume this can't happen
            Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
        }
        //should never be returned.
        return null;
    }
}
