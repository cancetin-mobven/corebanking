package com.openpayd.corebanking.entity;

public enum TransactionType {

    DEBIT(new Double(0)),
    CREDIT(new Double(0));

    private double amount;

    TransactionType(Double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
