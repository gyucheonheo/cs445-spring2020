package Entity.Account.CellPhoneFormat;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.*;

public class CellPhoneFormat {
    final Map<String, String> VALUES_BY_NAME;

    public CellPhoneFormat(String first, String middle, String last){
        VALUES_BY_NAME = new HashMap<>();
        setElement("first", first, 3, new FirstMustBeThreeDigit(), new FirstMustBeDigit());
        setElement("middle", middle, 3, new MiddleMustBeThreeDigit(), new MiddleMustBeDigit());
        setElement("last", last, 4, new LastMustBeFourDigit(), new LastMustBeDigit());
    }
        private void setElement(String key, String value, int limit, RuntimeException e1, RuntimeException e2){
            if(value.length() != limit){
                throw e1;
            }
            for( char c : value.toCharArray()){
                if( !isDigit(c)){
                    throw e2;
                }
            }
            this.VALUES_BY_NAME.put(key, value);
        }
        public static class FirstMustBeThreeDigit extends RuntimeException{}
        public static class FirstMustBeDigit extends RuntimeException{}
        public static class MiddleMustBeThreeDigit extends RuntimeException{}
        public static class MiddleMustBeDigit extends RuntimeException{}
        public static class LastMustBeFourDigit extends RuntimeException{}
        public static class LastMustBeDigit extends RuntimeException{}
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

