package com.openpayd.corebanking.entity;

public enum AccountType {

    CURRENT(0),
    SAVINGS(1);

    private int value;

    AccountType(int value){
        this.value=value;
    }

    public int getValue() {
        return this.value;
    }
}
