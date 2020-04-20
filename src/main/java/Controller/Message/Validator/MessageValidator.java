package Controller.Message.Validator;

import Boundary.Account.AccountInteractorQueryBoundary;
import Entity.Boundary.Account.User.User;
import Interactor.Account.AccountInteractorQuery;
import com.google.gson.JsonObject;

public class MessageValidator {
    private static String emsg="";
    public static boolean isValid(JsonObject message){
        return hasAid(message) && isAidEmpty(message) && isAccountActive(message) && hasMsg(message) && isMsgBodyEmpty(message);
    }

    private static boolean hasAid(JsonObject message) {
        if(!message.has("aid")){
            emsg = "aid is null";
            return false;
        }
        return true;
    }

    private static boolean isAidEmpty(JsonObject message) {
        if(message.get("aid").getAsString().isEmpty()){
            emsg = "aid is empty";
            return false;
        }
        return true;
    }

    private static boolean isAccountActive(JsonObject message) {
        AccountInteractorQueryBoundary ai = AccountInteractorQuery.INSTANCE;
        User u = ai.getUserById(message.get("aid").getAsString());
        if(!u.getIsActive()){
            emsg = "Account is not active";
            return false;
        }
        return true;
    }

    private static boolean hasMsg(JsonObject message) {
        if(!message.has("msg")){
            emsg = "msg is null";
            return false;
        }
        return true;
    }

    private static boolean isMsgBodyEmpty(JsonObject message) {
        if(message.get("msg").getAsString().isEmpty()){
            emsg = "msg is empty";
            return false;
        }
        return true;
    }

    public static String getErrorMessage(){
        return emsg;
    }
}
