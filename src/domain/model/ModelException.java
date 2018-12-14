package domain.model;

public class ModelException extends RuntimeException {
    ModelException() {
        super();
    }

    ModelException(String message, Throwable exception) {
        super(message, exception);
    }

    public ModelException(String message) {
        super(message);
    }

    ModelException(Throwable exception) {
        super(exception);
    }
}
