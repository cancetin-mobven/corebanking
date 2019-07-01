package com.openpayd.corebanking.service;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.Transfer;
import com.openpayd.corebanking.entity.dto.TransactionDTO;
import com.openpayd.corebanking.entity.dto.TransferDTO;

import java.util.List;

public interface ITransferService {

    Transfer createTransfer(TransferDTO transferDTO);

    List<Transfer> findByAccount(Account account);

}
