package domain.model;

class ModelException extends RuntimeException {
    ModelException() {
        super();
    }

    ModelException(String message, Throwable exception) {
        super(message, exception);
    }

    ModelException(String message) {
        super(message);
    }

    ModelException(Throwable exception) {
        super(exception);
    }
}
