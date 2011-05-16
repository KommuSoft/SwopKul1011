package projectswop20102011.domain;

import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.World;
import projectswop20102011.controllers.DispatchUnitsToEmergencyController;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class DispatchTest {
	private World world;
	private DispatchUnitsToEmergencyController duc;
	private Fire f1;
	private Firetruck ft;
	private Ambulance am1, am2, am3;

	@Before
	public void setUp() throws InvalidWorldException, InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException{
		world = new World();
		duc = new DispatchUnitsToEmergencyController(world);
		f1 = new Fire(new GPSCoordinate(0,10), EmergencySeverity.BENIGN, "brand", FireSize.LOCAL, false, 0, 1);

		ft = new Firetruck("vuurwagen", new GPSCoordinate(98,9), 100, 1001);
		am1 = new Ambulance("ziekenwagen", new GPSCoordinate(9,98), 100);
		am2 = new Ambulance("ziekenwagen", new GPSCoordinate(9,98), 100);
		am3 = new Ambulance("ziekenwagen", new GPSCoordinate(9,98), 100);

		world.getMapItemList().addMapItem(ft);
		world.getMapItemList().addMapItem(am1);
		world.getMapItemList().addMapItem(am2);
		world.getMapItemList().addMapItem(am3);

		world.getTimeSensitiveList().addTimeSensitive(ft);
		world.getTimeSensitiveList().addTimeSensitive(am1);
		world.getTimeSensitiveList().addTimeSensitive(am2);
		world.getTimeSensitiveList().addTimeSensitive(am3);
	}

	@Test
	public void testDispatch() throws InvalidEmergencyStatusException, InvalidEmergencyException{
		assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, f1.getStatus());

		HashSet<Unit> units = new HashSet<Unit>();
		units.add(ft);
		units.add(am1);
		duc.dispatchToEmergency(f1, units);

		assertEquals(EmergencyStatus.RESPONSE_IN_PROGRESS, f1.getStatus());
	}

}
