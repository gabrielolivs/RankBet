package br.com.rankbet.exception;

public class BusinessException extends Exception {
    private static final long serialVersionUID = -4526390974286140492L;

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
