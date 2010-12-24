package projectswop20102011.factories;

import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Hospital;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents a HospitalFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class HospitalFactory extends MapItemFactory {

	/**
	 * Creates a new HospitalFactory.
	 * @effect The new HospitalFactory is a new MapItemFactory with a given type name.
	 *		|super("hospital")
	 * @throws InvalidMapItemTypeNameException
	 *		If the type name of the new HospitalFactory is invalid.
	 */
	public HospitalFactory() throws InvalidMapItemTypeNameException {
		super("hospital");
	}

	/**
	 * Creates a new Hospital.
	 * @param parameters
	 *		The parameters of the constructor.
	 * @return The new Hospital.
	 * @throws InvalidMapItemNameException
	 *		If the given name is invalid.
	 * @throws InvalidLocationException
	 *		If the given location is invalid.
	 * @throws InvalidAmountOfParametersException
	 *		If the amount of given parameters is invalid.
	 */
	@Override
	public Hospital createMapItem(Object[] parameters) throws InvalidMapItemNameException, InvalidLocationException, InvalidAmountOfParametersException {
		if (parameters.length != 2) {
			throw new InvalidAmountOfParametersException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			return new Hospital((String) parameters[0], (GPSCoordinate) parameters[1]);
		}
	}
}
