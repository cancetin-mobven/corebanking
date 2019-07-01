package com.openpayd.corebanking.entity;

public enum TransactionType {

    DEBIT(new Double(0)),
    CREDIT(new Double(0));

//    private int type;
    private double amount;

    TransactionType(Double amount) {
  //      this.type = type;
        this.amount = amount;
    }
/*
    public int getType() {
        return this.type;
    }
*/
    public double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
