import Entity.Account.CellPhoneFormat.CellPhoneFormat;
import org.junit.Test;

public class CellPhoneFormatTest {
    @Test(expected=CellPhoneFormat.FirstMustBeDigit.class)
    public void if_first_is_not_digit_it_throws_FirstMustBeDigitException(){
        CellPhoneFormat cellphone = new CellPhoneFormat("31a","222","4444");
    }
    @Test(expected=CellPhoneFormat.FirstMustBeThreeDigit.class)
    public void if_first_is_less_than_3_digit_it_throws_First_MustBeThreeDigitException(){
       CellPhoneFormat cellphone = new CellPhoneFormat("1", "222","4444");
    }
    @Test(expected=CellPhoneFormat.FirstMustBeThreeDigit.class)
    public void if_first_is_greater_than_3_digit_it_throws_First_MustBeThreeDigitException(){
        CellPhoneFormat cellphone = new CellPhoneFormat("1111", "222","4444");
    }

    @Test(expected=CellPhoneFormat.MiddleMustBeDigit.class)
    public void if_middle_is_not_digit_it_throws_MiddleMustBeDigitException(){
        CellPhoneFormat cellphone = new CellPhoneFormat("111", "a22", "4444");
    }
    @Test(expected=CellPhoneFormat.MiddleMustBeThreeDigit.class)
    public void if_middle_is_less_than_3_digit_it_throws_MiddleMustBeThreeDigitException(){
        CellPhoneFormat cellphone = new CellPhoneFormat("111", "2","4444");
    }
    @Test(expected=CellPhoneFormat.MiddleMustBeThreeDigit.class)
    public void if_middle_is_greater_than_3_digit_it_throws_MiddleMustBeThreeDigitException(){
        CellPhoneFormat cellphone = new CellPhoneFormat("111", "2222","4444");
    }

    @Test(expected=CellPhoneFormat.LastMustBeDigit.class)
    public void if_middle_is_not_digit_it_throws_LastMustBeDigitException(){
        CellPhoneFormat cellphone = new CellPhoneFormat("111", "222", "4a4a");
    }
    @Test(expected=CellPhoneFormat.LastMustBeFourDigit.class)
    public void if_last_is_less_than_4_digit_it_throws_LastMustBeFourDigitException(){
        CellPhoneFormat cellphone = new CellPhoneFormat("111", "222","444");
    }
    @Test(expected=CellPhoneFormat.LastMustBeFourDigit.class)
    public void if_last_is_greater_than_4_digit_it_throws_LastMustBeFourDigitException(){
        CellPhoneFormat cellphone = new CellPhoneFormat("111", "222","44444");
    }


}
