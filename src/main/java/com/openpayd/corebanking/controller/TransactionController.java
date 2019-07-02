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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(httpMethod = "POST",
            value = "performs transfer operation",
            notes = "creates transaction, " +
                    "creates transfer records consisting debit and credit moves. " +
                    "Also this service updates balance of accounts",
            response = TransactionDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "created transaction successfully", response = ClientDTO.class),
            @ApiResponse(code = 404, message = "Credit or debit account inf. could not be found with given parameters."),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public ResponseEntity<TransactionDTO> doTransfer(@RequestBody TransactionDTO transactionDTO, HttpServletRequest request) {

        if (transactionDTO.getDebitAccount() == null || transactionDTO.getCreditAccount() == null) {
            return new ResponseEntity<TransactionDTO>(transactionDTO, HttpStatus.NOT_FOUND);
        }

        Account debitAccount = accountService.findById(transactionDTO.getDebitAccount().getId());
        if (debitAccount == null) {
            return new ResponseEntity<TransactionDTO>(transactionDTO, HttpStatus.NOT_FOUND);
        }

        transactionDTO.setDebitAccount(debitAccount);
        Account creditAccount = accountService.findById(transactionDTO.getCreditAccount().getId());
        if (creditAccount == null) {
            return new ResponseEntity<TransactionDTO>(transactionDTO, HttpStatus.NOT_FOUND);
        }

        if (debitAccount.getId() == creditAccount.getId()) {
            return new ResponseEntity<TransactionDTO>(transactionDTO, HttpStatus.BAD_REQUEST);
        }

        transactionDTO.setCreditAccount(creditAccount);

        return new ResponseEntity<TransactionDTO>(transactionService.createTransaction(transactionDTO), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/transactionsByAccount/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(httpMethod = "GET",
            value = "lists transfers by Account",
            response = Transfer.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "listed transactions successfully", response = Transfer.class),
            @ApiResponse(code = 404, message = "Transactions could not be found with given parameters."),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public ResponseEntity<List<Transfer>> listTransactionByAccount(@PathVariable("id") Long accountId, HttpServletRequest request) {

        List<Transfer> transfers = null;
        try {
            Account account = accountService.findById(accountId);
            transfers = transferService.findByAccount(account);
            returnCode = HttpStatus.OK;
        } catch (Exception e) {
            logger.debug(e.getMessage());
            returnCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if (transfers == null || transfers.size() == 0) {
            return new ResponseEntity<List<Transfer>>(transfers, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Transfer>>(transfers, returnCode);
        }
    }


}
