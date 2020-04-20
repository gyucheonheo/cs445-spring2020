package Entity.Bounded.Account.CellPhoneFormat;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.*;

public class BoundedCellPhoneFormat implements CellPhoneFormat {
    private final Map<String, String> VALUES_BY_NAME;

    public static CellPhoneFormat Make(String first, String middle, String last){
       return new BoundedCellPhoneFormat(first, middle, last);
    }
        private BoundedCellPhoneFormat(String first, String middle, String last){
            VALUES_BY_NAME = new HashMap<>();
            setElement("first", first);
            setElement("middle", middle);
            setElement("last", last);
        }
            private void setElement(String key, String value){
                this.VALUES_BY_NAME.put(key, value);
             }
    public String getFirst(){
        return VALUES_BY_NAME.get("first");
    }
    public String getMiddle(){
        return VALUES_BY_NAME.get("middle");
    }
    public String getLast(){
        return VALUES_BY_NAME.get("last");
    }
}

