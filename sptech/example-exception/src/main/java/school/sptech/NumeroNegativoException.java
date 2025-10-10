package school.sptech;
public class NumeroNegativoException extends Exception{

    public NumeroNegativoException() {
    }

    public NumeroNegativoException(String message) {
        super(message);
    }

    public NumeroNegativoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumeroNegativoException(Throwable cause) {
        super(cause);
    }

    public NumeroNegativoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
