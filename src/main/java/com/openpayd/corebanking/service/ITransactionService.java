package com.openpayd.corebanking.service;

import com.openpayd.corebanking.entity.dto.TransactionDTO;

public interface ITransactionService {

    TransactionDTO createTransaction(TransactionDTO transactionDTO);

}
