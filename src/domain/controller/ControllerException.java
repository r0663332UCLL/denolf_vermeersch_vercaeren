package domain.controller;

public class ControllerException extends RuntimeException {
    ControllerException() {
        super();
    }

    ControllerException(String message, Throwable exception) {
        super(message, exception);
    }

    public ControllerException(String message) {
        super(message);
    }

    ControllerException(Throwable exception) {
        super(exception);
    }
}
