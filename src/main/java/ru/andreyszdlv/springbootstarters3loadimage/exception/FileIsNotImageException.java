package ru.andreyszdlv.springbootstarters3loadimage.exception;

public class FileIsNotImageException extends RuntimeException {
    public FileIsNotImageException(String message) {
        super(message);
    }
}
