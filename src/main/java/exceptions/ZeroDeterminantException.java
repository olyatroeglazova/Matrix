package exceptions;

public class ZeroDeterminantException extends MatrixException {
    public ZeroDeterminantException() {
    }

    public ZeroDeterminantException(final String message) {
        super(message);
    }

    public ZeroDeterminantException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ZeroDeterminantException(final Throwable cause) {
        super(cause);
    }

}
