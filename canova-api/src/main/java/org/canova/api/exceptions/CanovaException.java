package org.canova.api.exceptions;

/**
 * Canova exception
 * @author Adam Gibson
 */
public class CanovaException extends Exception {
    public CanovaException() {
        super();
    }

    public CanovaException(String message) {
        super(message);
    }

    public CanovaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanovaException(Throwable cause) {
        super(cause);
    }

    protected CanovaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
