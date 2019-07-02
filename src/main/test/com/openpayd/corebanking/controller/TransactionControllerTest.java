package com.openpayd.corebanking.controller;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.Client;
import com.openpayd.corebanking.entity.dto.TransactionDTO;
import com.openpayd.corebanking.service.IAccountService;
import com.openpayd.corebanking.service.ITransactionService;
import com.openpayd.corebanking.service.ITransferService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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
    public void shouldDoTransferNotFoundHttpCode() {
        List<Client> clients = new ArrayList<Client>();
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        Long id = 0L;
        TransactionDTO transactionDTO = new TransactionDTO();
        Account debitAccount = new Account();
        debitAccount.setId(1L);
        transactionDTO.setDebitAccount(debitAccount);
        transactionDTO.setCreditAccount(debitAccount);

        when(accountService.findById(any(Long.class))).thenReturn(null);
        ResponseEntity<TransactionDTO> ld= transactionController.doTransfer(transactionDTO, mockedRequest);

        assertEquals(
                HttpStatus.NOT_FOUND.value() ,
                ld.getStatusCodeValue());
    }

    @org.junit.Test
    public void shouldDoTransferCreateTransactionNotInvokedSameAccounts() {
        List<Client> clients = new ArrayList<Client>();
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        Long id = 0L;
        TransactionDTO transactionDTO = new TransactionDTO();
        Account debitAccount = new Account();
        debitAccount.setId(1L);
        transactionDTO.setDebitAccount(debitAccount);
        when(accountService.findById(any(Long.class))).thenReturn(debitAccount); // always return same

        ResponseEntity<TransactionDTO> dt = transactionController.doTransfer(transactionDTO, mockedRequest);
        verify(transactionService,Mockito.times(0)).createTransaction(any(TransactionDTO.class));

    }

    @org.junit.Test
    public void shouldDoTransferCreateTransaction() {
        List<Client> clients = new ArrayList<Client>();
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        Long id = 0L;
        TransactionDTO transactionDTO = new TransactionDTO();
        Account debitAccount = new Account();
        debitAccount.setId(1L);
        Account creditAccount = new Account();
        creditAccount.setId(2L);
        transactionDTO.setDebitAccount(debitAccount);
        transactionDTO.setCreditAccount(creditAccount);
        when(accountService.findById(1l)).thenReturn(debitAccount);
        when(accountService.findById(2l)).thenReturn(creditAccount);

        ResponseEntity<TransactionDTO> dt = transactionController.doTransfer(transactionDTO, mockedRequest);
        verify(transactionService,Mockito.times(1)).createTransaction(any(TransactionDTO.class));
    }



}
