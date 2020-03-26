package Entity.Bounded.Message;

import Entity.Boundary.Message.Message;

public class NullBoundedMessage implements Message {
    @Override
    public boolean isNil(){
        return true;
    }
}
