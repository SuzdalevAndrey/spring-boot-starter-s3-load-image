package ru.andreyszdlv.springbootstarters3loadimage.exception;

public class NoSuchImageException extends RuntimeException {
    public NoSuchImageException(String message) {
        super(message);
    }
}
