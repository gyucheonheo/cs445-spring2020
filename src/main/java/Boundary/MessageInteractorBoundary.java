package Boundary;

import Entity.Boundary.Message.Message;

import java.util.List;

public interface MessageInteractorBoundary {
    void sendMsgToRide(String rid, Message msg);
    List<Message> getAllMessagesByRid(String rid);

}
