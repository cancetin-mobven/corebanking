package com.openpayd.corebanking.entity;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public AccountType accountType;

    public double balance;

    public BalanceStatus balanceStatus;

    @Transient
    public static double OVERDRAFT_BALANCE = 200;

    public Date createdAt;

    //changed to cascade.ALL by opening account also create user
    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    public Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BalanceStatus getBalanceStatus() {
        return balanceStatus;
    }

    public void setBalanceStatus(BalanceStatus balanceStatus) {
        this.balanceStatus = balanceStatus;
    }
}
