package projectswop20102011.externalsystem;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidWorldException;

public class EmergencyDispatchApiTest {

	private World world;
	private EmergencyDispatchApi emergencyDispatchApi;

	@Before
	public void setUp() {
		world = new World();
	}

	@Test
	public void testConstructor() throws InvalidWorldException {
		emergencyDispatchApi = new EmergencyDispatchApi(world);
		assertEquals(world, emergencyDispatchApi.getWorld());
	}

	@Test
	public void testRegisterNewEvent(){
		assertTrue(false);
	}
}
