package ru.andreyszdlv.springbootstarters3loadimage.exception;

public class EmptyImageException extends RuntimeException {
    public EmptyImageException(String message) {
        super(message);
    }
}
