package EntityTest.AccountTest.CellPhoneFormatTest;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import junit.framework.Assert;
import org.junit.Test;

public class CellPhoneFormatTest {
    @Test
    public void getFirstTest(){
        CellPhoneFormat cellphone = BoundedCellPhoneFormat.Make("111", "222","4444");
        Assert.assertEquals("111", cellphone.getFirst());
    }

    @Test
    public void getMiddleTest(){
        CellPhoneFormat cellphone = BoundedCellPhoneFormat.Make("111", "222","4444");
        Assert.assertEquals("222", cellphone.getMiddle());
    }

    @Test
    public void getLastTest(){
        CellPhoneFormat cellphone = BoundedCellPhoneFormat.Make("111", "222","4444");
        Assert.assertEquals("4444", cellphone.getLast());
    }

}
