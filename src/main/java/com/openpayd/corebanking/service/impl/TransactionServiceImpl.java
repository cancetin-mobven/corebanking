package com.openpayd.corebanking.service.impl;

import com.openpayd.corebanking.entity.Client;
import com.openpayd.corebanking.entity.Transaction;
import com.openpayd.corebanking.entity.Transfer;
import com.openpayd.corebanking.entity.TransferType;
//import com.openpayd.corebanking.entity.builder.TransferBuilder;
import com.openpayd.corebanking.entity.dto.ClientDTO;
import com.openpayd.corebanking.entity.dto.TransactionDTO;
import com.openpayd.corebanking.entity.dto.TransferDTO;
import com.openpayd.corebanking.repository.TransactionRepository;
import com.openpayd.corebanking.service.IAccountService;
import com.openpayd.corebanking.service.ITransactionService;
import com.openpayd.corebanking.service.ITransferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionServiceImpl implements ITransactionService{

    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    IAccountService accountService;

    @Autowired
    public ITransferService transferService;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
   @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {

        TransferDTO debitTransferDTO = new TransferDTO(transactionDTO.getDebitAccount(),transactionDTO.getAmount(), TransferType.DEBIT);
        TransferDTO creditTransferDTO = new TransferDTO(transactionDTO.getCreditAccount(),transactionDTO.getAmount(), TransferType.CREDIT);
        Transfer debitTransfer = transferService.createTransfer(debitTransferDTO); //Debit
        Transfer creditTransfer = transferService.createTransfer(creditTransferDTO); //Credit

        Transaction transaction = transactionRepository.save(convertToEntity(transactionDTO));
        return new TransactionDTO(transaction);
    }

    private Transaction convertToEntity(TransactionDTO transactionDTO){
        return modelMapper.map(transactionDTO, Transaction.class);
    }

}
