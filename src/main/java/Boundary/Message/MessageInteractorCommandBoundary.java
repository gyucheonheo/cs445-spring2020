package Boundary.Message;

import Entity.Boundary.Message.Message;

public interface MessageInteractorCommandBoundary {
    void sendMsgToRide(String rid, Message msg);
}
