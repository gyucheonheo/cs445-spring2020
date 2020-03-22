package Boundary.User;

public interface UserBoundary {
    List<Note> getNotes();
    List<Comment> getComments(String id);
}
