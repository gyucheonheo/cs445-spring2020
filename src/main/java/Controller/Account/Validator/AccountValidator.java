package Controller.Account.Validator;

import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class AccountValidator {
    private static String emsg = "";

    public static boolean isValid(JsonObject account) {
        return  isFirstNameValid(account) &&
                isLastNameValid(account)&&
                isPhoneValid(account) &&
                isActiveValid(account);
    }

    private static boolean isFirstNameValid(JsonObject account){
        return  hasMemberField("first_name", account, "First name is null") &&
                isMemberEmpty("first_name", account, "First name is empty") &&
                isAllString("first_name", account, "The first name appears to be invalid.");
    }
    private static boolean isLastNameValid(JsonObject account){
        return hasMemberField("last_name", account, "Last name is null") &&
                isMemberEmpty("last_name", account, "Last name is empty") &&
                isAllString("last_name", account, "The last name appears to be invalid.");
    }
    private static boolean isPhoneValid(JsonObject account){
        return hasMemberField("phone", account, "phone is null") &&
               isPhoneNumberValid(account);
    }
        private static boolean isMemberEmpty(String member, JsonObject account, String err) {
            String field = account.get(member).getAsString();
            if (field.isEmpty()) {
                                     emsg = err;
                                     return false;
                                     }
            return true;
        }

        private static boolean isAllString(String member,JsonObject account, String err) {
            String field = account.get(member).getAsString();
            if(!field.matches("[a-zA-Z]+")){
                                                emsg = err;
                                                return false;
                                                }
            return true;
        }

        private static boolean hasMemberField(String member, JsonObject account, String err) {
                                                                                                 if (!account.has(member)) {
                                                                                                 emsg = err;
                                                                                                 return false;
                                                                                                 }
                                                                                                 return true;
                                                                                                 }

        private static boolean isPhoneNumberValid(JsonObject account) {
            String phone = account.get("phone").getAsString();
            List<String> numbers = Arrays.asList(phone.split("-"));
            return isPhoneNDigit("3", numbers.get(0)) &&
                    isPhoneNDigit("3", numbers.get(1)) &&
                    isPhoneNDigit("4", numbers.get(2));
        }
            private static boolean isPhoneNDigit(String n, String number){
                String pattern ="\\d{"+n+"}";
                if(!number.matches(pattern)){
                    emsg="Invalid phone number";
                    return false;
                }
                return true;
            }
    private static boolean isActiveValid(JsonObject account) {
        Boolean isActive = account.get("is_active").getAsBoolean();
        if(isActive != false){
            emsg = "Invalid value for is_active";
            return false;
        }
        return true;
    }

    public static boolean isActivateInfoValid(JsonObject account){
        return isFirstNameValid(account) &&
                isLastNameValid(account)&&
                isPhoneValid(account);
    }
    public static String getErrorMessage() {
        return emsg;
    }
}
