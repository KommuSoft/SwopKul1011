package projectswop20102011.domain;

/**
 * An interface inherited by all the classes that can be a target.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface Targetable {

    /**
     * Returns the location of the target.
     * @return A GPSCoordinate that represents the location of the target.
     */
    public GPSCoordinate getTargetLocation ();

}
