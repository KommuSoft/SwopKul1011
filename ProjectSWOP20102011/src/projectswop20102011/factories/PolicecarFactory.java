package projectswop20102011.factories;

import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Policecar;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents a PolicecarFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class PolicecarFactory extends UnitFactory {

	/**
	 * Creates a new PolicecarFactory.
	 *
	 * @throws InvalidMapItemTypeNameException
	 *		If the type name of the new PolicecarFactory is invalid.
	 */
	public PolicecarFactory() throws InvalidMapItemTypeNameException {
		super("policecar");
	}

	/**
	 * Creates a new Policecar.
	 *@param parameters
	 *		The parameters of the constructor.
	 * @return The new Policecar.
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
	public Policecar createMapItem(Object[] parameters) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidAmountOfParametersException {
		if (parameters.length != 3) {
			throw new InvalidAmountOfParametersException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			return new Policecar((String) parameters[0], (GPSCoordinate) parameters[1], (Long) parameters[2]);
		}
	}
}
