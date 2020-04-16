package Interactor.Account;

import Boundary.Account.AccountInteractorQueryBoundary;
import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.User.BoundedUser;

import java.util.ArrayList;
import java.util.List;

public enum AccountInteractorQuery implements AccountInteractorQueryBoundary {
    INSTANCE;
    private List<User> users = AccountInteractor.INSTANCE.getUsers();

    public List<User> getAllUsers(String key) {
        return wrappedGetAllUsers(key);
    }
    private List<User> wrappedGetAllUsers(String key){
        if ( key == null || key.isEmpty() ) {
            return this.users;
        } else {
            List<User> result = new ArrayList<>();
            for(User u : users){
                String lowerKey = key.toLowerCase();
                if(u.getAid().toLowerCase().contains(lowerKey) ||
                        u.getLastName().toLowerCase().contains(lowerKey) ||
                        u.getFirstName().toLowerCase().contains(lowerKey) ||
                        u.getCellPhoneFormat().getFirst().toLowerCase().contains(lowerKey)||
                        u.getCellPhoneFormat().getLast().toLowerCase().contains(lowerKey) ||
                        u.getCellPhoneFormat().getMiddle().toLowerCase().contains(lowerKey)
                ){
                    result.add(u);
                }
            }
            return result;
        }
    }

    public User createUser(String first, String last, CellPhoneFormat cellPhone, String picture){
        return BoundedUser.Make(first, last, cellPhone, picture);
    }

    public User getUserById(String aid) {
        return wrappedGetUserById(aid);
    }

    public List<User> searchUserByKeyword(String s) {
        return null;
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
