package projectswop20102011.factories;

import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents an AmbulanceFactory.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AmbulanceFactory extends UnitFactory {

	/**
	 * Creates a new AmbulanceFactory.
	 *
	 * @throws InvalidMapItemTypeNameException
	 *		If the type name of the new AmbulanceFactory is invalid.
	 */
	public AmbulanceFactory() throws InvalidMapItemTypeNameException {
		super("ambulance");
	}

	/**
	 * Creates a new Ambulance.
	 * @param parameters
	 *		The parameters of the constructor.
	 * @return The new Ambulance.
	 * @throws InvalidLocationException
	 *		If the given location is invalid.
	 * @throws InvalidMapItemNameException
	 *		If the given name for the mapItem is invalid.
	 * @throws InvalidSpeedException
	 *		If the given speed is invalid.
	 * @throws InvalidAmountOfParametersException
	 *		If the amount of given parameters is invalid.
	 */
	@Override
	public Ambulance createMapItem(Object[] parameters) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidAmountOfParametersException {
		if (parameters.length != 3) {
			throw new InvalidAmountOfParametersException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			return new Ambulance((String) parameters[0], (GPSCoordinate) parameters[1], (Long) parameters[2]);
		}
	}
}
