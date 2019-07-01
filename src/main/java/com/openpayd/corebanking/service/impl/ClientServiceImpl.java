package com.openpayd.corebanking.service.impl;

import com.openpayd.corebanking.entity.Client;
import com.openpayd.corebanking.entity.dto.ClientDTO;
import com.openpayd.corebanking.repository.ClientRepository;
import com.openpayd.corebanking.service.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements IClientService {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ClientRepository clientRepository;

    @Override
    @Transactional
    public List<ClientDTO> listClients() {
       // List<Client>  l =clientRepository.findAll();
        //System.out.println(l.size());
     return clientRepository.findAll()
             .stream()
             .map( client -> new ClientDTO(client) ).collect(Collectors.toList());
        //List<ClientDTO>  clientDTOList = clientRepository.findAll();
        //return clientDTOList;
    }

    @Override
   // @Transactional
    public ClientDTO saveClient(ClientDTO clientDto) {
        Client client = clientRepository.save(convertToEntity(clientDto));
        System.out.println(client.getId());
        System.out.println(client.getName());
        return new ClientDTO(client);
    }

    @Override
    public ClientDTO getClient(Long clientId) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);

        if(clientOpt.isPresent()){
            return new ClientDTO(clientOpt.get());
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public Client findClientById(Long clientId) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);

        if(clientOpt.isPresent()){
            return clientOpt.get();
        }else{
            return null;
        }
    }

    private Client convertToEntity(ClientDTO clientDTO){
        return modelMapper.map(clientDTO, Client.class);
    }


}
