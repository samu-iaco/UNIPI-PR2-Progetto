package com.company;

public class NotExistsException extends Exception {
    public NotExistsException() {
        super();
    }

    public NotExistsException(String message) {
        super(message);
    }
}
