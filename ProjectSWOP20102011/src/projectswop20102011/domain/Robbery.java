package projectswop20102011.domain;

import projectswop20102011.domain.validators.TypeUnitValidator;
import java.io.InvalidClassException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.validators.DifferentQuadraticValidator;
import projectswop20102011.domain.validators.NumberDispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidDispatchUnitsConstraintException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a robbery.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class Robbery extends Emergency {

    /**
     * Indicates if this robbery is an armed robbery.
     */
    private boolean armed;
    /**
     * Indicates if this robbery is still in progress.
     */
    private boolean inProgress;

    /**
     * Makes a new robbery emergency with the given parameters.
     * @param location
     *		The location of this robbery emergency.
     * @param severity
     *		The severity of this robbery emergency.
     * @param description
     *		The description of this robbery emergency.
     * @param armed
     *		An indicator that indicates if this robbery is an armed robbery.
     * @param inProgress
     *		An indicator that indicates if this robbery is still in progress.
     * @effect The new Robbery is a new emergency with the given location, severity and description.
     *		| super(location,severity, description)
	 * @effect The is armed parameter of the robery is equal to the given parameter.
     *		| setArmed(armed)
     * @effect The in progress parameter of this robbery is equal to the given parameter.
     *		| setInProgress(inProgress)
     * @throws InvalidLocationException
     *		If the given location is an invalid location for an emergency.
     * @throws InvalidSendableSeverityException
     *		If the given severity is an invalid severity for an emergency.
     */
    public Robbery(GPSCoordinate location, SendableSeverity severity, String description,
            boolean armed, boolean inProgress) throws InvalidLocationException, InvalidSendableSeverityException {
        super(location, severity, description);
        setArmed(armed);
        setInProgress(inProgress);
    }

    /**
     * Indicates whether this robbery is an armed robbery.
     * @return True if this robbery is an armed robbery; false otherwise.
     */
    public boolean isArmed() {
        return armed;
    }

    /**
     * Sets the armed indicator to the given value.
     * @param armed
     *		The new value of the armed indicator.
     * @post The is armed parameter of this robbery is equal to the given parameter.
     *		| armed.equals(isArmed())
     */
    private void setArmed(boolean armed) {
        this.armed = armed;
    }

    /**
     * Indicates whether this robbery is still in progress.
     * @return True if this robbery is still in progress; false otherwise.
     */
    public boolean isInProgress() {
        return inProgress;
    }

    /**
     * Sets the in progress indicator to the given value.
     * @param inProgress
     *		The new value of the progress indicator.
     * @post The in progress parameter of this robbery is equal to the given parameter.
     *		| inProgress.equals(isInProgress())
     */
    private void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    /**
     * Returns a hashtable that represents all the information of this robbery.
     * This hashtable contains if the robbery is armed an if the robbery is in progress.
     * @return A hashtable that represents all the information of this robbery.
     */
    @Override
    public Hashtable<String, String> getLongInformation() {
        Hashtable<String, String> information = getInformation();

        information.put("armed", "" + isArmed());
        information.put("in progress", "" + isInProgress());

        return information;
    }

    /**
     * Calculates the units needed for this robbery.
     * @return The units needed for this robbery.
     */
    @Override
    protected ConcreteUnitsNeeded calculateUnitsNeeded() {
        int nPolice = ((isArmed() && isInProgress()) ? 3 : 1); //TODO: zijn dit geen magic numbers?
        try {
            ConcreteUnitsNeeded un = new ConcreteUnitsNeeded(this, new NumberDispatchUnitsConstraint(new TypeUnitValidator(Policecar.class), nPolice,new DifferentQuadraticValidator<Unit,Unit>()));
            un.pushPolicy(new ASAPDispatchPolicy(un));
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
        } catch (InvalidValidatorException ex) {
            //we assume this can't happen
            Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidClassException ex) {
            //we assume this can't happen
            Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidUnitsNeededException ex) {
            //we asume this can't happen
            Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDispatchPolicyException ex) {
            //we asume this can't happen
            Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Should never be returned
        return null;
    }
}
