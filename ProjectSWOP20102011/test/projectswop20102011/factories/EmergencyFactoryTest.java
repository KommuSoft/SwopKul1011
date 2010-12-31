package projectswop20102011.factories;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmergencyFactoryTest {

	@Test
	public void testIsValidEmergencyTypeName(){
		assertTrue(EmergencyFactory.isValidName("hoipipeloi"));
	}

	@Test
	public void testIsValidEmergencyTypeNameInvalid1(){
		assertFalse(EmergencyFactory.isValidName(null));
	}

	@Test
	public void testIsValidEmergencyTypeNameInvalid2(){
		assertFalse(EmergencyFactory.isValidName(""));
	}

}
