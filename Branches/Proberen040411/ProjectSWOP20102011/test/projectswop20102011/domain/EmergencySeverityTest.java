package projectswop20102011.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;

public class EmergencySeverityTest {

    @Test
    public void testMatches() {
        assertTrue(EmergencySeverity.BENIGN.matches("BENIGN"));
        assertTrue(EmergencySeverity.BENIGN.matches("BeNiGn"));
        assertTrue(EmergencySeverity.BENIGN.matches("benign"));
        assertTrue(EmergencySeverity.BENIGN.matches(EmergencySeverity.BENIGN.toString()));
        assertFalse(EmergencySeverity.BENIGN.matches("Begin"));
        assertTrue(EmergencySeverity.NORMAL.matches("NORMAL"));
        assertTrue(EmergencySeverity.NORMAL.matches("NoRmAl"));
        assertTrue(EmergencySeverity.NORMAL.matches("normal"));
        assertTrue(EmergencySeverity.NORMAL.matches(EmergencySeverity.NORMAL.toString()));
        assertFalse(EmergencySeverity.BENIGN.matches("normaal"));
        assertTrue(EmergencySeverity.SERIOUS.matches("SERIOUS"));
        assertTrue(EmergencySeverity.SERIOUS.matches("SeRiOuS"));
        assertTrue(EmergencySeverity.SERIOUS.matches("serious"));
        assertTrue(EmergencySeverity.SERIOUS.matches(EmergencySeverity.SERIOUS.toString()));
        assertFalse(EmergencySeverity.SERIOUS.matches("serieus"));
        assertTrue(EmergencySeverity.URGENT.matches("URGENT"));
        assertTrue(EmergencySeverity.URGENT.matches("UrGeNt"));
        assertTrue(EmergencySeverity.URGENT.matches("urgent"));
        assertTrue(EmergencySeverity.URGENT.matches(EmergencySeverity.URGENT.toString()));
        assertFalse(EmergencySeverity.URGENT.matches("UGent"));
    }

    @Test
    public void testParse() throws InvalidEmergencySeverityException {
        assertEquals(EmergencySeverity.BENIGN, EmergencySeverity.parse(EmergencySeverity.BENIGN.toString()));
        assertEquals(EmergencySeverity.NORMAL, EmergencySeverity.parse(EmergencySeverity.NORMAL.toString()));
        assertEquals(EmergencySeverity.SERIOUS, EmergencySeverity.parse(EmergencySeverity.SERIOUS.toString()));
        assertEquals(EmergencySeverity.URGENT, EmergencySeverity.parse(EmergencySeverity.URGENT.toString()));
        assertEquals(EmergencySeverity.BENIGN, EmergencySeverity.parse(EmergencySeverity.BENIGN.toString().toUpperCase()));
        assertEquals(EmergencySeverity.NORMAL, EmergencySeverity.parse(EmergencySeverity.NORMAL.toString().toUpperCase()));
        assertEquals(EmergencySeverity.SERIOUS, EmergencySeverity.parse(EmergencySeverity.SERIOUS.toString().toUpperCase()));
        assertEquals(EmergencySeverity.URGENT, EmergencySeverity.parse(EmergencySeverity.URGENT.toString().toUpperCase()));
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
    public void testToString() {
        assertEquals("benign", EmergencySeverity.BENIGN.toString());
        assertEquals("normal", EmergencySeverity.NORMAL.toString());
        assertEquals("serious", EmergencySeverity.SERIOUS.toString());
        assertEquals("urgent", EmergencySeverity.URGENT.toString());
    }

    @Test
    public void testGetMaximum () {
        assertEquals(EmergencySeverity.BENIGN,EmergencySeverity.BENIGN.getMaximum(EmergencySeverity.BENIGN));
        assertEquals(EmergencySeverity.NORMAL,EmergencySeverity.BENIGN.getMaximum(EmergencySeverity.NORMAL));
        assertEquals(EmergencySeverity.SERIOUS,EmergencySeverity.BENIGN.getMaximum(EmergencySeverity.SERIOUS));
        assertEquals(EmergencySeverity.URGENT,EmergencySeverity.BENIGN.getMaximum(EmergencySeverity.URGENT));

        assertEquals(EmergencySeverity.NORMAL,EmergencySeverity.NORMAL.getMaximum(EmergencySeverity.BENIGN));
        assertEquals(EmergencySeverity.NORMAL,EmergencySeverity.NORMAL.getMaximum(EmergencySeverity.NORMAL));
        assertEquals(EmergencySeverity.SERIOUS,EmergencySeverity.NORMAL.getMaximum(EmergencySeverity.SERIOUS));
        assertEquals(EmergencySeverity.URGENT,EmergencySeverity.NORMAL.getMaximum(EmergencySeverity.URGENT));

        assertEquals(EmergencySeverity.SERIOUS,EmergencySeverity.SERIOUS.getMaximum(EmergencySeverity.BENIGN));
        assertEquals(EmergencySeverity.SERIOUS,EmergencySeverity.SERIOUS.getMaximum(EmergencySeverity.NORMAL));
        assertEquals(EmergencySeverity.SERIOUS,EmergencySeverity.SERIOUS.getMaximum(EmergencySeverity.SERIOUS));
        assertEquals(EmergencySeverity.URGENT,EmergencySeverity.SERIOUS.getMaximum(EmergencySeverity.URGENT));

        assertEquals(EmergencySeverity.URGENT,EmergencySeverity.URGENT.getMaximum(EmergencySeverity.BENIGN));
        assertEquals(EmergencySeverity.URGENT,EmergencySeverity.URGENT.getMaximum(EmergencySeverity.NORMAL));
        assertEquals(EmergencySeverity.URGENT,EmergencySeverity.URGENT.getMaximum(EmergencySeverity.SERIOUS));
        assertEquals(EmergencySeverity.URGENT,EmergencySeverity.URGENT.getMaximum(EmergencySeverity.URGENT));
    }
}
