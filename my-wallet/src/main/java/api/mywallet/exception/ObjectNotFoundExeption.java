package api.mywallet.exception;

public class ObjectNotFoundExeption extends RuntimeException {

    public ObjectNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }
    public ObjectNotFoundExeption(String message) {
        super(message);
    }
}
