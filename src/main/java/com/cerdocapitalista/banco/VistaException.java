package com.cerdocapitalista.banco;

public class VistaException extends RuntimeException {
    public VistaException() {
        super();
    }

    public VistaException(String message) {
        super(message);
    }

    public VistaException(String message, Throwable cause) {
        super(message, cause);
    }

    public VistaException(Throwable cause) {
        super(cause);
    }

    public VistaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
