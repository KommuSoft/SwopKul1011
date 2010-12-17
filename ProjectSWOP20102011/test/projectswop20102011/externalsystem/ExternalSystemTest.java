package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class ExternalSystemTest {

	private EmergencyDispatchApi emergencyDispatchApi;
	private ExternalSystem externalSystem;
	private Time time1, time2, time3;
	private World world;
	private Event event1;
	private Location location1;
	private int x1, y1;
	private EmergencySeverity severity1;
	private String description1;
	private FireSize size1;
	private boolean chemical1;
	private long trappedPeople1;
	private long numberOfInjured1;

	@Before
	public void setUp() throws InvalidWorldException, InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyTypeNameException, InvalidParameterException, InvalidAmountOfParametersException {
		x1 = 10;
		y1 = 156;

		severity1 = EmergencySeverity.NORMAL;
		description1 = "Brand";
		size1 = FireSize.LOCAL;
		chemical1 = false;
		trappedPeople1 = 0;
		numberOfInjured1 = 123654;

		location1 = new Location(x1, y1);
		world = new World();
		event1 = new FireEvent(time1, world, location1, severity1, description1, size1, chemical1, trappedPeople1, numberOfInjured1);

		emergencyDispatchApi = new EmergencyDispatchApi(new World());
		externalSystem = (ExternalSystem) ExternalSystem.bootstrap(emergencyDispatchApi);

		time1 = new Time(10, 0);
		time2 = new Time(0, 0);
		time3 = null;
	}

	@Test
	public void testBootstrap() {
		assertEquals(0, externalSystem.getCurrentTime().compareTo(new Time(0, 0)));
	}

	@Test
	public void testIsValidNewTime() {
		assertTrue(externalSystem.isValidNewTime(time1));
		assertFalse(externalSystem.isValidNewTime(time2));
		assertFalse(externalSystem.isValidNewTime(time3));
	}

	@Test
	public void testNotifyTimeChanged() throws EmergencyDispatchException, ExternalSystemException {
		emergencyDispatchApi.registerNewEvent(event1);
		externalSystem.notifyTimeChanged(time1);

		assertTrue(externalSystem.getCurrentTime().compareTo(time1) == 0);
		//TODO testen of de emergency effectief geregistreerd is
		assertTrue(false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalNotifyTimeChanged() throws ExternalSystemException {
		externalSystem.notifyTimeChanged(time2);
	}
}
