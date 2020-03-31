package Interactor;

import Boundary.Account.User.AccountInteractorBoundary;
import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.User.BoundedUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AccountInteractor implements AccountInteractorBoundary {
    INSTANCE;
    private Map<String, User> users = new HashMap<>();

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        for(Map.Entry<String, User> entry : users.entrySet()){
            allUsers.add(entry.getValue());
        }
        return allUsers;
    }

    public User createUser(String first, String last, CellPhoneFormat cellPhone, String picture){
        return BoundedUser.Make(first, last, cellPhone, picture);
    }

    public void registerUser(User u) {
        users.put(u.getAid(), u);
    }

    public void activateUser(String aid){
        if(!users.containsKey(aid)){
            throw new UserNotFoundException();
        }
        User u = users.get(aid);
        u.setIsActive(true);
    }

    public void updateUser(String aid, String first, String last, CellPhoneFormat cellPhone, String url) {
        if(!users.containsKey(aid)){
            throw new UserNotFoundException();
        }
        User u = users.get(aid);
        u.setFirstName(first);
        u.setLastName(last);
        u.setCellPhoneFormat(cellPhone);
        u.setPicture(url);
    }

    public void deleteUser(String aid) {
        if(!users.containsKey(aid)){
           throw new UserNotFoundException();
        }
        users.remove(aid);
    }

    public User getUserById(String aid) {
        if(!users.containsKey(aid)){
            throw new UserNotFoundException();
        }
        return users.get(aid);
    }

    public List<User> searchUserByKeyword(String s) {
        return null;
    }

    public void rateUser(String tid, Rate r) {

    }
}
