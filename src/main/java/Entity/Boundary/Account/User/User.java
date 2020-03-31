package Entity.Boundary.Account.User;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.RideInformation.RideInformation;

public interface User {
    String getAid();
    boolean getIsActive();

    void setIsActive(boolean b);
    void setFirstName(String first);
    void setLastName(String last);
    void setCellPhoneFormat(CellPhoneFormat cellphone);
    void setPicture(String picture);

    String getFirstName();

    String getLastName();

    CellPhoneFormat getCellPhoneFormat();


    class FirstNameBeingEmptyIsInvalid extends RuntimeException { }
    class LastNameBeingEmptyIsInvalid extends RuntimeException { }
}
