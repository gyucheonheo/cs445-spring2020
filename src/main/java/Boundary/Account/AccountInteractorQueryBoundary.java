package Boundary.Account;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.User;

import java.util.List;

public interface AccountInteractorQueryBoundary {
    List<User> getAllUsers(String key);
    User createUser(String first, String last, CellPhoneFormat cellPhone , String picture);
    User getUserById(String aid);
}
