package Boundary.Message;

import Entity.Boundary.Message.Message;

import java.util.List;

public interface MessageInteractorQueryBoundary {
    List<Message> getAllMessagesByRid(String rid);
    Message createMessage(String aid, String msg);
}
