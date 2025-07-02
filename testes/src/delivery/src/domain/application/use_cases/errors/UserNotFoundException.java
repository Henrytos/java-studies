package domain.application.use_cases.errors;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("not found user");
    }
}
