package projectswop20102011;

import junit.framework.TestCase;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class CallTest {
    
        private Call call1, call2;
        private Date timestamp;

        @Before
        public void setUp(){
            timestamp = new Date();
        }
		
        @Test
        public void testConstructor() throws InvalidTimestampException{
            call1 = new Call(timestamp);
            assertEquals(timestamp,call1.getTimestamp());
        }


        @Test(expected=InvalidTimestampException.class)
        public void testInvalidTimestampConstructor() throws InvalidTimestampException{
                call2 = new Call(new Date(System.currentTimeMillis()+100000));
        }


}
