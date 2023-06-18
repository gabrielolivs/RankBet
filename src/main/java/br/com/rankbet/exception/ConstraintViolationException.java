package br.com.rankbet.exception;

public class ConstraintViolationException extends Exception {
    private static final long serialVersionUID = -4526390974286140492L;

    public ConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
