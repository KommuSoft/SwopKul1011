package projectswop20102011.utils;

import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.utils.parsers.GPSCoordinateParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TextScannerTest {

    public TextScannerTest() {
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
     * Test of canRead method, of class TextScanner.
     */
    @Test
    public void testCanRead() {
        TextScanner ts = new TextScanner("test(1425,-1302)test");
        assertTrue(ts.canRead(new GPSCoordinateParser()));

        GPSCoordinate gpsc;
    }

    /**
     * Test of read method, of class TextScanner.
     */
    @Test
    public void testRead() throws Exception {
        TextScanner ts = new TextScanner("test(1425,1302)cant(-14,25)fail");
        GPSCoordinateParser gpscp = new GPSCoordinateParser();
        GPSCoordinate gspc = ts.read(gpscp);
        assertEquals(1425,gspc.getX());
        assertEquals(1302,gspc.getY());
        gspc = ts.read(gpscp);
        assertEquals(-14,gspc.getX());
        assertEquals(25,gspc.getY());
    }

}