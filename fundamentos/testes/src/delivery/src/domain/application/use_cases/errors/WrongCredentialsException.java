package testes.src.delivery.src.domain.application.use_cases.errors;

public class WrongCredentialsException extends Exception{
    public WrongCredentialsException(){
        super("wrong credentials in validation");
    }
}
