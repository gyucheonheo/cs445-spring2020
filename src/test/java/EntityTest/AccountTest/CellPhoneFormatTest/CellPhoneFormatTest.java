package EntityTest.AccountTest.CellPhoneFormatTest;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import junit.framework.Assert;
import org.junit.Test;

public class CellPhoneFormatTest {
    @Test(expected= CellPhoneFormat.FirstMustBeDigit.class)
    public void if_first_is_not_digit_it_throws_FirstMustBeDigitException(){
        BoundedCellPhoneFormat.Make("31a","222","4444");
    }
    @Test(expected= CellPhoneFormat.FirstMustBeThreeDigit.class)
    public void if_first_is_less_than_3_digit_it_throws_First_MustBeThreeDigitException(){
         BoundedCellPhoneFormat.Make("1", "222","4444");
    }
    @Test(expected= CellPhoneFormat.FirstMustBeThreeDigit.class)
    public void if_first_is_greater_than_3_digit_it_throws_First_MustBeThreeDigitException(){
         BoundedCellPhoneFormat.Make("1111", "222","4444");
    }
    @Test
    public void getFirstTest(){
        CellPhoneFormat cellphone = BoundedCellPhoneFormat.Make("111", "222","4444");
        Assert.assertEquals("111", cellphone.getFirst());
    }
    @Test(expected= CellPhoneFormat.MiddleMustBeDigit.class)
    public void if_middle_is_not_digit_it_throws_MiddleMustBeDigitException(){
        BoundedCellPhoneFormat.Make("111", "a22", "4444");
    }
    @Test(expected= CellPhoneFormat.MiddleMustBeThreeDigit.class)
    public void if_middle_is_less_than_3_digit_it_throws_MiddleMustBeThreeDigitException(){
        BoundedCellPhoneFormat.Make("111", "2","4444");
    }
    @Test(expected= CellPhoneFormat.MiddleMustBeThreeDigit.class)
    public void if_middle_is_greater_than_3_digit_it_throws_MiddleMustBeThreeDigitException(){
        BoundedCellPhoneFormat.Make("111", "2222","4444");
    }

    @Test
    public void getMiddleTest(){
        CellPhoneFormat cellphone = BoundedCellPhoneFormat.Make("111", "222","4444");
        Assert.assertEquals("222", cellphone.getMiddle());
    }
    @Test(expected= CellPhoneFormat.LastMustBeDigit.class)
    public void if_middle_is_not_digit_it_throws_LastMustBeDigitException(){
        BoundedCellPhoneFormat.Make("111", "222", "4a4a");
    }
    @Test(expected= CellPhoneFormat.LastMustBeFourDigit.class)
    public void if_last_is_less_than_4_digit_it_throws_LastMustBeFourDigitException(){
        BoundedCellPhoneFormat.Make("111", "222","444");
    }
    @Test(expected= CellPhoneFormat.LastMustBeFourDigit.class)
    public void if_last_is_greater_than_4_digit_it_throws_LastMustBeFourDigitException(){
        BoundedCellPhoneFormat.Make("111", "222","44444");
    }
    @Test
    public void getLastTest(){
        CellPhoneFormat cellphone = BoundedCellPhoneFormat.Make("111", "222","4444");
        Assert.assertEquals("4444", cellphone.getLast());
    }

}
