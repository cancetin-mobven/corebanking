package com.openpayd.corebanking.controller;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.Client;
import com.openpayd.corebanking.entity.dto.AccountDTO;
import com.openpayd.corebanking.entity.dto.ClientDTO;
import com.openpayd.corebanking.service.IAccountService;
import com.openpayd.corebanking.service.IClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.List;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/account")
@Api(value = "accounts", description = "Account management api")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    HttpStatus returnCode = HttpStatus.OK;

    @Autowired
    IAccountService accountService;

    @Autowired
    IClientService clientService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(httpMethod = "GET",
            value = "Creates client account",
            response = AccountDTO.class,
            responseContainer = "Object"
    )
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO, HttpServletRequest request){
        System.out.println(accountDTO.toString());
        AccountDTO account = null;
        try{
            account = accountService.createAccount(accountDTO);
        }catch (Exception e){
            returnCode = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(account, returnCode);
    }

    @RequestMapping(value = "/accountByClient/{clientId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<AccountDTO>> listAccountByClient(@PathVariable("clientId") Long clientId, HttpServletRequest request){
        List<AccountDTO> accountDTOList = null;

        try {
            Client client = clientService.findClientById(clientId);
            accountDTOList = accountService.findByClient(client);
        } catch (Exception e){
            logger.debug(e.getMessage());
            returnCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if(accountDTOList == null){
            return new ResponseEntity<List<AccountDTO>>(accountDTOList , HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<AccountDTO>>(accountDTOList , returnCode);
        }
    }


}
