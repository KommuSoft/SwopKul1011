package projectswop20102011;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class UnitBuildingListTest {

    private NotTimeSensitiveUnitBuildingList ubl1, ubl2;

    @Before
    public void setUp(){
        ubl1 = new NotTimeSensitiveUnitBuildingList();
        ubl2 = new NotTimeSensitiveUnitBuildingList();
    }

    @Test
    public void testConstructor() {
        assertNotNull(ubl1);
        assertEquals(0, ubl1.getNotTimeSensitiveUnitBuildings().size());
    }

    @Test
    public void testAdd() throws InvalidUnitBuildingException{

    }

    @Test(expected = InvalidUnitBuildingException.class)
    public void testAddException() throws InvalidUnitBuildingException {

    }
}
