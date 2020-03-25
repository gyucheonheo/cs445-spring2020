package Entity.Message;

import Lib.DateTimeWithPattern;
import Lib.UniqueId;

public class Message {
    private String mid;
    private String sentBy;
    private String date;
    private String body;
    public Message(){

    }
    public Message(String sentBy, String body){
        this.mid = UniqueId.getUniqueID();
        date = DateTimeWithPattern.getDate("DD-MMM-YYYY , hh:mm:ss");
        this.sentBy = sentBy;
        this.body = body;
    }

    public boolean isNil(){
        return false;
    }
}
