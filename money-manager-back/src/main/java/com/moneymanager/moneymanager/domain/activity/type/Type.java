package com.moneymanager.moneymanager.domain.activity.type;

public enum Type {
    REVENUE("revenue"),
    EXPENSE("expense");
    private String value;
    Type(final String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
