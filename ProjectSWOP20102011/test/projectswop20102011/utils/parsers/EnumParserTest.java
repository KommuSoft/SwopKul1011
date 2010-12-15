/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectswop20102011.utils.parsers;

import projectswop20102011.domain.FireSize;
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
public class EnumParserTest {

    public EnumParserTest() {
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
    public void testConstructor() {
		//TODO dit print iets af
        new EnumParser(FireSize.class);
    }
    

}