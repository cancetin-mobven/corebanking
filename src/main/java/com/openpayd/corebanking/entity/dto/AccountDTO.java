package com.openpayd.corebanking.entity.dto;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.AccountType;
import com.openpayd.corebanking.entity.BalanceStatus;
import com.openpayd.corebanking.entity.Client;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
@ApiModel( value = "Account", description = "Account Resource Representation" )
public class AccountDTO {

    @ApiModelProperty( value = "Type of account consisting SAVINGS or CURRENT", required = true )
    public AccountType accountType;

    @ApiModelProperty( value = "balance of account", required = true )
    public double balance;

    @ApiModelProperty( value = "it defines balance status, it can be credit or debit", required = true )
    public BalanceStatus balanceStatus;

    public Date createdAt;

    @ApiModelProperty( value = "Client data of owner account", required = true )
    public Client client;

    public AccountDTO() {

    }

    public AccountDTO(Account account) {
        this.accountType = account.getAccountType(); //.getValue();
        this.balance = account.getBalance();
        this.balanceStatus = account.getBalanceStatus();
        this.createdAt = account.getCreatedAt();
        this.client = account.getClient();
    }

    public AccountDTO(AccountType accountType, double balance, BalanceStatus balanceStatus, Date createdAt, Client client) {
        this.accountType = accountType;
        this.balance = balance;
        this.balanceStatus = balanceStatus;
        this.createdAt = createdAt;
        this.client = client;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
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

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountType=" + accountType +
                ", balance=" + balance +
                ", balanceStatus=" + balanceStatus +
                ", createdAt=" + createdAt +
                ", client=" + client +
                '}';
    }
}
