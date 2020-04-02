package Entity.Bounded.Message;

import Entity.Boundary.Message.Message;
import Lib.DateTimeWithPattern;
import Lib.UniqueId;

public class BoundedMessage implements Message {
    private String mid;
    private String sentBy;
    private String date;
    private String body;

    public static Message Make(String sentBy, String body){
        return new BoundedMessage(sentBy, body);
    }
        private BoundedMessage(String sentBy, String body){
            this.mid = UniqueId.getUniqueID();
            date = DateTimeWithPattern.getDate("DD-MMM-YYYY , hh:mm:ss");
            setBody(body);
            setSentBy(sentBy);
        }
            private void setSentBy(String sentBy){
                this.sentBy = sentBy;
            }
            private void setBody(String body){
                this.body = body;
            }
    public static Message Make(){
        return new NullBoundedMessage();
    }

    public boolean isNil(){
        return false;
    }

}
