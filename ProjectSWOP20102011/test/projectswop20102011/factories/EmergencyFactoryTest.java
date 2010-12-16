package projectswop20102011.factories;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmergencyFactoryTest {

	@Test
	public void testIsValidEmergencyTypeName(){
		assertTrue(EmergencyFactory.isValidEmergencyTypeName("hoipipeloi"));
	}

	@Test
	public void testIsValidEmergencyTypeNameInvalid1(){
		assertFalse(EmergencyFactory.isValidEmergencyTypeName(null));
	}

	@Test
	public void testIsValidEmergencyTypeNameInvalid2(){
		assertFalse(EmergencyFactory.isValidEmergencyTypeName(""));
	}

}
