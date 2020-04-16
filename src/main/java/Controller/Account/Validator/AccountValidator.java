package Controller.Account.Validator;

import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class AccountValidator {
    private static String emsg = "";

    public static boolean isValid(JsonObject account) {
        String first_name = account.get("first_name").getAsString();
        String last_name = account.get("last_name").getAsString();
        String phone = account.get("phone").getAsString();
        Boolean isActive = account.get("is_active").getAsBoolean();

        if (first_name == null) {
            emsg = "First name is null";
            return false;
        }
        if (first_name.isEmpty()) {
            emsg = "First name is empty";
            return false;
        }
        for (char c : first_name.toCharArray()) {
            if (Character.isDigit(c)) {
                emsg = "The first name appears to be invalid.";
                return false;
            }
        }
        if (last_name == null) {
            emsg = "Last name is null";
            return false;
        }
        if (last_name.isEmpty()) {
            emsg = "Last name is empty";
            return false;
        }
        for (char c : last_name.toCharArray()) {
            if (Character.isDigit(c)) {
                emsg = "The last name appears to be invalid.";
                return false;
            }
        }
        if (phone == null) {
            emsg = "phone is null";
            return false;
        }
        List<String> numbers = Arrays.asList(phone.split("-"));
        if(!numbers.get(0).matches("\\d{3}") ||
                !numbers.get(1).matches("\\d{3}") ||
                !numbers.get(2).matches("\\d{4}")
        ){
            emsg = "Invalid phone number";
            return false;
        }

        if(isActive == true || isActive == null){
            emsg = "Invalid value for is_active";
            return false;
        }
        return true;
    }

    public static boolean isActivateInfoValid(JsonObject account){
        String first_name = account.get("first_name").getAsString();
        String last_name = account.get("last_name").getAsString();
        String phone = account.get("phone").getAsString();

        if (first_name == null) {
            emsg = "First name is null";
            return false;
        }
        if (first_name.isEmpty()) {
            emsg = "First name is empty";
            return false;
        }
        for (char c : first_name.toCharArray()) {
            if (Character.isDigit(c)) {
                emsg = "The first name appears to be invalid.";
                return false;
            }
        }
        if (last_name == null) {
            emsg = "Last name is null";
            return false;
        }
        if (last_name.isEmpty()) {
            emsg = "Last name is empty";
            return false;
        }
        for (char c : last_name.toCharArray()) {
            if (Character.isDigit(c)) {
                emsg = "The last name appears to be invalid.";
                return false;
            }
        }
        if (phone == null) {
            emsg = "phone is null";
            return false;
        }
        List<String> numbers = Arrays.asList(phone.split("-"));
        if(!numbers.get(0).matches("\\d{3}") ||
                !numbers.get(1).matches("\\d{3}") ||
                !numbers.get(2).matches("\\d{4}")
        ){
            emsg = "Invalid phone number";
            return false;
        }
        return true;
    }
    public static String getErrorMessage() {
        return emsg;
    }
}
