package com.openpayd.corebanking.service;

import com.openpayd.corebanking.entity.dto.AccountDTO;
import com.openpayd.corebanking.entity.dto.AddressDTO;

public interface IAddressService {
    public AddressDTO createAddress(AddressDTO addressDTO);
}
