package Controller.ErrorHandler;

import com.google.gson.JsonObject;

public class ErrorMessage {
    public JsonObject createError(String detail, int status, String instance){
        JsonObject errorMessage = new JsonObject();
        errorMessage.addProperty("type", "http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation");
        errorMessage.addProperty("title", "Your request data didn't pass validation");
        errorMessage.addProperty("detail", detail);
        errorMessage.addProperty("status", status);
        errorMessage.addProperty("instance", instance);
        return errorMessage;
    }
}
