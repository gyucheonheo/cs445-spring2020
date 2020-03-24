package Entity.Account.CellPhoneFormat;

import static java.lang.Character.*;

public class CellPhoneFormat {
    private String first;
    private String middle;
    private String last;

    public CellPhoneFormat(String first, String middle, String last){
        setFirst(first);
        setMiddle(middle);
        setLast(last);
    }
        private void setFirst(String first){
            if(first.length() != 3){
                throw new FirstMustBeThreeDigit();
            }
            for( char c : first.toCharArray()){
               if( !isDigit(c) ){
                   throw new FirstMustBeDigit();
               }

            }
        }
        private void setMiddle(String middle){
            if(middle.length() != 3){
                throw new MiddleMustBeThreeDigit();
            }
            for( char c : middle.toCharArray()){
                if( !isDigit(c) ){
                    throw new MiddleMustBeDigit();
                }

            }
        }
        private void setLast(String last){
            if(last.length() != 4){
                throw new LastMustBeFourDigit();
            }
            for( char c : last.toCharArray()){
                if( !isDigit(c) ){
                    throw new LastMustBeDigit();
                }

            }
        }
        public static class FirstMustBeThreeDigit extends RuntimeException{}
        public static class FirstMustBeDigit extends RuntimeException{}
        public static class MiddleMustBeThreeDigit extends RuntimeException{}
        public static class MiddleMustBeDigit extends RuntimeException{}
        public static class LastMustBeFourDigit extends RuntimeException{}
        public static class LastMustBeDigit extends RuntimeException{}
}

