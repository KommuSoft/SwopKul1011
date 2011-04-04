package projectswop20102011.userinterface;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.controllers.ReadEnvironmentDataController;
import projectswop20102011.domain.FireSize;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.utils.parsers.FireSizeParser;
import projectswop20102011.utils.parsers.GPSCoordinateParser;
import projectswop20102011.utils.parsers.LongParser;

/**
 * A text stream that reads in the environment, and maps its meaning to the ReadEnviromentDataController.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EnvironmentReader {

	/**
	 * A controller to manipulate the world.
	 */
	private final ReadEnvironmentDataController controller;
	private final Pattern REGEX_BUILDING = Pattern.compile("^([A-Za-z0-9]+),([^,]+),(\\([^(,)]+,[^(,)]+\\))$");
	private final Pattern REGEX_UNIT = Pattern.compile("^([A-Za-z0-9]+),([^,]+),(\\([^(,)]+,[^(,)]+\\)),([0-9]+)(,([0-9]+))?$");

	/**
	 * Creates a new instance of an EnvironmentReader with a given ReadEnvironmentDataController to manipulate the environment of the world.
	 * @param controller The controller that manipulates the world.
	 */
	public EnvironmentReader(ReadEnvironmentDataController controller) throws InvalidControllerException {
		if (controller == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.controller = controller;
	}

	public void readEnvironmentData(InputStream inputStream) throws Exception {
		Scanner scanner = new Scanner(inputStream);
		GPSCoordinateParser gpsCoordinateParser = new GPSCoordinateParser();
		LongParser longParser = new LongParser();
		LongParser capacityParser = new LongParser();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Matcher M = REGEX_BUILDING.matcher(line);
			if (M.find()) {
				String type = M.group(1);
				String name = M.group(2);
				GPSCoordinate location = gpsCoordinateParser.parse(M.group(3));
				if (type.equals("Hospital")) {
					this.controller.addHospital(name, location);
				}
			} else {
				M = REGEX_UNIT.matcher(line);
				if (M.find()) {
					String type = M.group(1);
					String name = M.group(2);
					GPSCoordinate location = gpsCoordinateParser.parse(M.group(3));
					long speed = longParser.parse(M.group(4));
					if (type.equals("Policecar")) {
						this.controller.addPolicecar(name, location, speed);
					} else if (type.equals("Firetruck")) {
						long capacity = capacityParser.parse(M.group(6));
						this.controller.addFiretruck(name, location, speed, capacity);
					} else if (type.equals("Ambulance")) {
						this.controller.addAmbulance(name, location, speed);
					}
				}
			}
		}
	}
}
