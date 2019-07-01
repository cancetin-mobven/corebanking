package com.openpayd.corebanking.service.impl;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.AccountType;
import com.openpayd.corebanking.entity.BalanceStatus;
import com.openpayd.corebanking.entity.Client;
import com.openpayd.corebanking.entity.dto.AccountDTO;
import com.openpayd.corebanking.entity.dto.ClientDTO;
import com.openpayd.corebanking.repository.AccountRepository;
import com.openpayd.corebanking.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    AccountRepository accountRepository;

  //  @Autowired
   // ClientServiceImpl clientService;

    public List<AccountDTO> listAccounts() {
        return null;
    }


  //  @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void prepossess() {

    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        if(accountDTO.getBalanceStatus() == null ){
            accountDTO.setBalanceStatus(BalanceStatus.CREDIT);
        }
        Account account = accountRepository.save(convertToEntity(accountDTO));

        return new AccountDTO(account);
    }

    @Transactional
    public List<AccountDTO> findByClient(Client client) {

        List<Account> accountList = accountRepository.findByClient(client);

       return accountList.stream()
                .map( account-> new AccountDTO(account) ).collect(Collectors.toList());

        //return new AccountDTO(account);
    }

    @Override
    public Account findById(Long accountId) {

        Optional<Account> account = accountRepository.findById(accountId);
        /*
                .orElseThrow(
                        () -> new Exception("User not found with userId " + accountId));
        */

       if(account.isPresent())
            return account.get();
        else
            return null;
    }

    private Account convertToEntity(AccountDTO accountDTO){
        return modelMapper.map(accountDTO, Account.class);
    }

    @Override
   // @Transactional
    public Account debit(Double amount,Account account){
        if(account.getAccountType() == AccountType.SAVINGS){

            Double newBalance = null;
            if(account.getBalanceStatus() == BalanceStatus.CREDIT){
                newBalance = account.getBalance() + amount;
            }else {
                newBalance = account.getBalance() - amount;
                if( newBalance < 0){
                    newBalance = -1 * newBalance;
                    account.setBalanceStatus(BalanceStatus.CREDIT);
                }
            }

            account.setBalance(newBalance);
            account = accountRepository.save(account);

/*
                Double newBalance = account.getBalance() - amount;
            if(account.getBalanceStatus() == BalanceStatus.DEBIT && newBalance < 0){
                newBalance = -1 * newBalance;
                account.setBalanceStatus(BalanceStatus.CREDIT);
            }


            account.setBalance(newBalance);
            account = accountRepository.save(account);

           */
        }  else {

            if(account.getBalanceStatus() == BalanceStatus.CREDIT){
                Double newBalance = account.getBalance() + amount;

                if(newBalance < 0){
                    account.setBalanceStatus(BalanceStatus.DEBIT);
                    newBalance = -1 * newBalance ;
                }
                account.setBalance(newBalance);
            }else {
                Double newBalance = account.getBalance() - amount;
                if(newBalance < 0) {
                    account.setBalanceStatus(BalanceStatus.CREDIT);
                    newBalance = -1 * newBalance ;
                }
                account.setBalance(newBalance);
            }
            account = accountRepository.save(account);
        }
        return account;
    }

    @Override
   // @Transactional
    public Account credit(Double amount,Account account){

        if(account.getBalanceStatus() == BalanceStatus.CREDIT){

            Double newBalance = account.getBalance() - amount;

            if(newBalance < 0){
                account.setBalanceStatus(BalanceStatus.DEBIT);
                newBalance = -1 * newBalance ;
            }
            account.setBalance(newBalance);

        } else {

            Double newBalance = account.getBalance() + amount;

            if(newBalance < 0) {
                account.setBalanceStatus(BalanceStatus.CREDIT);
                newBalance = -1 * newBalance ;
            }
            account.setBalance(newBalance);

        }

        /*
        if(account.getAccountType() == AccountType.DEBIT){

            account.setBalance(account.getBalance()+amount);
            account = accountRepository.save(account);

        }  else {

            account.setBalance(account.getBalance()-amount);
            account = accountRepository.save(account);

        }*/
        account = accountRepository.save(account);

        return account;
    }




}
