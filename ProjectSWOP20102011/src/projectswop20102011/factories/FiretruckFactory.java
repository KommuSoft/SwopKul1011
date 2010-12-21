package projectswop20102011.factories;

import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

/**
 * A class that represents a FiretruckFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FiretruckFactory extends UnitFactory {

	/**
	 * Creates a new FiretruckFactory.
	 *
	 * @throws InvalidMapItemTypeNameException
	 *      If the type name of the new firetruck is invalid.
	 */
	public FiretruckFactory() throws InvalidMapItemTypeNameException {
		super("firetruck");
	}

	/**
	 * Creates a new Firetruck.
	 * @param parameters
	 *		The parameters of the constructor.
	 * @return The new Firetruck.
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
	public Firetruck createMapItem(Object[] parameters) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidAmountOfParametersException, InvalidFireSizeException {
		if (parameters.length != 4) {
			throw new InvalidAmountOfParametersException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			return new Firetruck((String) parameters[0], (GPSCoordinate) parameters[1], (Long) parameters[2], (FireSize) parameters[3]);
		}
	}
}
