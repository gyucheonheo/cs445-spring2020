package Entity.Boundary.Message;

public interface Message {
    boolean isNil();

    String getMid();

    String getSentByAid();

    String getDate();

    String getBody();
}
