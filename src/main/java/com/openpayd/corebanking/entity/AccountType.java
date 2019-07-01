package com.openpayd.corebanking.entity;

public enum AccountType {
 //   DEBIT(0),
  //  CREDIT(1);
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
