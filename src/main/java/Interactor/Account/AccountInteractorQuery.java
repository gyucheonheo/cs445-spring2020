package Interactor.Account;

import Boundary.Account.AccountInteractorQueryBoundary;
import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.User.BoundedUser;

import java.util.List;
import java.util.stream.Collectors;

public enum AccountInteractorQuery implements AccountInteractorQueryBoundary {
    INSTANCE;
    private List<User> users = AccountInteractor.INSTANCE.getUsers();

    public List<User> getAllUsers(String key) {
        return wrappedGetAllUsers(key);
    }
    private List<User> wrappedGetAllUsers(String key){
        List<User> result;
        String lowerKey = convertToRegex(key);
        result = users.stream()
                .filter( u ->
                u.getAid().toLowerCase().matches(lowerKey) ||
                u.getLastName().toLowerCase().matches(lowerKey) ||
                u.getFirstName().toLowerCase().matches(lowerKey) ||
                u.getCellPhoneFormat().getFirst().toLowerCase().matches(lowerKey)||
                u.getCellPhoneFormat().getLast().toLowerCase().matches(lowerKey) ||
                u.getCellPhoneFormat().getMiddle().toLowerCase().matches(lowerKey)
                ).collect(Collectors.toList());
            return result;
    }
    private String convertToRegex(String data) {
        if (data == null || data.isEmpty()) {
            return "[a-zA-Z0-9\\- ]*";
        }
        return ".*"+data.toLowerCase()+".*";
    }
    public User createUser(String first, String last, CellPhoneFormat cellPhone, String picture){
        return BoundedUser.Make(first, last, cellPhone, picture);
    }

    public User getUserById(String aid) {
        return wrappedGetUserById(aid);
    }

        private User wrappedGetUserById(String aid){
            for(User u : users){
                if(u.getAid().equals(aid)){
                    return u;
                }
            }
            return BoundedUser.Make();
        }
}
