package com.openpayd.corebanking.service.impl;

import com.openpayd.corebanking.entity.*;
import com.openpayd.corebanking.entity.dto.ClientDTO;
import com.openpayd.corebanking.entity.dto.TransactionDTO;
import com.openpayd.corebanking.entity.dto.TransferDTO;
import com.openpayd.corebanking.repository.ClientRepository;
import com.openpayd.corebanking.repository.TransferRepository;
import com.openpayd.corebanking.service.IAccountService;
import com.openpayd.corebanking.service.ITransactionService;
import com.openpayd.corebanking.service.ITransferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferServiceImpl  implements ITransferService {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    public IAccountService accountService;

    @Override
  //  @Transactional
    public Transfer createTransfer(TransferDTO transferDTO){
        if( transferDTO.transferType == TransferType.DEBIT){
            accountService.debit(transferDTO.amount,transferDTO.account);
        }else{
            accountService.credit(transferDTO.amount,transferDTO.account);
        }
       return transferRepository.save(convertToEntity(transferDTO));

    }

    @Override
    public List<Transfer> findByAccount(Account account){
        return transferRepository.findByAccount(account);
    }


    private Transfer convertToEntity(TransferDTO transferDTO){
        return modelMapper.map(transferDTO, Transfer.class);
    }




}
