package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.validators.EmergencyComparator;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.utils.GetterMapFunction;
import projectswop20102011.utils.MapFunction;

/**
 * A class representing a disaster (a group of emergencies).
 * @invar A disaster contains always a valid number of emergencies (i.e. higher than one or just one) and all emergencies are valid (i.e. they are not part of another disaster).
 *		| areValidEmergencies(getEmergencies()) == true
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Disaster extends Sendable {

    /**
     * The emergencies of this Disaster.
     */
    private final List<Emergency> emergencies;
    /**
     * The units that are needed for this Disaster.
     */
    private final DerivedUnitsNeeded unitsNeeded;

    /**
     * Creates a disaster with the given emergencies and description.
     * @param emergencies
     *		The emergencies of the Disaster.
     * @param description
     *		The description of the Disaster.
     * @effect The new disaster is a sendable with the given description.
     *		| super(description)
     * @effect The emergencies are notified that they are part of the disaster.
     *		| indicateEmergenciesAsPartOfDisaster(emergencies)
     * @post The emergencies of this disaster are set to the given emergencies.
     *		| this.emergencies = new.emergencies
     * @post The units needed is initialized.
     *		| unitsNeeded = new DerivedUnitsNeeded(this)
     * @throws InvalidEmergencyException
     *		If the given emergencies are invalid.
     * @throws InvalidConstraintListException
     *		If the constraints for this disaster are invalid.
     */
    public Disaster(List<Emergency> emergencies, String description) throws InvalidEmergencyException, InvalidConstraintListException {
        super(description);
        if (!areValidEmergencies(emergencies)) {
            throw new InvalidEmergencyException("The number of emergencies must be higher than one, and all the emergency must be no part of an disaster.");
        }
        indicateEmergenciesAsPartOfDisaster(emergencies);
        this.emergencies = emergencies;
        try {
            unitsNeeded = new DerivedUnitsNeeded(this);
        } catch (InvalidSendableException ex) {
            //We assume this can never happen.
            Logger.getLogger(Disaster.class.getName()).log(Level.SEVERE, null, ex);
            throw new InvalidEmergencyException("The disaster is invalid.");
        }
    }

    /**
     * Indicate that a list of emergencies are part of a disaster
     * @param emergencies
     *		The emergencies to indicate that they are part of a disaster.
     * @effect Every emergency knows that it is part of a disaster.
     *		| for(every Emergency e in disaster)
     *		|	e.setDisaster(this)
     * @throws InvalidEmergencyException
     *		If one of the emergencies is already part of the disaster.
     */
    private void indicateEmergenciesAsPartOfDisaster(List<Emergency> emergencies) throws InvalidEmergencyException {
        for (Emergency e : emergencies) {
            e.setDisaster(this);
        }
    }

    /**
     * Checks if the given emergencies are valid emergencies for a Disaster.
     * @param emergencies
     *		The emergencies to check if they are valid for a Disaster.
     * @return True if the given list contains at least one emergency and the emergencies are not yet part of a disaster and currently unhandled.
     */
    public static boolean areValidEmergencies(List<Emergency> emergencies) {
        if (emergencies.isEmpty()) {
            return false;
        }
        for (Emergency e : emergencies) {
            if (e.isPartOfADisaster() || e.getStatus() != SendableStatus.RECORDED_BUT_UNHANDLED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the emergencies of this Disaster.
     * @return The emergencies of this Disaster.
     */
    public List<Emergency> getEmergencies() {
        return emergencies;
    }

    /**
     * Returns the location of this Disaster.
     * @return The location of this Disaster.
     * @note The location of the Disaster is the average of its emergencies.
     */
    @Override
    public GPSCoordinate getLocation() {
        try {
            return GPSCoordinate.calculateAverage(MapFunction.mapCollection(this.getEmergencies(), new GetterMapFunction<Emergency, GPSCoordinate>(Emergency.class, GPSCoordinate.class, Emergency.class.getMethod("getLocation"))));
        } catch (NoSuchMethodException ex) {
            //We assume this can't happen
            Logger.getLogger(Disaster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            //We assume this can't happen
            Logger.getLogger(Disaster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            //We assume this can't happen
            Logger.getLogger(Disaster.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Returns the severity level of the Disaster.
     * @return The severity level of the Disaster.
     * @note The severity level of the Disaster is the maximum severity level of its emergencies.
     */
    @Override
    public SendableSeverity getSeverity() {
        SendableSeverity max = this.getEmergencies().get(0).getSeverity();
        for (Emergency e : getEmergencies()) {
            max = max.getMaximum(e.getSeverity());
        }
        return max;
    }

    /**
     * Returns the status of this Disaster.
     * @return The status of this Disaster.
     * @note The status of a Disaster is record_but_unhandled if all the emergencies are recorded_but_unhandled;
     *		completed if all the emergencies are completed; otherwise it is response_in_progress.
     */
    @Override
    public SendableStatus getStatus() {
        SendableStatus status = this.getEmergencies().get(0).getStatus();
        for (int i = 1; i < this.getEmergencies().size(); i++) {
            status = status.combine(this.getEmergencies().get(i).getStatus());
        }
        return status;
    }

    /**
     * Returns a hashtable that contains the extended information of this disaster.
     * This hashtable contains long information of every Emergency.
     * @return A hashtable that contains the extended information of this disaster.
     */
    @Override
    public Hashtable<String, String> getLongInformation() {
        Hashtable<String, String> information = super.getInformation();

        for (Emergency e : getEmergencies()) {
            information.putAll(e.getLongInformation());
        }

        return information;
    }

    /**
     * Assigning units to this emergency.
     * @param units
     *      A list of units to assign.
     * @param eventHandler 
     *		The eventHandler where the notifications should be sent to.
     * @effect The units are assigned to the disaster.
     *		| getStatus().assignUnits(getUnitsNeeded(), units, eventHandler)
     * @throws InvalidSendableStatusException
     *      If the status of this emergency does not allow this action.
     * @throws  InvalidEmergencyException
     *		If the emergency is invalid.
     */
    @Override
    public void assignUnits(Set<Unit> units, EventHandler eventHandler) throws InvalidSendableStatusException, InvalidEmergencyException {
        this.getStatus().assignUnits(this.getUnitsNeeded(), units, eventHandler);
    }

    /**
     * Returns a ConcreteUnitsNeeded structure that contains the units needed for this Disaster.
     * @return A ConcreteUnitsNeeded structure that contains the units needed for this Disaster.
     * @note Handling dispatching and updating the status of the Disaster is also done by this object.
     * @note The visibility of this method is package. No classes outside the domain have access to the DerivedUnitsNeeded object.
     */
    @Override
    DerivedUnitsNeeded getUnitsNeeded() {
        return unitsNeeded;
    }

    /**
     * Returns a proposal generated by the policy of this constraint for the emergencies with severity urgent and serious.
     * @param availableUnits
     *      A list of units that are available for the proposal.
     * @return A list of units proposed by the policy of this constraint.
     */
    @Override
    public HashSet<Unit> getPolicyProposal(Set<Unit> availableUnits) {
        HashSet<Unit> au = new HashSet<Unit>((ArrayList<Unit>) ((new ArrayList<Unit>(availableUnits)).clone()));
        HashSet<Unit> units = new HashSet<Unit>();
        for (int i = 0; i < getEmergencies().size(); ++i) {
            if (getEmergencies().get(i).getSeverity().ordinal() > SendableSeverity.NORMAL.ordinal()) {
                units.addAll(getEmergencies().get(i).getPolicyProposal(au));
                au.removeAll(units);
            }
        }
        return units;
    }

    /**
     * Returns a proposal generated by the policy of this constraint.
     * @param availableUnits
     *      A list of units that are available for the proposal.
     * @return A list of units proposed by the policy of this constraint.
     */
    public Set<Unit> getPolicyProposalAllSeverities(Set<Unit> availableUnits) {
        Set<Unit> units = new HashSet<Unit>();
        for (int i = 0; i < getEmergencies().size(); ++i) {
            units.addAll(getEmergencies().get(i).getPolicyProposal(availableUnits));
            availableUnits.removeAll(units);
        }

        return units;
    }

    /**
     * Enable a unit to finish his job for this Disaster.
     * @param unitToFinish
     *      The unit that wants to finish this Disaster.
     * @param  eventHandler
     *		The eventHandler where the notifications should be sent to.
     * @effect The unit finishes its job in this disaster
     *		| getStatus().finishUnit(unitToFinish.getEmergency().getUnitsNeeded(), unitToFinish, eventHandler)
     * @throws InvalidSendableStatusException
     *      If the status of this Disaster does not allow this action.
     * @note This method has a package visibility: Units need to finish on their own and call this method to register this to the Disaster.
     */
    @Override
    void finishUnit(Unit unitToFinish, EventHandler eventHandler) throws InvalidSendableStatusException {
        this.getStatus().finishUnit(unitToFinish.getEmergency().getUnitsNeeded(), unitToFinish, eventHandler);
    }

    /**
     * This method does operations after the unit is finished.
     * @param unit 
     *		The unit that will finish.
     * @param eventHandler
     *		A handler to handle the events generated by this action.
     * @effect If the unit is needed for an emergency in the disaster it will be assigned to it
     *		| for(every Emergency e in Disaster)
     *		|	if(e is needed for e)
     *		|		e.assignUnits(unit, eventHandler)
     *		|		stop
     * @throws InvalidSendableStatusException 
     *		If the sendable is in the wrong state.
     * @throws InvalidEmergencyException
     *		If the emergency is invalid.
     */
    @Override
    protected void afterFinish(Unit unit, EventHandler eventHandler) throws InvalidSendableStatusException, InvalidEmergencyException {
        List<Emergency> emergencies = getEmergencies();
        Collections.sort(emergencies, new EmergencyComparator());
        Collections.reverse(emergencies);

        Set<Unit> u = new HashSet<Unit>(1);
        u.add(unit);

        for (Emergency e : emergencies) {
            Set<Unit> proposal = e.getPolicyProposal(u);
            if (proposal.size() > 0) {
                e.assignUnits(proposal, eventHandler);
                return;
            }
        }
    }

    /**
     * Checks if this disaster can be resolved with a given collection of all available units.
     * @param availableUnits
     *		All the available units in the world.
     * @return True if the given disaster can be resolved, otherwise false.
     */
    @Override
    public boolean canBeResolved(Set<Unit> availableUnits) {
        for (Emergency e : getEmergencies()) {
            if (!e.canBeResolved(availableUnits)) {
                return false;
            }
        }
        return true;
    }
}
