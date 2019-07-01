package com.openpayd.corebanking.entity;

public enum TransferType {

    DEBIT("debit"),
    CREDIT("credit");

    private String type;

    TransferType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
