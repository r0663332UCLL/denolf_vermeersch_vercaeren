package domain.db;

class DbException extends RuntimeException {
    DbException() {
        super();
    }

    DbException(String message, Throwable exception) {
        super(message, exception);
    }

    DbException(String message) {
        super(message);
    }

    DbException(Throwable exception) {
        super(exception);
    }
}
