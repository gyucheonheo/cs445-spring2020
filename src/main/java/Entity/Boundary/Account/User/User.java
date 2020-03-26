package Entity.Boundary.Account.User;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.RideInformation.RideInformation;

public interface User {
    String getAid();
    boolean getIsActive();
    RideInformation getAsDriver();
    RideInformation getAsRider();
    class FirstNameBeingEmptyIsInvalid extends RuntimeException { }
    class LastNameBeingEmptyIsInvalid extends RuntimeException { }
}
