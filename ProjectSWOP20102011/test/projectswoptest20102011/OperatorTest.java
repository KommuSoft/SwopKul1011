package projectswoptest20102011;

import projectswop20102011.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class OperatorTest extends TestCase{

        Operator operator;
        String name;

        @Override
        @Before
        public void setUp() throws Exception {
            name = "Jan de Helper";
        }

        @Test
        public void testConstructor(){
            operator = new Operator(name);
            assertEquals(name,operator.getName());
        }
}
