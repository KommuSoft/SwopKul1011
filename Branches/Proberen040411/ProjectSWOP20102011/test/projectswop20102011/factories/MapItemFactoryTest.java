package projectswop20102011.factories;

import org.junit.Test;
import static org.junit.Assert.*;

public class MapItemFactoryTest {

	@Test
	public void testIsValidEmergencyTypeName(){
		assertTrue(MapItemFactory.isValidMapItemTypeName("hoipipeloi"));
	}

	@Test
	public void testIsValidEmergencyTypeNameInvalid1(){
		assertFalse(MapItemFactory.isValidMapItemTypeName(null));
	}

	@Test
	public void testIsValidEmergencyTypeNameInvalid2(){
		assertFalse(MapItemFactory.isValidMapItemTypeName(""));
	}
}
