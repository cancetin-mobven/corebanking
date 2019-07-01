package com.openpayd.corebanking.entity.dto;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.Transfer;
import com.openpayd.corebanking.entity.TransferType;

public class TransferDTO {

    public long id;

    public TransferType transferType;

    public Double amount;

    public Account account;

    public TransferDTO() {

    }

    public TransferDTO(Transfer transfer) {
        this.id = transfer.getId();
        this.account =transfer.getAccount();
        this.amount = transfer.getAmount();
        this.transferType =transfer.getTransferType();
    }


    public TransferDTO(Account account, Double amount, TransferType transferType) {
        this.account = account;
        this.amount = amount;
        this.transferType = transferType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
