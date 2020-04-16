package Interactor.Message;

import Boundary.Message.MessageInteractorCommandBoundary;
import Entity.Boundary.Message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum MessageInteractorCommand implements MessageInteractorCommandBoundary {
    INSTANCE;
    private static Map<String, List<Message>> msgs = MessageInteractor.INSTANCE.getMessages();
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
}
