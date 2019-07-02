package com.openpayd.corebanking.controller;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.Client;
import com.openpayd.corebanking.entity.Transaction;
import com.openpayd.corebanking.entity.dto.TransactionDTO;
import com.openpayd.corebanking.service.IAccountService;
import com.openpayd.corebanking.service.ITransactionService;
import com.openpayd.corebanking.service.ITransferService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class TransactionControllerTest {

    @InjectMocks
    TransactionController transactionController;

    @Mock
    IAccountService accountService;

    @Mock
    ITransactionService transactionService;

    @Mock
    ITransferService transferService;


    HttpStatus returnCode = HttpStatus.OK;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void shouldGetClient_NotFoundHttpCode() {
        List<Client> clients = new ArrayList<Client>();
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        Long id = 0L;
        TransactionDTO transactionDTO = new TransactionDTO();
        Account debitAccount = new Account();
        debitAccount.setId(1L);
        transactionDTO.setDebitAccount(debitAccount);
        when(accountService.findById(any(Long.class))).thenReturn(null);
//      accountService.findById(transactionDTO.getDebitAccount().getId());
        ResponseEntity<TransactionDTO> ld= transactionController.doTransfer(transactionDTO, mockedRequest);

        assertEquals(
                HttpStatus.NOT_FOUND.value() ,
                ld.getStatusCodeValue());

    }

    /*
    *
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
    * */
}
