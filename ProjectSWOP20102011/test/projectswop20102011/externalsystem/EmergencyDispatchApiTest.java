package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.events.SimpleScenario;
import org.junit.Before;
import projectswop20102011.domain.World;

public class EmergencyDispatchApiTest {

	@Before
	public void setUp() {
		World world = new World();
		EmergencyDispatchApi api = new EmergencyDispatchApi(world);
		SimpleScenario ss = new SimpleScenario(api);
	}
}
