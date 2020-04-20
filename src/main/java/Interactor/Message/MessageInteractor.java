package Interactor.Message;

import Entity.Boundary.Message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MessageInteractor {
    INSTANCE;
    private static Map<String, List<Message>> msgs = new HashMap<>();

    public Map<String, List<Message>> getMessages() {
        return msgs;
    }
}
