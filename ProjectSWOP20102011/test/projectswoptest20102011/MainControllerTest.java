/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectswoptest20102011;

import projectswop20102011.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jonas
 */
public class MainControllerTest {

    public MainControllerTest() {
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

	@Test
	public void testActorAnalysis () {
		assertEquals(MainController.readActor("Operator ping"),"Operator");
	}

    @After
    public void tearDown() {
    }

	/**
	 * Test of readInput method, of class MainController.
	 */
	@Test
	public void testReadInput() {
		System.out.println("readInput");
		String expression = "";
		MainController.readInput(expression);
	}

	/**
	 * Test of readActor method, of class MainController.
	 */
	@Test
	public void testReadActor() {
		System.out.println("readActor");
		String expression = "";
		String expResult = "";
		String result = MainController.readActor(expression);
		assertEquals(expResult, result);
	}

}