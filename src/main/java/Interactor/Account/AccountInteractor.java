package Interactor.Account;

import Boundary.AccountInteractorBoundary;
import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.User.BoundedUser;

import java.util.ArrayList;
import java.util.List;

public enum AccountInteractor implements AccountInteractorBoundary {
    INSTANCE;
    private List<User> users = new ArrayList<>();

    public List<User> getUsers(){
        return users;
    }
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

    public void registerUser(User u) {
        this.wrappedRegisterUser(u);
    }
        private void wrappedRegisterUser(User u){
            users.add(u);
        }

    public void activateUser(String aid){
        wrappedActivateuser(aid);
    }
        private void wrappedActivateuser(String aid){
            User u = this.wrappedGetUserById(aid);
            u.setIsActive(true);
        }
    public void updateUser(String aid, String first, String last, CellPhoneFormat cellPhone, String url) {
        this.wrappedUpdateUser(aid, first, last, cellPhone, url);
    }
        private void wrappedUpdateUser(String aid, String first, String last, CellPhoneFormat cellPhone, String url){
            User u = this.wrappedGetUserById(aid);
            u.setFirstName(first);
            u.setLastName(last);
            u.setCellPhoneFormat(cellPhone);
            u.setPicture(url);
        }

    public void deleteUser(String aid) {
        this.wrappedDeleteUser(aid);
    }
        private void wrappedDeleteUser(String aid){
            User u = this.wrappedGetUserById(aid);
            users.remove(u);
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
