package com.example.testspringdata.authExceptions;

public class InvalidPasswordException extends Exception {

    private final String message;

    public InvalidPasswordException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
