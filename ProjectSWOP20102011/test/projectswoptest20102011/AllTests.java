package projectswoptest20102011;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class AllTests {
	public static Test suite() {
            TestSuite suite = new TestSuite("Test for the package projectswoptest20102011");

            suite.addTestSuite(CallerTest.class);
            suite.addTestSuite(CallTest.class);
            suite.addTestSuite(OperatorTest.class);

            return suite;
	}
}