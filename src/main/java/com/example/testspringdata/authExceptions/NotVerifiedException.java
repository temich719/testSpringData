package com.example.testspringdata.authExceptions;

public class NotVerifiedException extends Exception {

    private final String message;

    public NotVerifiedException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
