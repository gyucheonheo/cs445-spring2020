package Boundary.Account.User;

import Entity.Account.User.User;
import Entity.Note.Note;
import Entity.Rate.Rate;

import java.util.List;

public interface UserBoundary {
    List<User> getUsers();
    List<Note> getNotes();
    List<Rate> getRates(String id);
    void createUser();
    void activateUser();
    void updateUser();
    void deleteUser();
    void rateUser(String id, int scale);
    void makeComment(String id, Rate rate);

}
