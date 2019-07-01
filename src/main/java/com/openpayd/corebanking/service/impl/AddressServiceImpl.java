package com.openpayd.corebanking.service.impl;

import com.openpayd.corebanking.entity.Address;
import com.openpayd.corebanking.entity.dto.AddressDTO;
import com.openpayd.corebanking.repository.AddressRepository;
import com.openpayd.corebanking.service.IAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements IAddressService {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    AddressRepository addressRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO){
        Address address = addressRepository.save(convertToEntity(addressDTO));
        System.out.println(address.getId());
        System.out.println(address.getAddressLine1());
        return new AddressDTO(address);
    }

    private Address convertToEntity(AddressDTO addressDTO){
        return modelMapper.map(addressDTO, Address.class);
    }

}
