package Entity.Bounded.Message;

public class NullBoundedMessage extends BoundedMessage {
    @Override
    public boolean isNil(){
        return true;
    }
}
