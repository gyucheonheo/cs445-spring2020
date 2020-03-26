package Entity.Boundary.Account.User.RideInformation.Rate;

public interface Rate {
    String getRid();
    String getSentBy();
    String getFirstName();
    String getDate();
    int getRating();
    String getComment();

    class SentByNotAllowedEmptyException extends RuntimeException {
    }

    class SentByAllowOnlyNumberException extends RuntimeException {
    }

    class FirstNameByNotAllowedEmptyException extends RuntimeException {
    }

    class LessThanOneRatingNotAllowedException extends RuntimeException {
    }

    class GreaterThanFiveRatingNotAllowedException extends RuntimeException {
    }

    class CommentNotAllowedEmptyException extends RuntimeException{
    }
}

