package Interactor.Account;

import Boundary.Account.AccountInteractorCommandBoundary;
import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.User.BoundedUser;

import java.util.List;

public enum AccountInteractorCommand implements AccountInteractorCommandBoundary {
    INSTANCE;
    private List<User> users = AccountInteractor.INSTANCE.getUsers();

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
            User u = wrappedGetUserById(aid);
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
        private User wrappedGetUserById(String aid){
            for(User u : users){
                if(u.getAid().equals(aid)){
                    return u;
                }
            }
            return BoundedUser.Make();
        }
}
