package festivalbokaren;
/*
 * Custom exception class for invalid year of birth.
 * This exception is thrown when an invalid yearofbirth is provided.
 */
public class InvalidYearOfBirthException extends Exception {
    public InvalidYearOfBirthException(String message) {
        super(message);
    }
}