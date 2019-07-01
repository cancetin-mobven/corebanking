package com.openpayd.corebanking.service;

import com.openpayd.corebanking.entity.Client;
import com.openpayd.corebanking.entity.dto.ClientDTO;
import java.util.List;

public interface IClientService {

    List<ClientDTO> listClients();

    public ClientDTO saveClient(ClientDTO clientDTO);

    ClientDTO getClient(Long clientId);

    Client findClientById(Long clientId);

}