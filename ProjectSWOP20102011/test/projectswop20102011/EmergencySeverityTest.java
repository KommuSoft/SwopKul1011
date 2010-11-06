package projectswop20102011;

import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;

public class EmergencySeverityTest {

    @Test
    public void testMatches() {
        assertTrue(EmergencySeverity.BENIGN.matches("BENIGN"));
        assertTrue(EmergencySeverity.BENIGN.matches("BeNiGn"));
        assertTrue(EmergencySeverity.BENIGN.matches("benign"));
        assertTrue(EmergencySeverity.BENIGN.matches(EmergencySeverity.BENIGN.getTextual()));
        assertFalse(EmergencySeverity.BENIGN.matches("Begin"));
        assertTrue(EmergencySeverity.NORMAL.matches("NORMAL"));
        assertTrue(EmergencySeverity.NORMAL.matches("NoRmAl"));
        assertTrue(EmergencySeverity.NORMAL.matches("normal"));
        assertTrue(EmergencySeverity.NORMAL.matches(EmergencySeverity.NORMAL.getTextual()));
        assertFalse(EmergencySeverity.BENIGN.matches("normaal"));
        assertTrue(EmergencySeverity.SERIOUS.matches("SERIOUS"));
        assertTrue(EmergencySeverity.SERIOUS.matches("SeRiOuS"));
        assertTrue(EmergencySeverity.SERIOUS.matches("serious"));
        assertTrue(EmergencySeverity.SERIOUS.matches(EmergencySeverity.SERIOUS.getTextual()));
        assertFalse(EmergencySeverity.SERIOUS.matches("serieus"));
        assertTrue(EmergencySeverity.URGENT.matches("URGENT"));
        assertTrue(EmergencySeverity.URGENT.matches("UrGeNt"));
        assertTrue(EmergencySeverity.URGENT.matches("urgent"));
        assertTrue(EmergencySeverity.URGENT.matches(EmergencySeverity.URGENT.getTextual()));
        assertFalse(EmergencySeverity.URGENT.matches("UGent"));
    }

    /**
     * Test of parse method, of class EmergencySeverity.
     */
    @Test
    public void testParse() throws InvalidEmergencySeverityException {
        assertEquals(EmergencySeverity.BENIGN, EmergencySeverity.parse(EmergencySeverity.BENIGN.getTextual()));
        assertEquals(EmergencySeverity.NORMAL, EmergencySeverity.parse(EmergencySeverity.NORMAL.getTextual()));
        assertEquals(EmergencySeverity.SERIOUS, EmergencySeverity.parse(EmergencySeverity.SERIOUS.getTextual()));
        assertEquals(EmergencySeverity.URGENT, EmergencySeverity.parse(EmergencySeverity.URGENT.getTextual()));
        assertEquals(EmergencySeverity.BENIGN, EmergencySeverity.parse(EmergencySeverity.BENIGN.getTextual().toUpperCase()));
        assertEquals(EmergencySeverity.NORMAL, EmergencySeverity.parse(EmergencySeverity.NORMAL.getTextual().toUpperCase()));
        assertEquals(EmergencySeverity.SERIOUS, EmergencySeverity.parse(EmergencySeverity.SERIOUS.getTextual().toUpperCase()));
        assertEquals(EmergencySeverity.URGENT, EmergencySeverity.parse(EmergencySeverity.URGENT.getTextual().toUpperCase()));
    }

    @Test(expected = InvalidEmergencySeverityException.class)
    public void testParseException1() throws InvalidEmergencySeverityException {
        EmergencySeverity.parse("begin");
    }

    @Test(expected = InvalidEmergencySeverityException.class)
    public void testParseException2() throws InvalidEmergencySeverityException {
        EmergencySeverity.parse("normaal");
    }

    @Test(expected = InvalidEmergencySeverityException.class)
    public void testParseException3() throws InvalidEmergencySeverityException {
        EmergencySeverity.parse("serieus");
    }

    @Test(expected = InvalidEmergencySeverityException.class)
    public void testParseException4() throws InvalidEmergencySeverityException {
        EmergencySeverity.parse("UGent");
    }

	@Test
	public void testToString(){
		assertEquals("benign", EmergencySeverity.BENIGN.toString());
		assertEquals("normal", EmergencySeverity.NORMAL.toString());
		assertEquals("serious", EmergencySeverity.SERIOUS.toString());
		assertEquals("urgent", EmergencySeverity.URGENT.toString());
	}
}
