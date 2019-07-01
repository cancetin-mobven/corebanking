package com.openpayd.corebanking.controller;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.AccountType;
import com.openpayd.corebanking.entity.Transfer;
import com.openpayd.corebanking.entity.dto.AccountDTO;
import com.openpayd.corebanking.entity.dto.AddressDTO;
import com.openpayd.corebanking.entity.dto.ClientDTO;
import com.openpayd.corebanking.entity.dto.TransactionDTO;
import com.openpayd.corebanking.service.IAccountService;
import com.openpayd.corebanking.service.ITransactionService;
import com.openpayd.corebanking.service.ITransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    IAccountService accountService;

    @Autowired
    ITransactionService transactionService;

    @Autowired
    ITransferService transferService;

    HttpStatus returnCode = HttpStatus.OK;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TransactionDTO> doTransfer(@RequestBody TransactionDTO transactionDTO, HttpServletRequest request) {

        Account debitAccount = accountService.findById(transactionDTO.getDebitAccount().getId());
        if(debitAccount == null){
            return new ResponseEntity<TransactionDTO>(transactionDTO, HttpStatus.NOT_FOUND);
        }

        transactionDTO.setDebitAccount(debitAccount);
        Account creditAccount = accountService.findById(transactionDTO.getCreditAccount().getId());
        if(creditAccount == null){
            return new ResponseEntity<TransactionDTO>(transactionDTO, HttpStatus.NOT_FOUND);
        }

        transactionDTO.setCreditAccount(creditAccount);
        //Account creditAccount = transactionDTO.getCreditAccount();

        return new ResponseEntity<TransactionDTO>(transactionService.createTransaction(transactionDTO), HttpStatus.CREATED);

        //  return null;
    }
   /*     if(transactionDTO.getDebitAccount().getBalance() < transactionDTO.getAmount()){
            return new ResponseEntity<>(transactionDTO, HttpStatus.BAD_REQUEST);
        }
*/


     /*   System.out.println(accountDTO.toString());
        AccountDTO account = null ;
        try{
            account = accountService.createAccount(accountDTO);
        }catch (Exception e){

            System.out.println(e);
        }
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    */

/*
   // @Async
    public Future<String> asyncMethodWithReturnType() {
        System.out.println("Execute method asynchronously - "
                + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<String>("hello world !!!!");
        } catch (InterruptedException e) {
            //
        }
        return null;
    }*/



    @RequestMapping(value = "/transactionsByAccount/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Transfer>> listTransactionByAccount(@PathVariable("id") Long accountId, HttpServletRequest request){

        List<Transfer> transfers = null;
        try {
            Account account = accountService.findById(accountId);
            transfers = transferService.findByAccount(account);
            returnCode = HttpStatus.OK;
        } catch (Exception e){
            logger.debug(e.getMessage());
            returnCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if(transfers == null || transfers.size() == 0){
            return new ResponseEntity<List<Transfer>>(transfers, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<Transfer>>(transfers, returnCode);
        }
    }


}
