package com.openpayd.corebanking.repository;

import com.openpayd.corebanking.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository  extends CrudRepository<Address,Long> {

}
