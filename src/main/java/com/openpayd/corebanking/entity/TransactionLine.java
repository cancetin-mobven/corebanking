package com.openpayd.corebanking.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TransactionLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public TransactionType debit = TransactionType.DEBIT;
    public TransactionType credit = TransactionType.CREDIT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    public Account account;

    public Date transactionDate = new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransactionType getDebit() {
        return debit;
    }

    public void setDebit(TransactionType debit) {
        this.debit = debit;
    }

    public TransactionType getCredit() {
        return credit;
    }

    public void setCredit(TransactionType credit) {
        this.credit = credit;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
