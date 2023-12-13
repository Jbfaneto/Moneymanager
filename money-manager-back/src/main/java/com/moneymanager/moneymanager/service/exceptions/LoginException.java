package com.moneymanager.moneymanager.service.exceptions;

public class LoginException extends RuntimeException{
    public LoginException(String message) {
        super(message);
    }
}
