package com.openpayd.corebanking.service;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.Client;
import com.openpayd.corebanking.entity.dto.AccountDTO;

import java.util.List;

public interface IAccountService {

    AccountDTO createAccount(AccountDTO accountDTO);

    Account findById(Long accountId);

    List<AccountDTO> findByClient(Client client) ;

    Account debit(Double amount, Account account);

    Account credit(Double amount, Account account);

}
