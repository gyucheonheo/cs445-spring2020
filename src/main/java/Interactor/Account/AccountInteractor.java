package Interactor.Account;

import Entity.Boundary.Account.User.User;

import java.util.ArrayList;
import java.util.List;

public enum AccountInteractor {
    INSTANCE;
    private List<User> users = new ArrayList<>();

    public List<User> getUsers(){
        return users;
    }
}
