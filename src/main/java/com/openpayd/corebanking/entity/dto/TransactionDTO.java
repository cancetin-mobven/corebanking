package com.openpayd.corebanking.entity.dto;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.Transaction;

import java.util.Date;

public class TransactionDTO {

    public long id;

    Account debitAccount;
    Account creditAccount;
    double amount;
    String message;
    Date transactionDate;

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TransactionDTO() {

    }

    public TransactionDTO(Transaction transaction) {
        this.debitAccount = transaction.getDebitAccount();//.getValue();
        this.creditAccount = transaction.getCreditAccount();
        this.amount = transaction.getAmount();
        this.message = transaction.getMessage();
        this.transactionDate = transaction.getTransactionDate();
    }
}
