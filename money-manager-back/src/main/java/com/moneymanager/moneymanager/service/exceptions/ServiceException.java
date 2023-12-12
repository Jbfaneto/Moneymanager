package com.moneymanager.moneymanager.service.exceptions;

public class ServiceException extends RuntimeException{
    public ServiceException(String message) {
        super(message);
    }
}
