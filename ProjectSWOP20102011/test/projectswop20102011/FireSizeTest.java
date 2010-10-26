/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectswop20102011;

import projectswop20102011.exceptions.InvalidFireSizeException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author willem
 */
public class FireSizeTest {

    public FireSizeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of matches method, of class FireSize.
     */
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

    /**
     * Test of parse method, of class FireSize.
     */
    @Test
    public void testParse() throws Exception {
        assertEquals(FireSize.LOCAL, FireSize.parse(FireSize.LOCAL.getTextual()));
        assertEquals(FireSize.HOUSE, FireSize.parse(FireSize.HOUSE.getTextual()));
        assertEquals(FireSize.FACILITY, FireSize.parse(FireSize.FACILITY.getTextual()));
        assertEquals(FireSize.LOCAL, FireSize.parse(FireSize.LOCAL.getTextual().toUpperCase()));
        assertEquals(FireSize.HOUSE, FireSize.parse(FireSize.HOUSE.getTextual().toUpperCase()));
        assertEquals(FireSize.FACILITY, FireSize.parse(FireSize.FACILITY.getTextual().toUpperCase()));
    }

    /**
     * Test of parse method, of class FireSize.
     */
    @Test(expected = InvalidFireSizeException.class)
    public void testParseException1() throws InvalidFireSizeException {
        FireSize.parse("lokaal");
    }

    /**
     * Test of parse method, of class FireSize.
     */
    @Test(expected = InvalidFireSizeException.class)
    public void testParseException2() throws InvalidFireSizeException {
        FireSize.parse("huis");
    }

    /**
     * Test of parse method, of class FireSize.
     */
    @Test(expected = InvalidFireSizeException.class)
    public void testParseException3() throws InvalidFireSizeException {
        FireSize.parse("faculteit");
    }
}
