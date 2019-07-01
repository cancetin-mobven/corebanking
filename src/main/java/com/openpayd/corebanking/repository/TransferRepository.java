package com.openpayd.corebanking.repository;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends CrudRepository<Transfer,Long> {

    public List<Transfer> findByAccount(Account account);

}
