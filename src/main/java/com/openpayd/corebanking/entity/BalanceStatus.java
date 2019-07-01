package com.openpayd.corebanking.entity;

public enum BalanceStatus {
    DEBIT(1),
    CREDIT(0);

    private int value;

    BalanceStatus(int value){
        this.value=value;
    }

    public int getValue() {
        return this.value;
    }
}
