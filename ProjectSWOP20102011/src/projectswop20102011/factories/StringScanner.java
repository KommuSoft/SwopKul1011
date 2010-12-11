package projectswop20102011.factories;

import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;

/**
 * A class that scans a string.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class StringScanner {

    public StringScanner(String s){
        
    }

    public abstract String readString();

    public abstract FireSize readFireSize();

    public abstract GPSCoordinate readGPSCoordinate();

    public abstract int readInteger();


}
