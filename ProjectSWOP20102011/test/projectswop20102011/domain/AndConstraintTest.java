package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AndConstraintTest {
	AtomicConstraint c1, c2;

	@Before
	public void setUp(){
		c1 = new AtomicConstraint() {
			@Override
			public boolean isValid(Unit... u) {
				return true;
			}
		};
		c2 = new AtomicConstraint() {
			@Override
			public boolean isValid(Unit... u) {
				return false;
			}
		};
	}

	@Test
	public void testIsValid(){
		assertTrue(AndConstraint.isValid(c1, c1));
		assertFalse(AndConstraint.isValid(c1, c2));
		assertFalse(AndConstraint.isValid(c2, c1));
		assertFalse(AndConstraint.isValid(c2, c2));
	}

}