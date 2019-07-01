package com.openpayd.corebanking.repository;

import com.openpayd.corebanking.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository <Client,Long> {

    @Override
    Optional<Client> findById(Long aLong);

    List<Client> findAll();
}
