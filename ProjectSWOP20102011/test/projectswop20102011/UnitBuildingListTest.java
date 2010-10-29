package projectswop20102011;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class UnitBuildingListTest {

    private UnitBuildingList ubl1, ubl2;

    @Before
    public void setUp(){
        ubl1 = new UnitBuildingList();
        ubl2 = new UnitBuildingList();
    }

    @Test
    public void testConstructor() {
        assertNotNull(ubl1);
        assertEquals(0, ubl1.getUnitBuildings().size());
    }

    @Test
    public void testAdd() throws InvalidUnitBuildingException{

    }

    @Test(expected = InvalidUnitBuildingException.class)
    public void testAddException() throws InvalidUnitBuildingException {

    }
}
