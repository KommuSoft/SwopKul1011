package projectswop20102011;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.reflection.ReferenceableParameterGetter;

/**
 * A class that represents an emergency.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Emergency {

    private static long idDispatcher = 0;
    /**
     * A variable registering the id of this emergency
     */
    private final long id;
    /**
     * A variable registering the location of this emergency
     */
    private GPSCoordinate location;
    /**
     * A variable registering the severity of this emergency
     */
    private EmergencySeverity severity;
    /**
     * A variable registering the status of this emergency
     */
    private EmergencyStatus status;

    /**
     * Make a new emergency with the given location, severity and id and put this
     * new emergency in the given emergencylist if the id is valid.
     * @param emergencies
     *		The list of emergencies where this emergency must be put in.
     * @param location
     *		The location of this emergency.
     * @param severity
     *		The severity of this emergency.
     */
    public Emergency(EmergencyList emergencies, GPSCoordinate location, EmergencySeverity severity) {
        this.id = idDispatcher++;
        try {
            emergencies.addEmergency(this);
        } catch (InvalidEmergencyException ex) {
            //can't be thrown: Emergency can't be a member of the a collection before it has been created.
            Logger.getLogger(Emergency.class.getName()).log(Level.SEVERE, null, ex);
        }
        setLocation(location);
        setSeverity(severity);
        setStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED);
    }

    /**
     * Sets the location of this emergency.
     * @param location
     *		The location of this emergency.
     */
    private void setLocation(GPSCoordinate location) {
        this.location = location;
    }

    /**
     * Sets the severity of this emergency.
     * @param severity
     *		The severity of this emergency.
     */
    private void setSeverity(EmergencySeverity severity) {
        this.severity = severity;
    }

    /**
     * Sets the status of this emergency.
     * @param status
     *		The status of this emergency.
     */
    private void setStatus(EmergencyStatus status) {
        this.status = status;
    }

    /**
     * Returns the location of this emergency.
     * @return The location of this emergency.
     */
    @ReferenceableParameterGetter(name="location")
    public GPSCoordinate getLocation() {
        return location;
    }

    /**
     * Returns the severity of this emergency.
     * @return The severity of this emergency.
     */
    @ReferenceableParameterGetter(name="severity")
    public EmergencySeverity getSeverity() {
        return severity;
    }

    /**
     * Returns the status of this emergency.
     * @return The status of this emergency.
     */
    @ReferenceableParameterGetter(name="status")
    public EmergencyStatus getStatus() {
        return status;
    }

    /**
     * Returns the id of this emergency.
     * @return The is of this emergency.
     */
    @ReferenceableParameterGetter(name="id")
    public long getId() {
        return id;
    }
}
