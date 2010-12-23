package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.events.SimpleScenario;
import be.kuleuven.cs.swop.events.Time;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.lists.World;

public class EmergencyDispatchApiTest {

	private World world;
	private EmergencyDispatchApi api;
	private Time time;
	private SimpleScenario simpleScenario;

	@Before
	public void setUp() {
		world = new World();
		time = new Time(1, 10);
		api = new EmergencyDispatchApi(world);
		simpleScenario = new SimpleScenario(api);
	}

	@Test
	public void testNotifyTimeChanged() throws ExternalSystemException {
		assertEquals(0, world.getEmergencyList().toArray().length);
		simpleScenario.notifyTimeChanged(time);
		assertEquals(2, world.getEmergencyList().toArray().length);
	}
}
