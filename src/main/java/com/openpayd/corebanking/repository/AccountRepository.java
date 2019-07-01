package com.openpayd.corebanking.repository;

import com.openpayd.corebanking.entity.Account;
import com.openpayd.corebanking.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account,Long> {

    @Override
    Optional<Account> findById(Long id);

    List<Account> findByClient(Client client);

}
