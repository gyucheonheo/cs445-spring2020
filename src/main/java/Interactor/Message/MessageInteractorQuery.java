package Interactor.Message;

import Boundary.Message.MessageInteractorQueryBoundary;
import Entity.Boundary.Message.Message;
import Entity.Bounded.Message.BoundedMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum MessageInteractorQuery implements MessageInteractorQueryBoundary {
    INSTANCE;
    private static Map<String, List<Message>> msgs = MessageInteractor.INSTANCE.getMessages();

    public Message createMessage(String aid, String msg) {
        return BoundedMessage.Make(aid, msg);
    }

    public List<Message> getAllMessagesByRid(String rid) {
        if(!msgs.containsKey(rid)){
            List<Message> msgList = new ArrayList<>();
            msgs.put(rid, msgList);
        }
        return msgs.get(rid);
    }

}
