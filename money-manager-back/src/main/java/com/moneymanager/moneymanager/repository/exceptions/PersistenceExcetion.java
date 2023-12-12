package com.moneymanager.moneymanager.repository.exceptions;

public class PersistenceExcetion extends RuntimeException{
    public PersistenceExcetion(String message) {
        super(message);
    }
}
