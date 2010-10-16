package projectswoptest20102011;

import projectswop20102011.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class CallTest extends TestCase{
        Call call;
        Date timestamp;

        @Override
        @Before
        public void setUp() throws Exception {
            timestamp = new Date();
        }
        /*
         * Ik vraag mij wel af of dit een goede test is
         * Kheb het altijd al ambetante dingen gevonden
         *om met die date-objecten te werken
         */
        @Test
        public void testConstructor(){
            call = new Call(timestamp);
            assertEquals(timestamp,call.getTimestamp());
        }

}
