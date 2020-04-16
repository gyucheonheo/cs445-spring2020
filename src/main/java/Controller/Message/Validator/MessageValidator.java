package Controller.Message.Validator;

import Boundary.Account.AccountInteractorQueryBoundary;
import Entity.Boundary.Account.User.User;
import Interactor.Account.AccountInteractorQuery;
import com.google.gson.JsonObject;

public class MessageValidator {
    private static String emsg="";
    public static boolean isValid(JsonObject message){
        if(message.get("aid").getAsString() == null){
            emsg = "aid is null";
            return false;
        }
        if(message.get("aid").getAsString().isEmpty()){
            emsg = "aid is empty";
            return false;
        }
        AccountInteractorQueryBoundary ai = AccountInteractorQuery.INSTANCE;
        User u = ai.getUserById(message.get("aid").getAsString());
        if(!u.getIsActive()){
            emsg = "Account is not active";
            return false;
        }
        if(message.get("msg").getAsString() == null){
            emsg = "msg is null";
            return false;
        }
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
