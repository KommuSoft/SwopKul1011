package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WorldTest{
	private World w1;

	@Before
	public void setUp(){
		w1 = new World();
	}

	@Test
	public void testConstructor() {
		assertNotNull(w1);
		assertNotNull(w1.getEmergencyList());
		assertNotNull(w1.getNotTimeSensitiveUnitBuildingList());
		assertNotNull(w1.getTimeSensitiveUnitBuildingList());
	}

}