/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectswop20102011.utils.parsers;

import java.util.regex.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.ParsingException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class GPSCoordinateParserTest {
    
    private GPSCoordinateParser parser = new GPSCoordinateParser();

    public GPSCoordinateParserTest() {
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

    @Test
    public void testConstructor () {
        new GPSCoordinateParser();
    }

    /**
     * Test of parseFromMatcher method, of class GPSCoordinateParser.
     */
    @Test(expected=ParsingException.class)
    public void testParseFail1() throws ParsingException {
        this.parser.parse("parserfaalt");
    }
    @Test(expected=ParsingException.class)
    public void testParseFail2() throws ParsingException {
        this.parser.parse("(parser,faalt)");
    }
    @Test(expected=ParsingException.class)
    public void testParseFail3() throws ParsingException {
        this.parser.parse("((,),)");
    }
    @Test
    public void testParse() throws ParsingException {
        GPSCoordinate gps1 = this.parser.parse("(-1302,1425)");
        assertEquals(-1302,gps1.getX());
        assertEquals(1425,gps1.getY());
        GPSCoordinate gps2 = this.parser.parse("(0,-12)");
        assertEquals(0,gps2.getX());
        assertEquals(-12,gps2.getY());
        GPSCoordinate gps3 = this.parser.parse("dezetestmoetookslagen(0,-12)anders werkt het niet");
        assertEquals(0,gps3.getX());
        assertEquals(-12,gps3.getY());
    }

}