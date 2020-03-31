package Entity.Boundary.Message;

public interface Message {
    boolean isNil();
    class SentByNotAllowedEmptyException extends RuntimeException{}
    class BodyNotAllowedEmptyException extends RuntimeException{}
}
