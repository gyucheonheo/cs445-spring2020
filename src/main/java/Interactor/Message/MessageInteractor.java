package Interactor.Message;

import Boundary.MessageInteractorBoundary;
import Boundary.TripInteractorBoundary;
import Entity.Boundary.Message.Message;
import Entity.Bounded.Message.BoundedMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MessageInteractor implements MessageInteractorBoundary {
    INSTANCE;
    private static Map<String, List<Message>> msgs = new HashMap<>();

    public Message createMessage(String aid, String msg) {
        return BoundedMessage.Make(aid, msg);
    }

    public void sendMsgToRide(String rid, Message msg){
        List<Message> msgList;
        if(!msgs.containsKey(rid)){
           msgList = new ArrayList<>();
        } else {
            msgList = msgs.get(rid);
        }
        msgList.add(msg);
        msgs.put(rid, msgList);
    }

    public List<Message> getAllMessagesByRid(String rid) {
        if(!msgs.containsKey(rid)){
            List<Message> msgList = new ArrayList<>();
            msgs.put(rid, msgList);
        }
        return msgs.get(rid);
    }

    public Map<String, List<Message>> getMessages() {
        return msgs;
    }
}
