package Entity.Boundary.Account.User;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;

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

    String getPicture();

    CellPhoneFormat getCellPhoneFormat();

    boolean isNil();
}
