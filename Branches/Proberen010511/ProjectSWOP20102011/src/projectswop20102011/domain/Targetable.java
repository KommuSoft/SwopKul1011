package projectswop20102011.domain;

import projectswop20102011.domain.GPSCoordinate;

/**
 * An interface inherited by all the classes that can be a target.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface Targetable {

    public GPSCoordinate getTargetLocation ();

}
