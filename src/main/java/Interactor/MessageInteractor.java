package Interactor;

import Boundary.MessageInteractorBoundary;
import Boundary.TripInteractorBoundary;
import Entity.Boundary.Message.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MessageInteractor implements MessageInteractorBoundary {
    INSTANCE;
    private static Map<String, List<Message>> msgs = new HashMap<>();
    private TripInteractorBoundary tb = TripInteractor.INSTANCE;

    public void sendMsgToRide(String rid, Message msg){
        tb.getTripById(rid);
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
}
