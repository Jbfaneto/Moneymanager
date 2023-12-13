package com.moneymanager.moneymanager.service.exceptions;

public class AuthenticationException  extends RuntimeException{
    public AuthenticationException(String message) {
        super(message);
    }
}
