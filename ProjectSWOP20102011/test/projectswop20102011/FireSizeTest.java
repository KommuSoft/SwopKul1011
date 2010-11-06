package projectswop20102011;

import projectswop20102011.exceptions.InvalidFireSizeException;
import org.junit.Test;
import static org.junit.Assert.*;

public class FireSizeTest {

    @Test
    public void testMatches() {
        assertTrue(FireSize.LOCAL.matches("LOCAL"));
        assertTrue(FireSize.LOCAL.matches("LoCaL"));
        assertTrue(FireSize.LOCAL.matches("local"));
        assertTrue(FireSize.LOCAL.matches(FireSize.LOCAL.getTextual()));
        assertFalse(FireSize.LOCAL.matches("lokaal"));
        assertTrue(FireSize.HOUSE.matches("HOUSE"));
        assertTrue(FireSize.HOUSE.matches("HoUsE"));
        assertTrue(FireSize.HOUSE.matches("house"));
        assertTrue(FireSize.HOUSE.matches(FireSize.HOUSE.getTextual()));
        assertFalse(FireSize.HOUSE.matches("huis"));
        assertTrue(FireSize.FACILITY.matches("FACILITY"));
        assertTrue(FireSize.FACILITY.matches("FaCiLiTy"));
        assertTrue(FireSize.FACILITY.matches("facility"));
        assertTrue(FireSize.FACILITY.matches(FireSize.FACILITY.getTextual()));
        assertFalse(FireSize.FACILITY.matches("faculteit"));
    }

    @Test
    public void testParse() throws Exception {
        assertEquals(FireSize.LOCAL, FireSize.parse(FireSize.LOCAL.getTextual()));
        assertEquals(FireSize.HOUSE, FireSize.parse(FireSize.HOUSE.getTextual()));
        assertEquals(FireSize.FACILITY, FireSize.parse(FireSize.FACILITY.getTextual()));
        assertEquals(FireSize.LOCAL, FireSize.parse(FireSize.LOCAL.getTextual().toUpperCase()));
        assertEquals(FireSize.HOUSE, FireSize.parse(FireSize.HOUSE.getTextual().toUpperCase()));
        assertEquals(FireSize.FACILITY, FireSize.parse(FireSize.FACILITY.getTextual().toUpperCase()));
    }

    @Test(expected = InvalidFireSizeException.class)
    public void testParseException1() throws InvalidFireSizeException {
        FireSize.parse("lokaal");
    }

    @Test(expected = InvalidFireSizeException.class)
    public void testParseException2() throws InvalidFireSizeException {
        FireSize.parse("huis");
    }

    @Test(expected = InvalidFireSizeException.class)
    public void testParseException3() throws InvalidFireSizeException {
        FireSize.parse("faculteit");
    }

	@Test
	public void testToString(){
		assertEquals("facility", FireSize.FACILITY.toString());
		assertEquals("house", FireSize.HOUSE.toString());
		assertEquals("local", FireSize.LOCAL.toString());
	}
}
