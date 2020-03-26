package Entity.Boundary.Account.CellPhoneFormat;

public interface CellPhoneFormat {
    String getFirst();
    String getMiddle();
    String getLast();

    class FirstMustBeThreeDigit extends RuntimeException{}
    class FirstMustBeDigit extends RuntimeException{}
    class MiddleMustBeThreeDigit extends RuntimeException{}
    class MiddleMustBeDigit extends RuntimeException{}
    class LastMustBeFourDigit extends RuntimeException{}
    class LastMustBeDigit extends RuntimeException{}
}
