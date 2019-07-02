package com.openpayd.corebanking.controller;

import com.openpayd.corebanking.entity.dto.AddressDTO;
import com.openpayd.corebanking.service.IAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/address")
public class AddressController {

    private Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    IAddressService addressService;

    HttpStatus returnCode = HttpStatus.CREATED;

    @RequestMapping(value = "/saveAddress", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO, HttpServletRequest request) {

        AddressDTO addressDto = null;
        try {
            addressDto = addressService.createAddress(addressDTO);
        } catch (Exception e) {
            returnCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<AddressDTO>(addressDto, returnCode);
    }


}
