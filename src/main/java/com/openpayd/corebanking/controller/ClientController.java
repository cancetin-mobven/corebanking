package com.openpayd.corebanking.controller;


import com.openpayd.corebanking.entity.dto.ClientDTO;
import com.openpayd.corebanking.service.IClientService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
@Api(value = "Clients", description = "Client management api")
public class ClientController {

    private Logger logger = LoggerFactory.getLogger(ClientController.class);

    HttpStatus returnCode = HttpStatus.OK;

    @Autowired
    IClientService clientService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ClientDTO>> listClient(HttpServletRequest request){
        List<ClientDTO> listClientDTO = null;
        try {
            listClientDTO = clientService.listClients();
        }catch (Exception e){
            logger.error(e.getMessage());
            returnCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if(listClientDTO == null){
            return new ResponseEntity<List<ClientDTO>>(listClientDTO, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<ClientDTO>>(listClientDTO, returnCode);
        }
    }

//    @RequestMapping(value = "generateBill/{id}/{rates}", method=RequestMethod.GET)

    @RequestMapping(value = "/listById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ClientDTO> listClientById(@PathVariable("id") Long clientId, HttpServletRequest request){
        ClientDTO client = null;

        try {
            client = clientService.getClient(clientId);
            returnCode = HttpStatus.OK;
        } catch (Exception e){
            logger.debug(e.getMessage());
            returnCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if(client == null){
            return new ResponseEntity<ClientDTO>(client, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<ClientDTO>(client, returnCode);
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO, HttpServletRequest request){
        ClientDTO cto = null;
        try{
            cto = clientService.saveClient(clientDTO);
            returnCode = HttpStatus.CREATED;
        }catch (Exception e){
            returnCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(cto ,returnCode);
    }

}
