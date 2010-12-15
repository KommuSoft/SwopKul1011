package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.external.ExternalSystemException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidWorldException;

public class ExternalSystemTest {

	private EmergencyDispatchApi emergencyDispatchApi;
	private ExternalSystem externalSystem;
	private Time time1, time2, time3;

	@Before
	public void setUp() throws InvalidWorldException {
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
	public void testNotifyTimeChanged() throws ExternalSystemException {
		externalSystem.notifyTimeChanged(time1);
		assertTrue(false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalNotifyTimeChanged() throws ExternalSystemException{
		externalSystem.notifyTimeChanged(time2);
	}
}
