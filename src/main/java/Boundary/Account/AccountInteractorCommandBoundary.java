package Boundary.Account;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.User;

public interface AccountInteractorCommandBoundary {
    void registerUser(User u);
    void activateUser(String aid);
    void updateUser(String aid, String first, String last, CellPhoneFormat cellPhone, String url);
    void deleteUser(String aid);
}
